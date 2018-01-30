'use strict';
angular
    .module('app', ['ngRoute'])
    .config(function ($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'views/listado.html',
                controller: 'libreriaCtrl',
                controllerAs: 'libreria'
            })
            .when('/:id', {
                templateUrl: 'views/detalle.html',
                controller: 'detalleCtrl',
                controllerAs: 'detail'
            })
            .otherwise({
                redirectTo: '/'
            });
    });
