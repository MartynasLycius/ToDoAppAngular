'use strict';

angular.module('ToDoApp')
    .factory('TodoItem', function ($http, $resource, AuthService) {
            return $resource('./api/to-do-item:id', {}, {
            'get'  : { method: 'GET',headers: {'Content-Type': 'application/json'}, params: {}, format: 'json',
                transformResponse: [function (data, headersGetter) {
                    return data;
                }].concat($http.defaults.transformResponse)},
            'save'  : { method: 'POST',  headers: {'Content-Type': 'application/json'}, params: {}, format: 'json',
                transformResponse: [function (data, headersGetter) {
                    return data;
                }].concat($http.defaults.transformResponse)},
            'update'  : { method: 'PUT', params: {}, format: 'json',
                transformResponse: [function (data, headersGetter) {
                    return data;
                }].concat($http.defaults.transformResponse)},
            'delete'  : { method: 'DELETE', params: {}, format: 'json',
                transformResponse: [function (data, headersGetter) {
                    return data;
                }].concat($http.defaults.transformResponse)}
        });
    });


