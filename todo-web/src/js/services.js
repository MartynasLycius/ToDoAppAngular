var todoServices = angular.module('todoServices', [])

todoServices.factory('TodoService', ['$http', '$q', function ($http, $q) {

    var REST_SERVICE_URI = 'http://localhost:8089/todo-service/resources/items/';

    var factory = {
        fetchAllTodos: fetchAllTodos,
        createTodo: createTodo,
        updateTodo: updateTodo,
        deleteTodo: deleteTodo
    };
    return factory;

    function fetchAllTodos() {
        
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI)
            .then(
                function (response) {
                    //console.log("response.data: "+response.data);
                    deferred.resolve(response.data);
                },
                function (errResponse) {
                    console.error('Error while fetching Todos');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

    function createTodo(todo) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI, todo)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function (errResponse) {
                    console.error('Error while creating User');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

    function updateTodo(todo, id) {
        var deferred = $q.defer();
        $http.put(REST_SERVICE_URI + id, todo)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function (errResponse) {
                    console.error('Error while updating User');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

    function deleteTodo(id) {
        var deferred = $q.defer();
        $http.delete(REST_SERVICE_URI + id)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function (errResponse) {
                    console.error('Error while deleting User');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

}]);

// var baseUrl = 'http://localhost:8089/todo-service/resources';

// var todoServices = angular.module('todoServices', [])

// todoServices.service('ItemResource', ['$http', function($http) {
//     // Service logic and data retrieval
//     console.log("todoServices::ItemResource");
//     this.fetchData = function() {
//       return $http.get(baseUrl + '/items').then(function(response) {
//         return response.data;
//       }).catch(function(error) {
//         throw error;
//       });
//     };
//   }]);

// todoServices.factory('TodoResource', ['$resource', function ($resource) {
//     return $resource(baseUrl + '/items');
// }]);

// // services.service('ItemResource', function(MathService) {
// //     this.square = function(a) {
// //        return MathService.multiply(a,a);
// //     }
// //  });

// // services.factory('TodoResource', ['$resource', function ($resource) {
// //     return $resource(baseUrl + '/items');
// // }]);

// // services.factory('TodoResource', ['$resource', function ($resource) {
// //     return $resource(baseUrl + '/items/:id', {id: '@id'}, {'update': {method: 'PUT'}});
// // }]);