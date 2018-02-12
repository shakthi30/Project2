/**
 * HomeController
 */
app
		.controller(
				"HomeController",
				function($rootScope, $location, HomeService) {
					function getNotification() {
						HomeService
								.getNotificationNotViewed()
								.then(
										function(response) {

											$rootScope.notificationNotViewed = response.data

										}, function(response) {
											if (response.status == 400) {
											}
										})
						HomeService
								.getNotificationViewed()
								.then(
										function(response) {

											$rootScope.notificationViewed = response.data;
											$rootScope.notificationLength = $rootScope.notificationViewed.length
										}, function(response) {
											if (response.status == 400) {
											}
										})

					}
					$rootScope.clearNotification = function() {
						console.log("in clearNotification")
						$rootScope.notificationLength = 0;
					}
					$rootScope.updateNotification = function(id) {
						HomeService.updateNotification(id).then(
								function(response) {
									getNotification();
								}, function(response) {
									if (resposne.status == 401) {
										$location.path('/login');
									}
								})
					}

					getNotification();
				})