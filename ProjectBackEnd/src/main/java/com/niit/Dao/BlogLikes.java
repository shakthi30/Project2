package com.niit.Dao;

import com.niit.Model.BlogPost;
import com.niit.Model.BlogPostLikes;
import com.niit.Model.User;

public interface BlogLikes {
	public BlogPostLikes userLikes(BlogPost blogPost, User user);

	public BlogPost updateBlogPostLikes(BlogPost blogPost, User user);
}
