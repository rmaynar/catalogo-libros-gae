'use strict';

angular.module('app')
    .controller('detalleCtrl', function ($scope, $routeParams, libreriaService) {
    	
    	$scope.cargarLibro = function() {
    		libreriaService.cargarLibro($routeParams.id, function (detail) {
                $scope.formulario = detail.data;
            });
        }
    	
       $scope.cargarLibro();
    });
