package com.niit.Dao;

import java.util.List;

import com.niit.Model.BlogComment;
import com.niit.Model.BlogPost;

public interface BlogDao {
	public void saveBlogPost(BlogPost blog);

	public List<BlogPost> getBlogs(int approval);

	public BlogPost getBlogById(int id);

	public void updateBlogPost(BlogPost blog, String rejectionReason);

	public int getNumberOfLikes(BlogPost blogPost);

	public void addComment(BlogComment blogComment);

	public BlogPost getComments(int id);
}
