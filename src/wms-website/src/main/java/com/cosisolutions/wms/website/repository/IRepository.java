package com.cosisolutions.wms.website.repository;

import org.hibernate.HibernateException;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Ovidijus Stukas on 3/7/2016.
 * For CosISolutions
 */
public interface IRepository<T extends Serializable> {
    Integer insertEntity(T entity) throws HibernateException;

    void updateEntity(T entity) throws HibernateException;

    void deleteEntity(Class<T> clazz, int id) throws HibernateException;

    T getEntity(Class<T> clazz, int id) throws HibernateException;

    List<T> getEntities(Class<T> clazz) throws HibernateException;
}
