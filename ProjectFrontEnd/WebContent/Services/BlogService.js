/**
 * BlogService
 */
app.factory("BlogService", function($http) {
	var BASE_URL = "http://localhost:8082/ProjectMiddleWare";
	var blogService = {};
	blogService.getComments = function(id) {
		return $http.get(BASE_URL + "/get_Comments/" + id)
	}
	blogService.addComment = function(blogComment) {
		return $http.post(
				BASE_URL + "/post_Comment/" + blogComment.blogPost.id,
				blogComment)
	}
	blogService.updateLike = function(blog) {
		return $http.put(BASE_URL + "/updateLike", blog)
	}
	blogService.NumberOfLikes = function(id) {
		return $http.get(BASE_URL + "/number_Of_Likes/" + id)
	}
	blogService.userLikes = function(id) {
		return $http.get(BASE_URL + "/getLikes/" + id)
	}
	blogService.save = function(blog) {
		return $http.post(BASE_URL + "/addBlog", blog)
	}
	blogService.getBlogsWaiting = function() {
		return $http.get(BASE_URL + "/getBlogs/" + 0)
	}
	blogService.getBlogsApproved = function() {
		return $http.get(BASE_URL + "/getBlogs/" + 1)
	}
	blogService.getBlog = function(id) {
		return $http.get(BASE_URL + "/getBlog/" + id)
	}
	blogService.updateBlog = function(blog, rejectionReason) {
		console.log(blog)
		if (rejectionReason == undefined) {
			return $http.put(BASE_URL + "/updateBlog?rejectionReason="
					+ 'Not_Mentioned', blog)
		} else {
			return $http.put(BASE_URL + "/updateBlog?rejectionReason="
					+ rejectionReason, blog)
		}
	}
	return blogService;
})