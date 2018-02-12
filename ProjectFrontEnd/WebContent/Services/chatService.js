/**
 * 
 */
app.filter('reverse', function() {
	return function(items) {
		return items.slice().reverse();
	};
});

app.directive('ngFocus', function() {
	return function(scope, element, attrs) {
		element.bind('click', function() {
			$('.' + attrs.ngFocus)[0].focus();
		});
	};
});

app.factory('socket', function($rootScope) {
	alert('app factory')
	var socket = new SockJS('/ProjectMiddleWare/chatmodule');
	var stompClient = Stomp.over(socket);
	stompClient.connect('', '', function(frame) {
		console.log(frame)
		$rootScope.$broadcast('sockConnected', frame);
	});
	console.log(stompClient)
	return {

		stompClient : stompClient
	};
});