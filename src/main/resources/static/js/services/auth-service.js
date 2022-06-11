angular.module('ToDoApp')
    .service('AuthService', [
        "$window","$http",
        function ($window, $http) {
            var user;
            return {
                getUser: getUser,
                setUser: setUser
            };
            function getUser() {
                if (!user) {
                    user = angular.fromJson($window.localStorage.getItem('user'));
                    if(user) {
                        $http.defaults.headers.common['Authorization'] = user.type + ' ' + user.token;
                    }
                }
                return user;
            }
            function setUser(userInfo) {
                user = userInfo;
                console.log(user)
                $window.localStorage.setItem('user', angular.toJson(userInfo));
                if(user) {
                    $http.defaults.headers.common['Authorization'] = user.type + ' ' + user.token;
                }

            }

        }
    ]);