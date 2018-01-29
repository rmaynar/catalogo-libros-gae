package com.test.dao;

import com.googlecode.objectify.ObjectifyService;
import com.test.data.Libro;

import java.util.List;
import java.util.logging.Logger;

/**
 * Capa de acceso a datos
 * 
 * @author Ra�l Maynar Selgas
 */
public class LibroDAO {

    private static final Logger LOGGER = Logger.getLogger(LibroDAO.class.getName());

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
        return ObjectifyService.ofy().load().type(Libro.class).id(id).now();
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
    }
}
