angular.module('ToDoApp')
    .controller('LoginController', function ($http, $scope, $state, $window, AuthService, $rootScope) {
        $scope.login = function () {
            // requesting the token by usename and passoword
            $http.post('./api/sign-in', {username: $scope.username, password: $scope.password}).success(function (res) {
                $scope.password = null;
                // checking if the token is available in the response
                console.log(res)
                if (res.data) {
                    $scope.message = '';
                    // setting the Authorization Bearer token with JWT token
                    // $http.defaults.headers.common['Authorization'] = res.data.type+' '+ res.data.token;
                    // setting the user in AuthService
                    AuthService.setUser(res.data);
                    // $window.localStorage.setItem('authUser', angular.toJson(AuthService.user));
                    $rootScope.$broadcast('LoginSuccessful');
                    // going to the home page
                    $state.go('home');
                } else {
                    // if the token is not present in the response then the
                    // authentication was not successful. Setting the error message.
                    $scope.message = 'Authentication Failed! Please Try Again.';
                }
            }).error(function (error) {
                console.log(error)
                // if authentication was not successful. Setting the error message.
                $scope.message = 'Authentication Failed! Please Try Again.';
            });
        };
    });