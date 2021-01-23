package com.github.krystian211.city.bus.route.search.engine.dao.impl;

import com.github.krystian211.city.bus.route.search.engine.dao.IBusRouteDAO;
import com.github.krystian211.city.bus.route.search.engine.model.BusRoute;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BusRouteDAOImpl implements IBusRouteDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void persistBusRoute(BusRoute busRoute) {
        CommonDAOUtilities.persistObject(busRoute,sessionFactory);
    }

    @Override
    public BusRoute getBusRouteById(int busRouteId) {
        return CommonDAOUtilities.getObjectById(busRouteId,"BusRoute",sessionFactory);
    }
}
