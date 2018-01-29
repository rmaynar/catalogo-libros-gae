'use strict';

angular.module('appLibreria')
    .controller('libreriaCtrl', function ($scope, libreriaService) {

        $scope.cargar = function() {
        	libreriaService.cargar(function (listado) {
                $scope.listadoNombreLibros = listado.data;
            });
        }

        $scope.guardar = function() {
        	libreriaService.guardar($scope.formulario, function() {
                $scope.cargar();
            });
        }

        $scope.formulario = {};

        $scope.cargar();
    });
