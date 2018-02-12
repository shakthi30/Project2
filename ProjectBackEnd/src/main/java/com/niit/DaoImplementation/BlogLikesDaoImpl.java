package com.niit.DaoImplementation;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.Dao.BlogLikes;
import com.niit.Model.BlogPost;
import com.niit.Model.BlogPostLikes;
import com.niit.Model.User;

@Repository("blogLikesDao")
public class BlogLikesDaoImpl implements BlogLikes {
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public BlogPostLikes userLikes(BlogPost blogPost, User user) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		System.out.println(blogPost.getId() + "" + user.getUser_Name());
		Query query = session.createQuery("from BlogPostLikes where blogPost.id= :blogPost  and user.user_Name= :user");
		query.setInteger("blogPost", blogPost.getId());
		query.setString("user", user.getUser_Name());
		BlogPostLikes blogPostLikes = (BlogPostLikes) query.uniqueResult();
		session.getTransaction().commit();
		return blogPostLikes;
	}

	@Override
	public BlogPost updateBlogPostLikes(BlogPost blogPost, User user) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		BlogPostLikes blogPostLikes = userLikes(blogPost, user);
		if (blogPostLikes == null) {
			System.out.println("in update likes amna");
			System.out.println(blogPost.getId());
			BlogPostLikes insertLikes = new BlogPostLikes();
			insertLikes.setBlogPost(blogPost);
			insertLikes.setUser(user);
			session.save(insertLikes);
			blogPost.setLikes(blogPost.getLikes() + 1);
			System.out.println(blogPost.getLikes() + 1);
			session.update(blogPost);
		} else {
			session.delete(blogPostLikes);
			blogPost.setLikes(blogPost.getLikes() - 1);
			session.merge(blogPost);
		}

		session.getTransaction().commit();
		return blogPost;
	}

}
