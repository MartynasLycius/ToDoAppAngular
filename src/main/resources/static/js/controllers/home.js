angular.module('ToDoApp')
    .controller('HomeController', function ($http, $scope, AuthService,TodoItem) {
        $scope.user = AuthService.user;
        TodoItem.get({},function (data){
            console.log(data)
        })
    });