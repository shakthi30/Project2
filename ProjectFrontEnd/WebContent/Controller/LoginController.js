/**
 * LoginController
 */
app.controller('LoginController', function($scope, LoginService, $location,
		$rootScope, $cookieStore) {

	$scope.registerUser = function() {
		LoginService.registerUser($scope.user).then(function(response) {
			if (response.status == 200) {
				console.log(response.data)
			}
		}, function(response) {
			console.log(response.status)
		})
	}
	$scope.login = function() {
		console.log("inside login")
		console.log($scope.user)
		LoginService.login($scope.user).then(function(response) {
			if (response.status == 200) {
				$location.path("/home");
				$rootScope.username = response.data
				$cookieStore.put('username', response.data)

			}
		}, function(response) {
			console.log(response.status)
		})
	}
})