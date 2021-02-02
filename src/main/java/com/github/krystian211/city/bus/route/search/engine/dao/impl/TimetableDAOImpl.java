package com.github.krystian211.city.bus.route.search.engine.dao.impl;

import com.github.krystian211.city.bus.route.search.engine.dao.ITimetableDAO;
import com.github.krystian211.city.bus.route.search.engine.model.Timetable;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class TimetableDAOImpl implements ITimetableDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void persistTimetable(Timetable timetable) {
        CommonDAOUtilities.persistObject(timetable, sessionFactory);
    }

    @Override
    public Timetable getTimetable(int busStopId, int busRouteId, int directionId) {
        Session session = sessionFactory.openSession();
        Query<Timetable> query = session.createQuery("FROM com.github.krystian211.city.bus.route.search.engine.model.Timetable WHERE parentBusStop_id=:busStopId AND busRoute_id=:busRouteId AND direction_id=:directionId");
        query.setParameter("busStopId", busStopId);
        query.setParameter("busRouteId", busRouteId);
        query.setParameter("directionId", directionId);
        Timetable timetable = null;
        try {
            timetable = query.getSingleResult();
        } catch (
                NoResultException e) {
            System.out.println("No timetable!");
        } finally {
            session.close();
        }
        return timetable;
    }

    @Override
    public List<Timetable> getTimetablesByBusStop(int busStopId) {
        Session session = sessionFactory.openSession();
        Query<Timetable> query = session.createQuery("FROM com.github.krystian211.city.bus.route.search.engine.model.Timetable  WHERE parentBusStop_id =: busStopId");
        query.setParameter("busStopId", busStopId);
        List<Timetable> timetables = query.getResultList();
        session.close();
        return timetables;
    }
}
