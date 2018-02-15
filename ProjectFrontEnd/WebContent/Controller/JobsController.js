/**
 * JobsController
 */
app.controller("JobsController", function($scope, JobService, $location,
		$rootScope) {
	$scope.value = false;
	$scope.addJob = function() {
		console.log(JobService);
		JobService.addJob($scope.job).then(function(response) {
			console.log(response.status)
		}, function(response) {
			response.status
		})
	}
	function getAllJobs() {
		console.log("inside get all jobs")
		JobService.getAllJobs().then(function(response) {
			$scope.jobs = response.data
			console.log(response.data)
		}, function(response) {
			alert("please Login")
			$location.path("/login")
		})
	}
	getAllJobs();
	$scope.getJob = function(id) {

		JobService.getJob(id).then(function(response) {
			$scope.value = true;
			$scope.job = response.data
		}, function(response) {
			response.status
		})
	}

})
