package com.niit.restController;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.niit.Dao.BlogDao;
import com.niit.Dao.BlogLikes;
import com.niit.Dao.UserDao;
import com.niit.Model.BlogComment;
import com.niit.Model.BlogPost;
import com.niit.Model.BlogPostLikes;
import com.niit.Model.User;

@Controller
public class BlogController {
	@Autowired
	BlogDao blogDao;

	@Autowired
	UserDao userDao;

	@Autowired
	BlogLikes blogLikesDao;

	@RequestMapping(value = "/addBlog", method = RequestMethod.POST)
	public ResponseEntity<?> saveBlog(@RequestBody BlogPost blogPost, HttpSession session) {
		String username = (String) session.getAttribute("username");
		if (username == null) {
			return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
		} else {
			User user = userDao.getUserByUsername(username);

			blogPost.setPostedOn(new Date());
			blogPost.setPostedBy(user);
			blogPost.setApproved(false);
			try {
				blogDao.saveBlogPost(blogPost);
			} catch (Exception e) {
				return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<BlogPost>(blogPost, HttpStatus.OK);
		}

	}

	@RequestMapping(value = "/getBlogs/{approval}", method = RequestMethod.GET)
	public ResponseEntity<?> getBlogs(@PathVariable int approval, HttpSession session) {
		String username = (String) session.getAttribute("username");
		if (username == null) {
			return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
		} else {
			List<BlogPost> blogpost = blogDao.getBlogs(approval);
			return new ResponseEntity<List<BlogPost>>(blogpost, HttpStatus.OK);
		}

	}

	@RequestMapping(value = "/getBlog/{blogId}", method = RequestMethod.GET)
	public ResponseEntity<?> getBlogById(@PathVariable int blogId, HttpSession session) {
		String username = (String) session.getAttribute("username");
		if (username == null) {
			return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
		} else {
			BlogPost blogPost = blogDao.getBlogById(blogId);
			return new ResponseEntity<BlogPost>(blogPost, HttpStatus.OK);
		}

	}

	@RequestMapping(value = "/updateBlog", method = RequestMethod.PUT)
	public ResponseEntity<?> updateBlog(@RequestBody BlogPost blog,
			@RequestParam(required = false) String rejectionReason, HttpSession session) {

		String username = (String) session.getAttribute("username");
		if (username == null) {
			return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
		} else {
			System.out.println(blog.getId());
			blogDao.updateBlogPost(blog, rejectionReason);
			return new ResponseEntity<BlogPost>(blog, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/getLikes/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> userLikes(@PathVariable int id, HttpSession session) {

		String username = (String) session.getAttribute("username");
		User user = userDao.getUserByUsername(username);
		BlogPost blogPost = blogDao.getBlogById(id);
		if (username == null) {
			return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
		} else {
			BlogPostLikes blogPostLikes = blogLikesDao.userLikes(blogPost, user);
			System.out.println(blogPostLikes + "here you are");
			return new ResponseEntity<BlogPostLikes>(blogPostLikes, HttpStatus.OK);
		}

	}

	@RequestMapping(value = "/updateLike", method = RequestMethod.PUT)
	public ResponseEntity<?> updateLike(@RequestBody BlogPost blogPost, HttpSession session) {

		String username = (String) session.getAttribute("username");
		User user = userDao.getUserByUsername(username);

		if (username == null) {
			return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
		} else {

			int blogId = blogPost.getId();
			blogPost.setId(blogId);
			BlogPost blogPostResult = blogLikesDao.updateBlogPostLikes(blogPost, user);
			return new ResponseEntity<BlogPost>(blogPostResult, HttpStatus.OK);
		}

	}

	@RequestMapping(value = "/number_Of_Likes/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> numberOfLikes(@PathVariable int id, HttpSession session) {
		String username = (String) session.getAttribute("username");
		User user = userDao.getUserByUsername(username);

		if (username == null) {
			return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
		} else {
			BlogPost blogPost = blogDao.getBlogById(id);
			int numberOfLikes = blogDao.getNumberOfLikes(blogPost);
			return new ResponseEntity<Integer>(numberOfLikes, HttpStatus.OK);
		}

	}

	@RequestMapping(value = "/post_Comment/{id}", method = RequestMethod.POST)
	public ResponseEntity<?> addComment(@RequestBody BlogComment blogComment, @PathVariable int id,
			HttpSession session) {
		String username = (String) session.getAttribute("username");
		User user = userDao.getUserByUsername(username);

		if (username == null) {
			return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
		} else {
			BlogPost blogPost = blogDao.getBlogById(id);
			blogComment.setCommentedBy(user);
			blogComment.setCommentedOn(new Date());
			blogComment.setBlogPost(blogPost);
			blogDao.addComment(blogComment);
			return new ResponseEntity<BlogComment>(blogComment, HttpStatus.OK);
		}

	}

	@RequestMapping(value = "/get_Comments/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getComments(@PathVariable int id) {
		BlogPost blog = blogDao.getBlogById(id);
		List<BlogComment> comments = blog.getBlogComment();
		return new ResponseEntity<List<BlogComment>>(comments, HttpStatus.OK);
	}

}
