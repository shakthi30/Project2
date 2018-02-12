/**
 * HomeService
 */
app.factory("HomeService", function($http) {
	var homeService = {};
	var BASE_URL = "http://localhost:8082/ProjectMiddleWare";
	homeService.getNotificationNotViewed = function() {
		return $http.get(BASE_URL + "/getNotifications/" + 0);
	}
	homeService.getNotificationViewed = function() {
		return $http.get(BASE_URL + "/getNotifications/" + 1);
	}
	homeService.updateNotification = function(id) {
		return $http.put(BASE_URL + "/updateNotification/" + id);
	}
	return homeService;

})