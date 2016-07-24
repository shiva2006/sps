package com.mydomain.sps.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

/**
 * @author Shiv.Gangadhar Implementaion of BaseDao
 */
@Stateless
@Name("baseDaoImpl")
@AutoCreate
public class BaseDaoImpl implements BaseDao, Serializable
{

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    @In
    private EntityManager entityManager;

    /**
     * @return Underlying Persistence provider session
     */
    public Session getSession() {
        return (Session) entityManager.getDelegate();
    }

    /*
     * (non-Javadoc)
     * @see com.laurusis.mes.warehouse.dao.BaseDao#saveObject(java.lang.Object)
     */
    public void saveObject(Object object) {
        entityManager.persist(object);
        entityManager.flush();
    }

    /*
     * (non-Javadoc)
     * @see com.laurusis.mes.warehouse.dao.BaseDao#update(java.lang.Object)
     */
    public void update(Object object) {
        entityManager.merge(object);
        entityManager.flush();
    }

    /*
     * (non-Javadoc)
     * @see com.laurusis.mes.warehouse.dao.BaseDao#delete(java.lang.Object)
     */
    public void delete(Object object) {
        entityManager.remove(object);
        entityManager.flush();
    }

    /*
     * (non-Javadoc)
     * @see com.laurusis.mes.warehouse.dao.BaseDao#getObject(java.lang.Class,
     * java.io.Serializable)
     */
    public Object getObject(Class<?> clazz, Serializable key) {
        return getSession().get(clazz, key);
    }

    /*
     * (non-Javadoc)
     * @see com.laurusis.mes.warehouse.dao.BaseDao#loadObject(java.lang.Class,
     * java.io.Serializable)
     */
    public Object loadObject(Class<?> clazz, Serializable key) {
        return getSession().load(clazz, key);
    }

    /*
     * (non-Javadoc)
     * @see com.laurusis.warehouse.mes.dao.BaseDao#find(java.lang.String,
     * java.lang.Object[])
     */
    public List<?> find(final String queryKey, Object... parameters) {
        Query query = entityManager.createNamedQuery(queryKey);
        bindParamtersToQuery(query, parameters);

        return query.getResultList();
    }

    /*
     * (non-Javadoc)
     * @see com.laurusis.mes.warehouse.dao.BaseDao#find(java.lang.String,
     * java.lang.Object[], int, int)
     */
    public List<?> find(final String queryKey, final Object[] parameters, final int startIndex,
            final int maxResults) {
        Query query = entityManager.createNamedQuery(queryKey);
        bindParamtersToQuery(query, parameters);
        query.setFirstResult(startIndex).setMaxResults(maxResults);
        return query.getResultList();
    }

    /*
     * (non-Javadoc)
     * @see com.laurusis.mes.warehouse.dao.BaseDao#countforObjects(java.lang
     * .String, java.lang.Object[])
     */
    public int countforObjects(final String queryKey, Object... parameters) {
        Query query = entityManager.createNamedQuery(queryKey);
        bindParamtersToQuery(query, parameters);
        Object count = query.getSingleResult();
        return (count == null) ? 0 : Integer.parseInt(count.toString());
    }

    /*
     * (non-Javadoc)
     * @see com.laurusis.mes.warehouse.dao.BaseDao#findUsingRestrictions(java
     * .lang.Class, java.lang.Object[], java.lang.Object[])
     */
    public List<?> findUsingRestrictions(final Class<?> clazz, final Object[] restrictions,
            Object... params) {
        Criteria criteria = getSession().createCriteria(clazz);
        return criteria
                .add(Restrictions.eq(restrictions[0].toString(),
                        Integer.parseInt(params[0].toString()))).list();
    }

    /*
     * (non-Javadoc)
     * @see com.laurusis.mes.warehouse.dao.BaseDao#loadUsingCriteria(java.lang
     * .Class, java.util.Map)
     */
    public List<?> loadUsingCriteria(final Class<?> clazz, final Map<String, Object> criteriaParams) {
        return getSession().createCriteria(clazz).add(Restrictions.allEq(criteriaParams)).list();
    }

    /*
     * (non-Javadoc)
     * @see com.laurusis.mes.warehouse.dao.BaseDao#persistAll(java.util.List)
     */
    public void persistAll(final List<?> list) {
        for (Object object : list) {
            getSession().persist(object);
        }
        getSession().flush();
        getSession().clear();
    }

    /**
     * @param parameters
     * @param query
     *            Binds parameter to query.
     */
    public void bindParamtersToQuery(Query query, Object... parameters) {
        if (parameters != null) {
            int index = 0;
            int length = parameters.length;
            while (index < length) {
                query.setParameter(index + 1, parameters[index++]);
            }
        }
    }

    /*
     * (non-Javadoc)
     * @see
     * com.laurusis.mes.warehouse.dao.BaseDao#executeProcedure(java.lang.String,
     * java.lang.Object[])
     */
    public void executeNativeQuery(String queryName, Object... params) {
        Query query = entityManager.createNamedQuery(queryName);
        bindParamtersToQuery(query, params);
        query.executeUpdate();
    }

    /*
     * (non-Javadoc)
     * @see com.laurusis.mes.warehouse.dao.BaseDao#loadObject(java.lang.Class,
     * java.io.Serializable, org.hibernate.LockMode)
     */
    public Object loadObject(Class<?> clazz, Serializable key, LockMode lockMode) {
        if (clazz == null || key == null || lockMode == null) {
            throw new IllegalArgumentException("Class, key and LockMode must not be null");
        }
        Criteria criteria =
                getSession().createCriteria(clazz).add(Restrictions.idEq(key))
                        .setLockMode(lockMode);
        return criteria.uniqueResult();
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List appedQuery(String queryKey, String queryToAppend, Object... parameters) {
        org.hibernate.Query query = getSession().getNamedQuery(queryKey);
        String namedQuery = query.getQueryString();
        namedQuery = namedQuery.concat(queryToAppend);        
        Query changedQuery = entityManager.createNativeQuery(namedQuery);
        bindParamtersToQuery(changedQuery, parameters);
        return changedQuery.getResultList();
    }

    @Override
    public Object getSingleResult(String query, Object... objects) {
        Query resultQuery = entityManager.createNativeQuery(query);
        bindParamtersToQuery(resultQuery, objects);
        return resultQuery.getSingleResult();
    }
    
    public static <T> T convertType(Object obj, Class<T> type) {
        T data = null;
        if (null != obj) {
            data = type.cast(obj);
        }
        return data;
    }

}
