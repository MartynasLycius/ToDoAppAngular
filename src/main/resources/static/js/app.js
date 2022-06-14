angular.module('ToDoApp', ['ui.router','ui.bootstrap',,'ngResource'])
    .run(function (AuthService, $rootScope, $state) {
        $rootScope.$on('$stateChangeStart', function (event, toState, toParams, fromState, fromParams) {
            if (!AuthService.getUser()) {
                console.log('going to login page')
                if (toState.name != 'login') {
                    event.preventDefault();
                    $state.go('login');
                }
            } else {
                if (toState.data && toState.data.role) {
                    var hasAccess = false;
                    for (var i = 0; i < AuthService.user.roles.length; i++) {
                        var role = AuthService.user.roles[i];
                        if (toState.data.role == role) {
                            hasAccess = true;
                            break;
                        }
                    }
                    if (!hasAccess) {
                        event.preventDefault();
                        $state.go('access-denied');
                    }

                }
            }
        });
        $rootScope.setSuccessMessage = function (message) {
            $rootScope.closeAlert();
            $('.message.success').show();
            localStorage.setItem('globalSuccessMessage',message);
            $rootScope.globalSuccessMessage= localStorage.getItem('globalSuccessMessage');
            setTimeout(function() { $(".message.success").hide(); }, 2000);
        };
        $rootScope.closeAlert = function() {
            $('.message.error, .message.success, .message.warning').hide();
            $rootScope.globalErrorMessage = '';
            $rootScope.globalSuccessMessage = '';
            $rootScope.globalWarningMessage = '';
        }
        $rootScope.setErrorMessage = function (message) {
            $rootScope.closeAlert();
            $('.message.error').show();
            localStorage.setItem('globalErrorMessage', message);
            $rootScope.globalErrorMessage= localStorage.getItem('globalErrorMessage');
            setTimeout(function() { $(".message.error").hide(); }, 2000);
        }
    })
    .config(function ($stateProvider, $urlRouterProvider, $httpProvider) {
    // uncomment below to make alerts look like toast

    $urlRouterProvider.otherwise('/');
        $stateProvider.state('nav', {
            abstract: true,
            url: '',
            views: {
                'nav@': {
                    templateUrl: './views/nav.html',
                    controller: 'NavController'
                }
            }
        });
    // $httpProvider.interceptors.push('authExpiredInterceptor');
    $httpProvider.interceptors.push('notificationInterceptor');

});