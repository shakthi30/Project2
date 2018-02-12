package com.niit.DaoImplementation;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.Dao.BlogDao;
import com.niit.Model.BlogComment;
import com.niit.Model.BlogPost;
import com.niit.Model.Notification;

@Repository("blogDao")
public class BlogDaoImpl implements BlogDao {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public void saveBlogPost(BlogPost blog) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		System.out.println("error here");
		session.save(blog);
		session.getTransaction().commit();

	}

	@Override
	public List<BlogPost> getBlogs(int approval) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("from BlogPost where approved = " + approval + "");
		List<BlogPost> blogposts = query.list();
		session.getTransaction().commit();
		return blogposts;

	}

	@Override
	public BlogPost getBlogById(int id) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		BlogPost blogPost = (BlogPost) session.get(BlogPost.class, id);
		session.getTransaction().commit();
		return blogPost;
	}

	@Override
	public void updateBlogPost(BlogPost blog, String rejectionReason) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Notification notification = new Notification();

		notification.setBlogTitle(blog.getBlogTitle());
		notification.setUserName(blog.getPostedBy().getUser_firstName());
		notification.setViewed(false);
		if (blog.getApproved()) {
			session.update(blog);
			notification.setApprovalStatus("APPROVED");
			session.save(notification);
		} else {
			if (rejectionReason == null) {
				notification.setRejectionReason("NOT RELEVANT");
			} else {
				notification.setRejectionReason(rejectionReason);
				notification.setApprovalStatus("REJECTED");
				session.save(notification);
				session.delete(blog);

			}
		}
		session.getTransaction().commit();

	}

	@Override
	public int getNumberOfLikes(BlogPost blogPost) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("from BlogPost where id= :id");
		query.setInteger("id", blogPost.getId());
		BlogPost blogPostResult = (BlogPost) query.uniqueResult();
		session.getTransaction().commit();
		return blogPostResult.getLikes();
	}

	@Override
	public void addComment(BlogComment blogComment) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(blogComment);
		session.getTransaction().commit();

	}

	@Override
	public BlogPost getComments(int id) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		BlogPost blogPost = (BlogPost) session.get(BlogPost.class, id);
		session.getTransaction().commit();
		return blogPost;

	}

}
