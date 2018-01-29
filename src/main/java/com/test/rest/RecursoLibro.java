package com.test.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.test.dao.LibroDAO;
import com.test.data.Libro;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
/**
 * @author Ra�l Maynar Selgas
 */


@Path("/libreria")
@Produces("application/json;charset=utf-8")
@Api(value = "libreria", description = "Api Rest de gestión de libros de una libería")
public class RecursoLibro {

    private LibroDAO libroDAO;

    public RecursoLibro() {
        this.libroDAO = new LibroDAO();
    }

    @GET
    @ApiOperation("Obtiene el listado de todos los libros")
    public Response recuperarTodo() {
        return Response.ok(this.libroDAO.list()).build();
    }

    @GET
    @Path("/{id}")
    @ApiOperation("Obtiene el libro con el id pasado como parámetro")
    public Response recuperar(@PathParam("id") Long id) {
    	Libro libro = this.libroDAO.get(id);
        if (libro == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(libroDAO).build();
    }

    @POST
    @Consumes("application/json;charset=utf-8")
    @ApiOperation("Almacena el libro pasado como parámetro")
    public Response salvar(Libro libro) {
        this.libroDAO.save(libro);
        return Response.ok().build(); //201
    }

    @DELETE
    @Path("/{id}")
    @ApiOperation("Elimina el libro con el id pasado por parámetro")
    public Response borrar(@PathParam("id") Long id) {
    	Libro libro = this.libroDAO.get(id);
        if (libro == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        this.libroDAO.delete(libro);
        return Response.ok().build(); //204
    }
}
