package com.niit.Model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Blog_Table")
public class BlogPost {
	@Id
	@GeneratedValue
	private int id;
	@Column(unique = true)
	private String blogTitle;
	@Lob
	private String blogContent;
	@OneToMany(mappedBy = "blogPost", fetch = FetchType.EAGER)
	private List<BlogComment> blogComment;

	public List<BlogComment> getBlogComment() {
		return blogComment;
	}

	public void setBlogComment(List<BlogComment> blogComment) {
		this.blogComment = blogComment;
	}

	private Date postedOn;
	@ManyToOne
	private User postedBy;
	private Boolean approved;

	public Boolean getApproved() {
		return approved;
	}

	public void setApproved(Boolean approved) {
		this.approved = approved;
	}

	private int likes;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBlogTitle() {
		return blogTitle;
	}

	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}

	public String getBlogContent() {
		return blogContent;
	}

	public void setBlogContent(String blogContent) {
		this.blogContent = blogContent;
	}

	public Date getPostedOn() {
		return postedOn;
	}

	public void setPostedOn(Date postedOn) {
		this.postedOn = postedOn;
	}

	public User getPostedBy() {
		return postedBy;
	}

	public void setPostedBy(User postedBy) {
		this.postedBy = postedBy;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

}
