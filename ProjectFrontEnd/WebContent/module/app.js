var app = angular.module("App", [ 'ngRoute', 'ngCookies' ])
app.config(function($routeProvider) {

	$routeProvider.when('/register', {
		templateUrl : "Views/login_registrationform.html",
		controller : "LoginController"

	}).when('/', {
		templateUrl : "Views/loginForm.html",
		controller : "LoginController"

	}).when('/home', {
		templateUrl : "Views/Home_Page.html",
		controller : "HomeController"
	}).when('/addJobs', {
		templateUrl : "Views/jobs_Form.html",
		controller : "JobsController"
	}).when('/viewJobs', {
		templateUrl : "Views/jobs_List.html",
		controller : "JobsController"
	}).when('/addBlog', {
		templateUrl : "Views/blog_Form.html",
		controller : "BlogController"
	}).when('/getBlogs', {
		templateUrl : "Views/blog_List.html",
		controller : "BlogController"
	}).when('/admin/getBlog/:id', {
		templateUrl : "Views/approva_Form.html",
		controller : "BlogController"
	}).when('/getBlog/:id', {
		templateUrl : "Views/approved_List.html",
		controller : "BlogController"
	}).when('/suggestedUsers', {
		templateUrl : "Views/suggestedUser_List.html",
		controller : "FriendsController"
	}).when('/pendingRequests', {
		templateUrl : "Views/pendingRequests_List.html",
		controller : "FriendsController"
	}).when('/friendsList', {
		templateUrl : "Views/friends_List.html",
		controller : "FriendsController"
	}).when('/chat', {
		templateUrl : "Views/chat.html",
		controller : "ChatController"
	})

})
app.run(function($rootScope, $cookieStore, LoginService, $location) {

	if ($rootScope.username == undefined) {
		$rootScope.username = $cookieStore.get('username')
	}
	$rootScope.logout = function() {

		LoginService.logout().then(function(response) {
			delete $rootScope.username
			$cookieStore.remove('username')
			$location.path('/')
		}, function(response) {
			console.log(response.status)
		})
	}
})
