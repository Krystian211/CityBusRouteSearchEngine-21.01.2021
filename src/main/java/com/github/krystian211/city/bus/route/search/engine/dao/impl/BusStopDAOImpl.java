package com.github.krystian211.city.bus.route.search.engine.dao.impl;

import com.github.krystian211.city.bus.route.search.engine.dao.IBusStopDAO;
import com.github.krystian211.city.bus.route.search.engine.model.BusStop;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BusStopDAOImpl implements IBusStopDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void persistBusStop(BusStop busStop) {
        CommonDAOUtilities.persistObject(busStop,sessionFactory);
    }

    @Override
    public List<BusStop> getAllBusStop() {
        return CommonDAOUtilities.getAllObjects(BusStop.class,sessionFactory);
    }

    @Override
    public List<BusStop> getBusStopsByStreet(int streetId) {
        Session session = sessionFactory.openSession();
        Query<BusStop> query = session.createQuery("SELECT bs FROM com.github.krystian211.city.bus.route.search.engine.model.BusStop bs JOIN bs.streets sts WHERE sts.id=:streetId");
        query.setParameter("streetId", streetId);
        List<BusStop> busStops = query.getResultList();
        session.close();
        return busStops;
    }
}
