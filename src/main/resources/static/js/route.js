angular.module('ToDoApp').config(function ($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise('/page-not-found');
    $stateProvider.state('nav', {
        abstract: true,
        url: '',
        views: {
            'nav@': {
                templateUrl: './views/nav.html',
                controller: 'NavController'
            }
        }
    }).state('login', {
        parent: 'nav',
        url: '/login',
        views: {
            'content@': {
                templateUrl: './views/login.html',
                controller: 'LoginController'
            }
        }
    }).state('home', {
        parent: 'nav',
        url: '/',
        views: {
            'content@': {
                templateUrl: './views/todo-item.html',
                controller: 'TodoItemController'
            }
        }
    }).state('todo-item-add', {
        parent: 'nav',
        url: '/todo-item-add/{id}',
        views: {
            'content@': {
                templateUrl: './views/todo-item-add.html',
                controller: 'TodoItemAddController'
            }
        }
    }).state('page-not-found', {
        parent: 'nav',
        url: '/page-not-found',
        views: {
            'content@': {
                templateUrl: './views/page-not-found.html',
                controller: 'PageNotFoundController'
            }
        }
    }).state('access-denied', {
        parent: 'nav',
        url: '/access-denied',
        views: {
            'content@': {
                templateUrl: './views/access-denied.html',
                controller: 'AccessDeniedController'
            }
        }
    });
});