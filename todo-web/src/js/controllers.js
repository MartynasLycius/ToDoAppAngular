var todoControllers = angular.module('todoControllers', []);

todoControllers.controller('TodoController', ['$scope', '$location', 'TodoService', function ($scope, $location, TodoService) {

    $scope.todo = {};
    $scope.todos = [];
    $scope.newTodo = {"name":"", "description":"", "date":""};
    //$scope.editingTodo = {}; 
      

    fetchAllTodos();

    function fetchAllTodos() {
        
        TodoService.fetchAllTodos()
            .then(
                function (d) {
                    $scope.todos = d;
                },
                function (errResponse) {
                    console.error('Error while fetching Todos');
                }
            );
    }
    
    $scope.submitForm = function() {
        TodoService.createTodo($scope.newTodo)
            .then(
                fetchAllTodos,
                function (errResponse) {
                    console.error('Error while creating Todo');
                }
            );
        $location.path('/todo-list');         
    };

    function updateTodo(todo, id) {
        TodoService.updateUser(todo, id)
            .then(
                fetchAllTodos,
                function (errResponse) {
                    console.error('Error while updating User');
                }
            );
    }

    function deleteTodo(id) {
        TodoService.deleteUser(id)
            .then(
                fetchAllTodos,
                function (errResponse) {
                    console.error('Error while deleting User');
                }
            );
    }
    
    $scope.edit = function(todoForEdit) {
        //var edit_form = {};
        //angular.copy(todoForEdit, edit_form);
        //$scope.editingTodo = edit_form;
        //angular.copy(todoForEdit, $scope.editingTodo);
        $scope.editingTodo = angular.copy(todoForEdit);;
        console.log({$scope_editingTodo: $scope.editingTodo})
        $location.path('/edit-todo');
    };

    $scope.cancelEdit = function() {
        delete $scope.editingTodo;
        $location.path('/todo-list');
    };

    function remove(id) {
        console.log('id to be deleted', id);
        if (self.todo.id === id) {//clean form if the user to be deleted is shown there.
            reset();
        }
        deleteTodo(id);
    }

    function reset() {
        self.todo = {id: null, title: '', value: ''};
        $scope.myForm.$setPristine(); //reset Form
    }

}]);


// var todoControllers = angular.module('todoControllers', []);

// todoControllers.controller('TodoController', ['$scope', 'TodoService', function($scope, TodoService) {
//     // Controller logic and data binding
//     //$scope.message = 'Hello, AngularJS!';
    
//     // Call a method from the service
//     TodoService.fetchData().then(function(data) {
//       $scope.data = data;
//     }).catch(function(error) {
//       console.log('Error:', error);
//     });
//   }]);

// // todoControllers.controller('todoListCtrl', function ($scope, $http) {    
// //     $http({    
// //         method: 'GET',    
// //         url: '/sampleUrl'    
// //     }).then(function success(response) {    
// //     // this function will be called when the request is success    
// //     }, function error(response) {    
// //     // this function will be called when the request returned error status    
// //     });    
// // });

// // controllers.controller('ListCtrl', ['$scope', 'TodoResource', '$routeParams', '$location', '$route',
// //     function ($scope, TodoResource, $routeParams, $location, $route) {

// //         $scope.rows = TodoResource.query();

// //         $scope.delete = function (row) {
// //             row.$delete().then(function () {
// //                 $route.reload();
// //             })
// //         }

// //     }]);

// // controllers.controller('CreateCtrl', ['$scope', 'TodoResource', '$routeParams', '$location', '$route',
// //     function ($scope, TodoResource, $routeParams, $location, $route) {

// //         $scope.row = new TodoResource();

// //         $scope.submit = function () {
// //             $scope.row.$save().then(function () {
// //                 $location.path("/todo-list");
// //             });
// //         }

// //     }]);

// // controllers.controller('EditCtrl', ['$scope', 'TodoResource', '$routeParams', '$location', '$route',
// //     function ($scope, TodoResource, $routeParams, $location, $route) {

// //         $scope.row = TodoResource.get({id: $routeParams.id})

// //         $scope.submit = function () {
// //             $scope.row.$update().then(function () {
// //                 $location.path("/todo-list");
// //             });
// //         }

// //     }]);
// // ;