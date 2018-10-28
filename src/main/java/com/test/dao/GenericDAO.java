package com.test.dao;

import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.api.datastore.DatastoreTimeoutException;
import com.google.appengine.api.datastore.QueryResultIterator;
import com.google.common.collect.Lists;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.SaveException;
import com.googlecode.objectify.cmd.Query;
import com.googlecode.objectify.cmd.QueryKeys;
import com.test.data.PageBean;
import com.test.data.PageKeyBean;
import com.test.utils.Constants;
import com.test.utils.Utilidades;

/**
 * Implementación general de un DAO
 */
public abstract class GenericDAO<T> {
    private static final int MULTIPLO = 5000;
    public static final int MAX_ATTEMPTS = 4;
    public static final int INI_ATTEMPT = 1;
    public static final String LOG_ERROR_DS = "Error en el acceso a DS. Superado el número de intentos máximo ";
    public static final String LOG_ERROR_DS_ATTEMPT = "Reintentando .... Error en el acceso a DS. Intento ";
    private final Class<T> dataClass;
    private static final Logger LOGGER = Logger
            .getLogger(GenericDAO.class.getName());

    @SuppressWarnings("unchecked")
    public GenericDAO() {
        this.dataClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * @param id
     * @return Objeto o null
     */
    public T get(String id) {
        return ObjectifyService.ofy().load().type(this.dataClass).id(id).now();
    }

    /**
     * @param id
     * @return Objeto o null
     */
    public T get(long id) {
        return ObjectifyService.ofy().load().type(this.dataClass).id(id).now();
    }

    /**
     * @param ids
     * @return Objetos recuperados junto con su clave
     */
    public Map<String, T> getByStringIds(Collection<String> ids) {
        int attempt = INI_ATTEMPT;
        do {
            try {
                return ObjectifyService.ofy().load().type(this.dataClass)
                        .ids(ids);
            } catch (DatastoreTimeoutException e) {
                LOGGER.severe(LOG_ERROR_DS_ATTEMPT + attempt);
                if (attempt == MAX_ATTEMPTS) {
                    Utilidades.printException(e.getMessage(), e);
                    throw e;
                }
                attempt = reintento(attempt);
            }
        } while (attempt <= MAX_ATTEMPTS);
        LOGGER.severe(LOG_ERROR_DS + attempt);

        return null;
    }

    public Map<Long, T> getByLongIds(Collection<Long> ids) {
        int attempt = INI_ATTEMPT;
        do {
            try {
                return ObjectifyService.ofy().load().type(this.dataClass)
                        .ids(ids);
            } catch (DatastoreTimeoutException e) {
                LOGGER.severe(LOG_ERROR_DS_ATTEMPT + attempt);
                if (attempt == MAX_ATTEMPTS) {
                    Utilidades.printException(e.getMessage(), e);
                    throw e;
                }
                attempt = reintento(attempt);
            }
        } while (attempt <= MAX_ATTEMPTS);
        LOGGER.severe(LOG_ERROR_DS + attempt);

        return null;
    }

    /**
     * Recupera todos los registros
     *
     * @return
     */
    public List<T> getAll() {
        int attempt = INI_ATTEMPT;
        do {
            try {
                List<T> lst = Lists.newArrayList();
                Cursor cursor = null;
                Query<T> query;
                int bloqueLecturaDS = 500;
                do {
                    if (cursor == null) {
                        query = ObjectifyService.ofy().load()
                                .type(this.dataClass).limit(bloqueLecturaDS);
                    } else {
                        query = ObjectifyService.ofy().load()
                                .type(this.dataClass).limit(bloqueLecturaDS)
                                .startAt(cursor);
                    }
                    QueryResultIterator<T> iterator = query.iterator();
                    lst.addAll(filterNull(iterator));
                    cursor = iterator.getCursor();
                } while (ObjectifyService.ofy().load().type(this.dataClass)
                        .limit(1).startAt(cursor).iterator().hasNext());
                return lst;
            } catch (DatastoreTimeoutException e) {
                LOGGER.severe(LOG_ERROR_DS_ATTEMPT + attempt);
                if (attempt == MAX_ATTEMPTS) {
                    Utilidades.printException(e.getMessage(), e);
                    throw e;
                }
                attempt = reintento(attempt);
            }
        } while (attempt <= MAX_ATTEMPTS);
        LOGGER.severe(LOG_ERROR_DS + attempt);
        // return ObjectifyService.ofy().load().type(this.dataClass).list();
        return Collections.emptyList();

    }

    /**
     * Recupera los registros filtrados por unos parámetros
     *
     * @param filters
     * @return
     */
    public List<T> listFilter(Map<String, Object> filters) {
        int attempt = INI_ATTEMPT;
        do {
            try {
                Query<T> query = ObjectifyService.ofy().load()
                        .type(this.dataClass);
                return aplicaFiltros(filters, query).list();
            } catch (DatastoreTimeoutException e) {
                LOGGER.severe(LOG_ERROR_DS_ATTEMPT + attempt);
                if (attempt == MAX_ATTEMPTS) {
                    Utilidades.printException(e.getMessage(), e);
                    throw e;
                }
                attempt = reintento(attempt);
            }
        } while (attempt <= MAX_ATTEMPTS);
        LOGGER.severe(LOG_ERROR_DS + attempt);
        return Collections.emptyList();

    }

    /**
     * Recupera una página mediante cursor con filtro
     *
     * @param cursor
     * @param itemsPerPage
     * @param filters
     * @return
     */
    public PageBean<T> pagedOrderFilter(Cursor cursor, int itemsPerPage,
            Map<String, Object> filters, List<String> orders) {
        int attempt = INI_ATTEMPT;
        do {
            try {
                Query<T> query = null;
                if (cursor != null) {
                    query = aplicaFiltros(filters,
                            ObjectifyService.ofy().load().type(this.dataClass))
                                    .limit(itemsPerPage).startAt(cursor);
                } else {
                    query = aplicaFiltros(filters,
                            ObjectifyService.ofy().load().type(this.dataClass))
                                    .limit(itemsPerPage);
                }
                if (orders != null && !orders.isEmpty()) {
                    query = applicaOrden(query, orders);
                }
                return getPage(query, filters, orders);
            } catch (DatastoreTimeoutException e) {
                LOGGER.severe(LOG_ERROR_DS_ATTEMPT + attempt);
                if (attempt == MAX_ATTEMPTS) {
                    Utilidades.printException(e.getMessage(), e);
                    throw e;
                }
                attempt = reintento(attempt);
            }
        } while (attempt <= MAX_ATTEMPTS);
        LOGGER.severe(LOG_ERROR_DS + attempt);
        return null;
    }

    public PageBean<T> pagedOrder(Cursor cursor, int itemsPerPage,
            List<String> orders) {
        int attempt = INI_ATTEMPT;
        do {
            try {
                Query<T> query = null;
                if (cursor != null) {
                    query = ObjectifyService.ofy().load().type(this.dataClass)
                            .limit(itemsPerPage).startAt(cursor);
                } else {
                    query = ObjectifyService.ofy().load().type(this.dataClass)
                            .limit(itemsPerPage);
                }
                if (orders != null && !orders.isEmpty()) {
                    query = applicaOrden(query, orders);
                }
                return getPage(query, orders);
            } catch (DatastoreTimeoutException e) {
                LOGGER.severe(LOG_ERROR_DS_ATTEMPT + attempt);
                if (attempt == MAX_ATTEMPTS) {
                    Utilidades.printException(e.getMessage(), e);
                    throw e;
                }
                attempt = reintento(attempt);
            }
        } while (attempt <= MAX_ATTEMPTS);
        LOGGER.severe(LOG_ERROR_DS + attempt);
        return null;
    }

    public void deleteEntidades() {
        List<Key<T>> lstIt = Lists.newArrayList();
        for (QueryResultIterator<Key<T>> it = ObjectifyService.ofy().cache(true)
                .load().type(this.dataClass).keys().iterator(); it.hasNext();) {
            lstIt.add(it.next());
            if (lstIt.size() == Constants.NUM_ELEMENTOS_BORRAR_DS) {
                ObjectifyService.ofy().cache(true).delete().keys(lstIt);
                lstIt.clear();
            }
        }
        if (lstIt != null && !lstIt.isEmpty()) {
            ObjectifyService.ofy().cache(true).delete().keys(lstIt);
        }
    }

    public PageKeyBean<T> pagedOnlyKeys(Cursor cursor, int itemsPerPage) {
        int attempt = INI_ATTEMPT;
        do {
            try {
                QueryKeys<T> query = null;
                if (cursor != null) {
                    query = ObjectifyService.ofy().load().type(this.dataClass)
                            .limit(itemsPerPage).startAt(cursor).keys();
                } else {
                    query = ObjectifyService.ofy().load().type(this.dataClass)
                            .limit(itemsPerPage).keys();
                }

                return getPageKey(query);
            } catch (DatastoreTimeoutException e) {
                LOGGER.severe(LOG_ERROR_DS_ATTEMPT + attempt);
                if (attempt == MAX_ATTEMPTS) {
                    Utilidades.printException(e.getMessage(), e);
                    throw e;
                }
                attempt = reintento(attempt);
            }
        } while (attempt <= MAX_ATTEMPTS);
        LOGGER.severe(LOG_ERROR_DS + attempt);
        return null;
    }

    /**
     * @param bean
     * @return clave del objeto almacenado
     */
    public Key<T> save(T bean) {
        int attempt = INI_ATTEMPT;
        do {
            try {
                return ObjectifyService.ofy().save().entity(bean).now();
            } catch (DatastoreTimeoutException | SaveException e) {
                LOGGER.severe(LOG_ERROR_DS_ATTEMPT + attempt);
                if (attempt == MAX_ATTEMPTS) {
                    Utilidades.printException(e.getMessage(), e);
                    throw e;
                }
                attempt = reintento(attempt);
            }
        } while (attempt <= MAX_ATTEMPTS);
        LOGGER.severe(LOG_ERROR_DS + attempt);
        return null;
    }

    /**
     * @param beans
     * @return claves de cada objeto almacenado
     */
    public Map<Key<T>, T> saveAll(Collection<T> beans) throws SaveException {
        int attempt = INI_ATTEMPT;
        do {
            try {
                return ObjectifyService.ofy().save().entities(beans).now();
            } catch (DatastoreTimeoutException | SaveException e) {
                LOGGER.severe(LOG_ERROR_DS_ATTEMPT + attempt);
                if (attempt == MAX_ATTEMPTS) {
                    Utilidades.printException(e.getMessage(), e);
                    throw e;
                }
                attempt = reintento(attempt);
            }
        } while (attempt <= MAX_ATTEMPTS);
        LOGGER.severe(LOG_ERROR_DS + attempt);
        return null;
    }

    public Map<Key<T>, T> deleteAll(Collection<T> beans) throws SaveException {
        int attempt = INI_ATTEMPT;
        do {
            try {
                ObjectifyService.ofy().delete().entities(beans).now();
                attempt = MAX_ATTEMPTS + 1;
            } catch (DatastoreTimeoutException | SaveException e) {
                LOGGER.severe(LOG_ERROR_DS_ATTEMPT + attempt);
                if (attempt == MAX_ATTEMPTS) {
                    Utilidades.printException(e.getMessage(), e);
                    throw e;
                }
                attempt = reintento(attempt);
            }
        } while (attempt <= MAX_ATTEMPTS);
        LOGGER.severe(LOG_ERROR_DS + attempt);
        return null;
    }

    /**
     * @param id
     *            Clave del objeto a eliminar
     */
    public void delete(String id) {
        ObjectifyService.ofy().delete().type(this.dataClass).id(id).now();
    }

    /**
     * @param id
     *            Clave del objeto a eliminar
     */
    public void delete(long id) {
        ObjectifyService.ofy().delete().type(this.dataClass).id(id).now();
    }

    /**
     * @param ids
     *            Claves de los objetos a eliminar
     */
    public void deleteByStringIds(Collection<String> ids) {
        DeleteByIds(ids);
    }

    private boolean DeleteByIds(Collection<?> ids) {
        int attempt = INI_ATTEMPT;

        do {
            try {
                ObjectifyService.ofy().delete().type(this.dataClass).ids(ids);
                return true;
            } catch (DatastoreTimeoutException e) {
                LOGGER.severe(LOG_ERROR_DS_ATTEMPT + attempt);
                if (attempt == MAX_ATTEMPTS) {
                    Utilidades.printException(e.getMessage(), e);
                    throw e;
                }
                attempt = reintento(attempt);
            }
        } while (attempt <= MAX_ATTEMPTS);

        LOGGER.severe(LOG_ERROR_DS + attempt);
        return false;

    }

    /**
     * @param ids
     *            Claves de los objetos a eliminar
     */
    public void deleteByLongIds(Collection<Long> ids) {
        DeleteByIds(ids);
    }

    /**
     * Contador de elementos de una entidad .... si se recuperan los elementos
     * de una entidad vacía devuelve un error
     *
     * @param filters
     * @return
     */
    public int count(Map<String, Object> filters) {
        int attempt = INI_ATTEMPT;
        do {
            try {
                if (filters == null) {
                    return ObjectifyService.ofy().load().type(this.dataClass)
                            .count();
                } else {
                    Query<T> query = ObjectifyService.ofy().load()
                            .type(this.dataClass);
                    return aplicaFiltros(filters, query).count();
                }
            } catch (DatastoreTimeoutException e) {
                LOGGER.severe(LOG_ERROR_DS_ATTEMPT + attempt);
                if (attempt == MAX_ATTEMPTS) {
                    Utilidades.printException(e.getMessage(), e);
                    throw e;
                }
                attempt = reintento(attempt);
            }
        } while (attempt <= MAX_ATTEMPTS);
        LOGGER.severe(LOG_ERROR_DS + attempt);
        return 0;
    }

    /**
     * Espera 5, 10, 15 y 20 sg entre reintentos de ejecutar la qry en DS
     *
     * @param attempt
     * @return
     */
    private int reintento(int attempt) {
        LOGGER.warning("Timeout de DStore recuperando lista de DS. Intento "
                + attempt + " esperando " + attempt * MULTIPLO + "segundos");
        try {
            Thread.sleep(attempt * MULTIPLO);
        } catch (InterruptedException e1) {
            Utilidades.printException(e1.getMessage(), e1);
            Thread.currentThread().interrupt();
        }
        attempt++;
        return attempt;
    }

    /**
     * Obtiene la página a partir de la qry
     *
     * @param query
     * @param filters
     * @param orders
     * @return
     */
    private PageBean<T> getPage(Query<T> query, Map<String, Object> filters,
            List<String> orders) {
        QueryResultIterator<T> iterator = query.iterator();
        PageBean<T> pageBean = new PageBean<>();
        pageBean.setData(filterNull(iterator));
        Cursor next = iterator.getCursor();
        if (hasMorePages(next, filters, orders)) {
            pageBean.setNext(Utilidades.getStrCursorDS(next));
        }
        return pageBean;
    }

    private PageKeyBean<T> getPageKey(QueryKeys<T> query) {
        QueryResultIterator<Key<T>> iterator = query.iterator();
        PageKeyBean<T> pageBean = new PageKeyBean<>();
        pageBean.setData(filterNull(iterator));
        Cursor next = iterator.getCursor();
        if (hasMorePages(next, null)) {
            pageBean.setNext(Utilidades.getStrCursorDS(next));
        }
        return pageBean;
    }

    private PageBean<T> getPage(Query<T> query, List<String> orders) {
        QueryResultIterator<T> iterator = query.iterator();
        PageBean<T> pageBean = new PageBean<>();
        pageBean.setData(filterNull(iterator));
        Cursor next = iterator.getCursor();
        if (hasMorePages(next, orders)) {
            pageBean.setNext(Utilidades.getStrCursorDS(next));
        }
        return pageBean;
    }

    /**
     * @param iterator
     * @return list without null elements
     */
    private <T> List<T> filterNull(QueryResultIterator<T> iterator) {
        List<T> filteredList = Lists.newArrayList();
        while (iterator.hasNext()) {
            T item = iterator.next();
            if (item != null) {
                filteredList.add(item);
            }
        }
        return filteredList;
    }

    private <T> boolean hasMorePages(Cursor cursor, Map<String, Object> filters,
            List<String> orders) {
        Query<T> query = (Query<T>) aplicaFiltros(filters,
                ObjectifyService.ofy().load().type(this.dataClass)).limit(1)
                        .startAt(cursor);
        if (orders != null && !orders.isEmpty()) {
            query = applicaOrden(query, orders);
        }
        return query.iterator().hasNext();
    }

    private <T> boolean hasMorePages(Cursor cursor, List<String> orders) {
        Query<T> query = (Query<T>) ObjectifyService.ofy().load()
                .type(this.dataClass).limit(1).startAt(cursor);
        if (orders != null && !orders.isEmpty()) {
            query = applicaOrden(query, orders);
        }
        return query.iterator().hasNext();
    }

    public <T> boolean hasPages() {
        Query<T> query = (Query<T>) ObjectifyService.ofy().load()
                .type(this.dataClass).limit(1);

        return query.iterator().hasNext();
    }

    /**
     * Aplica filtros a la qry
     *
     * @param filters
     * @param query
     * @return
     */
    private <T> Query<T> aplicaFiltros(Map<String, Object> filters,
            Query<T> query) {

        if (filters != null) {
            for (Map.Entry<String, Object> key : filters.entrySet()) {
                query = query.filter(key.getKey(), filters.get(key.getKey()));
            }
        }
        return query;
    }

    /**
     * Establece los criterios de ordenación de la qry
     *
     * @param query
     * @param orders
     * @return
     */
    private <T> Query<T> applicaOrden(Query<T> query, List<String> orders) {
        for (String order : orders) {
            query = query.order(order);
        }
        return query;
    }

    /**
     * Recupera los registros filtrados por unos parámetros
     *
     * @param filters
     * @return
     */
    public Iterable<Key<T>> listIdsFilter(Map<String, Object> filters) {
        int attempt = INI_ATTEMPT;
        do {
            try {
                return aplicaFiltros(filters,
                        ObjectifyService.ofy().load().type(this.dataClass))
                                .keys();

            } catch (DatastoreTimeoutException e) {
                LOGGER.severe(LOG_ERROR_DS_ATTEMPT + attempt);
                if (attempt == MAX_ATTEMPTS) {
                    Utilidades.printException(e.getMessage(), e);
                    throw e;
                }
                attempt = reintento(attempt);
            }
        } while (attempt <= MAX_ATTEMPTS);
        LOGGER.severe(LOG_ERROR_DS + attempt);
        return Collections.emptyList();

    }

    public Iterable<Key<T>> listIds() {
        int attempt = INI_ATTEMPT;
        do {
            try {
                return ObjectifyService.ofy().load().type(this.dataClass)
                        .keys();

            } catch (DatastoreTimeoutException e) {
                LOGGER.severe(LOG_ERROR_DS_ATTEMPT + attempt);
                if (attempt == MAX_ATTEMPTS) {
                    Utilidades.printException(e.getMessage(), e);
                    throw e;
                }
                attempt = reintento(attempt);
            }
        } while (attempt <= MAX_ATTEMPTS);
        LOGGER.severe(LOG_ERROR_DS + attempt);
        return Collections.emptyList();

    }

}
