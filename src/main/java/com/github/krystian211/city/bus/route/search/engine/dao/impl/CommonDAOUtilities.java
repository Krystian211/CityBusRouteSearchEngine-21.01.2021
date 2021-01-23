package com.github.krystian211.city.bus.route.search.engine.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;

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

    static <T> T getObjectById(int objectId,String entityName, SessionFactory sessionFactory){
        Session session=sessionFactory.openSession();
        Query<T> query=session.createQuery("FROM com.github.krystian211.city.bus.route.search.engine.model."+entityName+" WHERE id=:id");
        query.setParameter("id",objectId);
        T object=null;
        try {
            object = query.getSingleResult();
        }catch (NoResultException e){
            System.out.println("No object found!");
        }finally {
            session.close();
        }

        return object;
    }

}
