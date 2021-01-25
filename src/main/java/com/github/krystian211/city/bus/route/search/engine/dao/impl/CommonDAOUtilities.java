package com.github.krystian211.city.bus.route.search.engine.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import java.util.List;

public class CommonDAOUtilities {

    static <T> void persistObject(T object, SessionFactory sessionFactory) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(object);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    static <T> List<T> getAllObjects(Class<T> entityClass, SessionFactory sessionFactory) {
        Session session = sessionFactory.openSession();
        String entityPath = entityClass.toString();
        entityPath = entityPath.replaceAll("class", "");
        Query<T> query = session.createQuery("FROM" + entityPath);
        List<T> objects = query.getResultList();
        session.close();
        return objects;
    }

    static <T> T getObjectByAttribute(Class<T> entityClass, String attributeName, String attributeValue, SessionFactory sessionFactory) {
        Session session = sessionFactory.openSession();
        String entityPath = entityClass.toString();
        entityPath=entityPath.replaceAll("class","");
        Query<T> query = session.createQuery("FROM" + entityPath + " WHERE "+attributeName+";=attrValue");
        query.setParameter("attrValue",attributeValue);
        T object = null;
        try
        {
            object = query.getSingleResult();
        }catch(
                NoResultException e)
        {
            System.out.println("No object found!");
        }finally
        {
            session.close();
        }
        return object;
    }

    static <T> List<T> getObjectsByAttribute(Class<T> entityClass, String attributeName, String attributeValue, SessionFactory sessionFactory) {
        Session session = sessionFactory.openSession();
        String entityPath = entityClass.toString();
        entityPath = entityPath.replaceAll("class", "");
        Query<T> query = session.createQuery("FROM" + entityPath + " WHERE " + attributeName + "=:attrValue");
        query.setParameter("attrValue", attributeName);
        List<T> objects = query.getResultList();
        session.close();
        return objects;
    }

}
