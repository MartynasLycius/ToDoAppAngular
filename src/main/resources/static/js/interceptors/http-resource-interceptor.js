angular.module('ToDoApp').service('HttpResourceInterceptor', ['$cookies', function(AuthService) {
    var token = AuthService.type+ AuthService.token
        service = this;
    service.request = function(config) {
        config.headers['Authorization'] = token;
        return config;
    };
}]);
//
// angular.module('ToDoApp').config([ '$httpProvider',   function($httpProvider) {
//     $httpProvider.interceptors.push('HttpResourceInterceptor');
// }]);