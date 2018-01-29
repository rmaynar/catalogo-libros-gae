'use strict';

angular.module('appLibreria')
    .service('libreriaService', function ($http) {
        return {
            cargar: function (success) {
                return $http.get("/rest/libreria").then(success);
            },
            guardar: function (libreria, success) {
                return $http.post("/rest/libreria", libreria).then(success);
            }
        };
    });
