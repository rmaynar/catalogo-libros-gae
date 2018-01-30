'use strict';

angular.module('app')
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
        
        $scope.eliminar = function(id) {
        	libreriaService.eliminar(id, function() {
                $scope.cargar();
            });
        }
        
        $scope.buscar = function() {
            if (!$scope.text) {
                $scope.clear();
                return;
            }

            libreriaService.buscar($scope.text, function(list) {
                $scope.listadoNombreLibros = list.data;
            });
        }

        $scope.clear = function() {
            $scope.text = undefined;
            $scope.cargar();
        }
        
        $scope.formulario = {};

        $scope.cargar();
    });
