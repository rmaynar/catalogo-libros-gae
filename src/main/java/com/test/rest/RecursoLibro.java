package com.test.rest;

import java.util.logging.Logger;

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
import com.wordnik.swagger.annotations.ApiParam;
/**
 * @author Raúl Maynar Selgas
 */


@Path("/libreria")
@Produces("application/json;charset=utf-8")
@Api(value = "libreria", description = "Api librería")
public class RecursoLibro {
	
	private static final Logger LOGGER = Logger.getLogger(RecursoLibro.class.getName());
	
    private LibroDAO libroDAO;

    public RecursoLibro() {
        this.libroDAO = new LibroDAO();
    }

    @GET
    @ApiOperation("Obtiene el listado de todos los libros")
    public Response recuperarTodo() {
    	LOGGER.info("GET - recuperarTodo");
        return Response.ok(this.libroDAO.list()).build();
    }

    @GET
    @Path("/{id}")
    @ApiOperation("Obtiene el libro con el id pasado como parámetro")
    public Response recuperar(@ApiParam("ID libro") @PathParam("id") Long id) {
    	LOGGER.info("GET/{id} - recuperar por id");
    	Libro libro = this.libroDAO.get(id);
        if (libro == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(libro).build();
    }
    
    @GET
    @Path("/buscar/{text}")
    @ApiOperation("Busca un libro en función del texto de la búsqueda")
    public Response buscar(@ApiParam("texto de la búsqueda") @PathParam("text") String text) {
    	LOGGER.info("GET - buscar");
        return Response.ok(this.libroDAO.buscar(text)).build();
    }

    @POST
    @Consumes("application/json;charset=utf-8")
    @ApiOperation("Guarda el libro pasado como parámetro")
    public Response salvar(Libro libro) {
        this.libroDAO.save(libro);
        return Response.ok().build(); //201
    }

    @DELETE
    @Path("/{id}")
    @ApiOperation("Elimina el libro cuyo id es pasado como parámetro")
    public Response borrar(@PathParam("id") Long id) {
    	Libro libro = this.libroDAO.get(id);
        if (libro == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        this.libroDAO.delete(libro);
        return Response.ok().build(); //204
    }
}
