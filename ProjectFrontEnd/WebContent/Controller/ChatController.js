/**
 * ChatController
 */

app.controller("ChatController", [
		'$rootScope',
		'$scope',
		'socket',
		function($rootScope, $scope, socket) {
			if ($rootScope.username.user_Name == undefined) {
				alert($rootScope.username.user_Name)
				alert("Please Login to continue")
				$location.path("/login")
			} else {
				$scope.chats = [];
				$scope.stompClient = socket.stompClient;
				$scope.users = [];
				$scope.$on('sockConnected', function(event, frame) {
					alert("entering chat")
					$scope.chats = [];
					$scope.stompClient = socket.stompClient;
					$scope.users = []
				})
				$scope.$on('sockConnected', function(event, frame) {
					alert('sockconnected')
					$scope.userName = $rootScope.username.user_Name;
					console.log($scope.userName)
					$scope.stompClient.subscribe(
							'/app/join/' + $scope.userName, function(message) {
								console.log(message)
								$scope.users = JSON.parse(message.body);
								$scope.$apply();
							})
					$scope.stompClient.subscribe("/topic/join", function(
							message) {
						console.log(message.body)
						user = message.body;
						console.log(user)

						if (user != $scope.userName
								&& $.inArray(user, $scope.users) == -1) {
							$scope.addUser(user);
							$scope.latestUser = user;
							$scope.$apply();
							$('#joinedChat').fadeIn(500).delay(2000).fadeOut(
									500);
						}
					})

				})
				$scope.sendMessage = function(chat) {
					chat.from = $scope.userName;

					$scope.stompClient.send('/app/chat', {}, JSON
							.stringify(chat));
					console.log(chat)
					$rootScope.$broadcast('sendingChat', chat);
					$scope.chat.message = "";
				}
				$scope.addUser = function(user) {
					$scope.users.push(user);
					$scope.$apply();
				}
				$scope.$on('sockConnected', function(event, frame) {
					console.log("queue")
					$scope.userName = $rootScope.username.user_Name;
					console.log($scope.userName)
					$scope.user = $rootScope.username.user_Name;
					console.log($scope.user)
					$scope.stompClient.subscribe("/queue/chats/"
							+ $scope.userName, function(message) {
						console.log(message)
						$scope.processIncomingMessage(message, false);
					})
					$scope.stompClient.subscribe("/queue/chats", function(
							message) {
						console.log(message)
						$scope.processIncomingMessage(message, true);
					})
				})
				$scope.$on('sendingChat', function(event, sentChat) {
					chat = angular.copy(sentChat);
					chat.from = 'Me';
					chat.direction = 'outgoing';
					$scope.addChat(chat);
				})
				$scope.processIncomingMessage = function(message, isBroadcast) {
					console.log(message)
					message = JSON.parse(message.body);
					message.direction = 'incoming';
					message.broadcast = isBroadcast
					console.log(message)
					console.log($scope.userName)
					console.log(message.from)
					if (message.from != $scope.userName) {
						$scope.addChat(message);
						$scope.$apply(); // since inside subscribe closure
					}
				}
				$scope.addChat = function(chat) {
					$scope.chats.push(chat);
				};
			}
		} ])