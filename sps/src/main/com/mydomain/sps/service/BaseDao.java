package com.mydomain.sps.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.LockMode;
import org.hibernate.Session;

/**
 * @author Shiv.Gangadhar Contains common dao operations.
 */
/**
 * @author Shiv.Gangadhar
 *
 */
public interface BaseDao
{
    /**
     * @param object
     *            Synchronize an entity with DB.
     */
    public void saveObject(Object object);

    /**
     * @param query
     * @param parms
     * @return ResultList from DB
     */
    public List find(String query, Object... parms);

    /**
     * @param queryKey
     * @param parameters
     * @param startIndex
     * @param maxResults
     * @return Limited ResultList
     */
    public List find(final String queryKey, final Object[] parameters, final int startIndex,
            final int maxResults);

    /**
     * @param queryKey
     * @param parameters
     * @return count of a query.
     */
    public int countforObjects(final String queryKey, Object... parameters);

    /**
     * @param object
     *            deletes object from persistence context.
     */
    public void delete(Object object);

    /**
     * @param object
     *            updates persistence context.
     */
    public void update(Object object);

    /**
     * @param clazz
     * @param key
     * @return Object from proxy. if object is not present in proxy throws
     *         nullPointerException.
     */
    public Object loadObject(Class<?> clazz, Serializable key);

    public Object loadObject(Class<?> clazz, Serializable key, LockMode lockMode);

    /**
     * @param clazz
     * @param key
     * @return Object from db. if object is not present in db, returns null.
     */
    public Object getObject(Class<?> clazz, Serializable key);

    /**
     * @param clazz
     * @param variables
     * @param params
     * @return List using Restrictions.
     */
    public List findUsingRestrictions(Class<?> clazz, final Object[] variables,
            Object... params);

    /**
     * @param list
     *            batch inserts.
     */
    public void persistAll(List<?> list);

    /**
     * @param clazz
     * @param criteria
     * @return List using Restrictions.
     */
    public List loadUsingCriteria(Class<?> clazz, Map<String, Object> criteria);

    /**
     * returns Underlying Persistence provider session
     */
    public Session getSession();

    /**
     * @param procedure
     *            takes procedure name as an arguments
     * @param params
     *            these params will be set into procedure
     */
    public void executeNativeQuery(String procedure, Object... parameters);
    public List appedQuery(String query, String queryToAppend, Object...objects); 
    public Object getSingleResult(String query, Object...objects);
}
