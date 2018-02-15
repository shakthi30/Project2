/**
 * FriendsController
 */
app.controller('FriendsController', function(FriendService, $scope) {
	function getSuggestedUsers() {
		FriendService.suggestedUsers().then(function(response) {
			$scope.suggestedUsers = response.data
			console.log(suggestedUsers)
		}, function(response) {
			console.log(response.status)
		})
	}
	$scope.addFriend = function(toId) {
		FriendService.addFriend(toId).then(function(response) {
			alert("Friend Request sent")
			getSuggestedUsers();
		}, function(response) {
			console.log(response.status)
		})
	}
	getSuggestedUsers();
	function pendingRequests() {
		FriendService.pendingRequests().then(function(response) {
			$scope.pendingRequests = response.data
			
			console.log(response.data.length)
			
		}, function(response) {alert("No requests")
		})
	}
	pendingRequests();
	$scope.accept = function(id) {
		FriendService.accept(id).then(function(response) {
			alert("Accepted")
			pendingRequests();
		}, function(response) {
			console.log(response.status)
		})

	}
	$scope.deleteReq = function(id) {
		FriendService.deleteReq(id).then(function(response) {
			alert("Deleted")
			pendingRequests();
		}, function(response) {
			console.log(response.status)
		})
	}
	listOfFriends = function() {
		FriendService.friends().then(function(response) {
			$scope.friends = response.data
		}, function(response) {
		})
	}
	listOfFriends();
})