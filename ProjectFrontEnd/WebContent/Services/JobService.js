/**
 * JobService
 */
app.factory("JobService", function($http) {
	var BASE_URL = "http://localhost:8082/ProjectMiddleWare";
	var jobService = {};
	jobService.addJob = function(job) {
		return $http.post(BASE_URL + "/add_Jobs", job)
	}
	console.log(jobService);
	jobService.getAllJobs = function() {
		return $http.get(BASE_URL + "/jobsList")
	}
	jobService.getJob = function(id) {
		return $http.get(BASE_URL + "/getJob/" + id)
	}
	return jobService;

})