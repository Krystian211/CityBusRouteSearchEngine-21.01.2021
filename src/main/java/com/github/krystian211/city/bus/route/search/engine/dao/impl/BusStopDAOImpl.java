package com.github.krystian211.city.bus.route.search.engine.dao.impl;

import com.github.krystian211.city.bus.route.search.engine.dao.IBusStopDAO;
import com.github.krystian211.city.bus.route.search.engine.model.BusStop;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BusStopDAOImpl implements IBusStopDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void persistBusStop(BusStop busStop) {
        CommonDAOUtilities.persistObject(busStop,sessionFactory);
    }
}
