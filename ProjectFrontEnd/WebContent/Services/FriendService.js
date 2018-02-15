/**
 * FriendService
 */
app.factory('FriendService', function($http) {
	var BASE_URL = "http://localhost:8082/ProjectMiddleWare";
	var friendService = {};
	friendService.suggestedUsers = function() {
		return $http.get(BASE_URL + "/suggested_users")
	}
	friendService.addFriend = function(toId) {
		return $http.post(BASE_URL + "/addFriend/" + toId)
	}
	friendService.pendingRequests = function() {
		return $http.get(BASE_URL + "/pendingRequests")
	}
	friendService.accept = function(id) {
		return $http.put(BASE_URL + "/accept_Request/" + id)
	}
	friendService.deleteReq = function(id) {
		return $http.put(BASE_URL + "/delete_Request/" + id)
	}
	friendService.friends = function() {
		return $http.get(BASE_URL + "/friends")
	}
	return friendService;

})