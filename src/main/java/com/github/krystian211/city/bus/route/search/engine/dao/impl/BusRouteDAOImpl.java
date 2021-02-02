package com.github.krystian211.city.bus.route.search.engine.dao.impl;

import com.github.krystian211.city.bus.route.search.engine.dao.IBusRouteDAO;
import com.github.krystian211.city.bus.route.search.engine.model.BusRoute;
import com.github.krystian211.city.bus.route.search.engine.model.BusStop;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BusRouteDAOImpl implements IBusRouteDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void persistBusRoute(BusRoute busRoute) {
        CommonDAOUtilities.persistObject(busRoute,sessionFactory);
    }

    @Override
    public List<BusRoute> getAllBusRoutes() {
        return CommonDAOUtilities.getAllObjects(BusRoute.class,sessionFactory);
    }

    @Override
    public List<BusRoute> getBusRoutesByBusStop(int busStopId) {
        Session session = sessionFactory.openSession();
        Query<BusRoute> query = session.createQuery("SELECT br FROM com.github.krystian211.city.bus.route.search.engine.model.BusRoute br JOIN br.passedBusStops pbs WHERE pbs.id=:busStopId");
        query.setParameter("busStopId", busStopId);
        List<BusRoute> busStops = query.getResultList();
        session.close();
        return busStops;
    }

    @Override
    public BusRoute getBusRouteById(int busRouteId) {
        return CommonDAOUtilities.getObjectById(BusRoute.class,busRouteId,sessionFactory);
    }

}
