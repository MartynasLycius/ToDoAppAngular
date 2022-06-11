angular.module('ToDoApp')
    .controller('TodoItemController', function ($http, $scope, AuthService,TodoItem) {
        $scope.todoItems = [];
        TodoItem.get({},function (data){
            console.log(data)
            $scope.todoItems = data.content

        })
    })
    .controller('TodoItemAddController', function ($http, $scope, $rootScope, $state, $stateParams,TodoItem) {
        $scope.todoItem = {};
        console.log($stateParams)
        if($stateParams.id) {
            TodoItem.get({id: $stateParams.id}, function (response) {
                $scope.todoItem = response.data
                console.log(response.data)

            })
        }
        var onSaveSuccess = function (result) {
            $rootScope.setSuccessMessage('Save Successful');
            $scope.serial = parseFloat($scope.serial)+1;
            $state.go('home');
        };
        var onSaveError = function (result) {
            console.log(result)
            $rootScope.setErrorMessage("Problem occurred in saving. Please see system log for details");
            $state.go('home');
            //$scope.isSaving = false;
        };
        $scope.save = function () {
            console.log($scope.todoItem)
            TodoItem.save($scope.todoItem, onSaveSuccess, onSaveError)

        }
    });