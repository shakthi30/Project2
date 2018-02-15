/**
 * BlogController
 */
app.controller("BlogController", function($scope, BlogService, $routeParams) {

	var id = $routeParams.id;

	$scope.addComment = function() {
		$scope.blogComment.blogPost = $scope.blog;
		BlogService.addComment($scope.blogComment).then(function(response) {
			alert("Comment Posted")
			getComments();
			$scope.blogComment.commentText = '';
		}, function(response) {
			if (response.status == 401) {
				alert("Unauthorized access! Please Log in")
			}
		})
	}
	$scope.saveBlog = function() {
		BlogService.save($scope.blog).then(function(response) {
			response.data
			$scope.success = response.status
		}, function(response) {
			$scope.error = response.status
		})
	}
	function getAllBlogs() {
		BlogService.getBlogsWaiting().then(function(response) {
			$scope.blogsWaiting = response.data
		}, function(response) {
		})
		BlogService.getBlogsApproved().then(function(response) {
			$scope.blogsApproved = response.data
		}, function(response) {
		})
	}
	$scope.updateBlog = function() {
		console.log($scope.blog.approved)
		BlogService.updateBlog($scope.blog, $scope.rejectionReason).then(
				function(response) {
					console.log(response.data)
				}, function(response) {
					console.log(response.status)
				})
	}
	$scope.updateLike = function() {
		BlogService.updateLike($scope.blog).then(function(response) {
			$scope.liked = !$scope.liked
		}, function(response) {
		})
		BlogService.NumberOfLikes(id).then(function(response) {
			$scope.likes = response.data
		}, function(response) {
			if (response.status == 401)
				alert("Unauthorize! Please Login")
		})
	}
	BlogService.userLikes(id).then(function(response) {
		if (response.data == '') {

			$scope.liked = false
		} else {
			$scope.liked = true
		}
	}, function(response) {
		if (response.status == 401)
			alert("Unauthorize! Please Login")
	})
	BlogService.NumberOfLikes(id).then(function(response) {
		$scope.likes = response.data
	}, function(response) {
		if (response.status == 401)
			alert("Unauthorize! Please Login")
	})
	function getComments() {
		BlogService.getComments(id).then(function(response) {
			$scope.comments = response.data
		}, function(response) {
			response.status
		})
	}
	getComments();
	getAllBlogs();
	function getBlog() {

		if (id == undefined) {
			return;
		} else {

			BlogService.getBlog(id).then(function(response) {
				$scope.blog = response.data
				console.log(response.data)
			}, function(response) {
				console.log(response.data)
			})

		}

	}
	getBlog();

})