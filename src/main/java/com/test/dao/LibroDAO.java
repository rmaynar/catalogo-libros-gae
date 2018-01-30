package com.test.dao;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import com.googlecode.objectify.ObjectifyService;
import com.test.data.Libro;

/**
 * Capa de acceso a datos
 * 
 * @author Raúl Maynar Selgas
 */
public class LibroDAO {

	private ServicioBusqueda busquedaService;
	
    private static final Logger LOGGER = Logger.getLogger(LibroDAO.class.getName());

    
    

	public LibroDAO() {
		busquedaService = new ServicioBusqueda();
	}

	/**
     * @return lista de objetos libro
     */
    public List<Libro> list() {
        LOGGER.info("Obteniendo lista de libros");
        return ObjectifyService.ofy().load().type(Libro.class).list();
    }

    /**
     * @param id
     * @return objeto bean con identificador único
     */
    public Libro get(Long id) {
        LOGGER.info("Obteniendo libro con id " + id);
        Libro libro = ObjectifyService.ofy().load().type(Libro.class).id(id).now();
        LOGGER.info("Libro obtenido--- id " + libro.getId() + " nombre " + libro.getNombre());
        return libro;
    }

    /**
     * @param text
     * @return objeto bean con identificador único
     */
    public Collection<Libro> buscar(String text) {
    	if (text == null) {
            throw new IllegalArgumentException("texto de búsqueda nulo");
        }

        LOGGER.info("Se busca libro que contiene texto: " + text);

        return ObjectifyService.ofy().load().type(Libro.class).ids(busquedaService.buscar(text)).values();
    }
    
    /**
     * Almacena el libro pasado por parámetro
     * @param bean
     */
    public void save(Libro bean) {
        if (bean == null) {
            throw new IllegalArgumentException("Recuperado objeto libro nulo");
        }
        LOGGER.info("Almacenando libro con id " + bean.getId());
        ObjectifyService.ofy().save().entity(bean).now();
        //indexamos
        busquedaService.indexar(bean);
    }

    /**
     * Borra el libro pasado por parámetro
     * @param bean
     */
    public void delete(Libro bean) {
        if (bean == null) {
            throw new IllegalArgumentException("Recuperado objeto libro nulo");
        }
        LOGGER.info("Borrando libro con id " + bean.getId());
        ObjectifyService.ofy().delete().entity(bean);
        //quitamos el index
      //indexamos
        busquedaService.desindexar(bean);
    }
}
