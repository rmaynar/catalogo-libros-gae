'use strict';
angular
    .module('appLibreria', ['ngRoute'])
    .config(function ($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'views/listadoLibros.html',
                controller: 'libreriaCtrl',
                controllerAs: 'libreria'
            })
            .when('/:id', {
                templateUrl: 'views/detalle.html',
                controller: 'DetailCtrl',
                controllerAs: 'detail'
            })
            .otherwise({
                redirectTo: '/'
            });
    });
