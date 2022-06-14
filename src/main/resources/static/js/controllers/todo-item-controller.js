angular.module('ToDoApp')
    .controller('TodoItemController', function ($http, $scope, $rootScope, $state, AuthService,TodoItem) {
        $scope.todoItems = [];
        $scope.currentPage = 1;
        $scope.numPerPage = 0;
        $scope.maxSize = 0;
        $scope.totalItems = 0;
        $scope.itemsPerPage = 5;
        TodoItem.get(function (data) {
            console.log(data)
            $scope.currentPage = data.number+1;
            $scope.maxSize = data.size
            $scope.totalItems = data.totalElements
            $scope.todoItems = data.content
            $scope.filesSerial=0

        })
        $scope.pageChanged = function() {
            TodoItem.get({pageNumber:$scope.currentPage},function (data) {
                console.log(data);
                $scope.filesSerial = data.number * data.size;
                $scope.files = data.content;
            })
        };
        $scope.delete = function(toDoItem) {
            if (confirm("Are you sure, you want to delete task: "+toDoItem.title)) {
                TodoItem.delete({id: toDoItem.id}, function (data) {
                    console.log(data);
                    if(data) {
                        $rootScope.setSuccessMessage('Item Deleted Successfully');
                        $state.go('home',null, {reload: true});
                    }

                })
            }
        };
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
    })
    .controller('TodoItemViewController', function ($http, $scope, $rootScope, $state, $stateParams,TodoItem) {
        $scope.todoItem = {};
        console.log($stateParams)
        if($stateParams.id) {
            TodoItem.get({id: $stateParams.id}, function (response) {
                $scope.todoItem = response.data
                console.log(response.data)

            })
        }
    });