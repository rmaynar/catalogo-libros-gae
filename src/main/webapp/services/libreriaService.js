'use strict';

angular.module('app')
    .service('libreriaService', function ($http) {
        return {
            cargar: function (success) {
                return $http.get("/rest/libreria").then(success);
            },
            cargarLibro: function (id,success) {
            	return $http.get("/rest/libreria/"+id).then(success);
            },
            guardar: function (libreria, success) {
                return $http.post("/rest/libreria", libreria).then(success);
            },
            eliminar: function (id, success) {
            	return $http.delete("/rest/libreria/"+ id).then(success);
            },
            buscar: function (text, success) {
                return $http.get("/rest/libreria/buscar/" + text).then(success);
            }
        };
    });
