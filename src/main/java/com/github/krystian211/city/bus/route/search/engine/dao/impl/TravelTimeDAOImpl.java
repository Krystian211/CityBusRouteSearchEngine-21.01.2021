package com.github.krystian211.city.bus.route.search.engine.dao.impl;

import com.github.krystian211.city.bus.route.search.engine.dao.ITravelTimeDAO;
import com.github.krystian211.city.bus.route.search.engine.model.Timetable;
import com.github.krystian211.city.bus.route.search.engine.model.TravelTime;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;

@Repository
public class TravelTimeDAOImpl implements ITravelTimeDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void persistTravelTime(TravelTime travelTime) {
        CommonDAOUtilities.persistObject(travelTime,sessionFactory);
    }

    @Override
    public TravelTime getTravelTimeByBusStopIds(int startingBusStopId, int endBusStopId) {
        Session session = sessionFactory.openSession();
        Query<TravelTime > query = session.createQuery("FROM com.github.krystian211.city.bus.route.search.engine.model.TravelTime WHERE startingPoint_id=:startingPointId AND endpoint_id=:endpointId");
        query.setParameter("startingPointId", startingBusStopId);
        query.setParameter("endpointId", endBusStopId);
        TravelTime travelTime = null;
        try {
            travelTime = query.getSingleResult();
        } catch (
                NoResultException e) {
            System.out.println("No travel time found!");
        } finally {
            session.close();
        }
        return travelTime;
    }
}
