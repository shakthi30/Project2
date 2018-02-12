/**
 * 
 */
app.factory('LoginService', function($http) {
	var BASE_URL = "http://localhost:8082/ProjectMiddleWare";
	var loginService = {}

	loginService.registerUser = function(user) {
		return $http.post(BASE_URL + "/insert_UserDetails", user)
	}

	loginService.login = function(user) {
		return $http.post(BASE_URL + "/login", user)
	}

	loginService.logout = function() {
		console.log("in log out function in service!!")
		return $http.get(BASE_URL + "/logout")
	}
	return loginService;
})