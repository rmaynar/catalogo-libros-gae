'use strict';
angular
    .module('test', ['ngRoute'])
    .config(function ($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'views/list.html',
                controller: 'ListCtrl',
                controllerAs: 'list'
            })
            .when('/:id', {
                templateUrl: 'views/detail.html',
                controller: 'DetailCtrl',
                controllerAs: 'detail'
            })
            .otherwise({
                redirectTo: '/'
            });
    });
