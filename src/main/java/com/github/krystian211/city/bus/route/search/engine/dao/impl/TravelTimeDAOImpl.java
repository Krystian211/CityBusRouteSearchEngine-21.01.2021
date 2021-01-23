package com.github.krystian211.city.bus.route.search.engine.dao.impl;

import com.github.krystian211.city.bus.route.search.engine.dao.ITravelTimeDAO;
import com.github.krystian211.city.bus.route.search.engine.model.TravelTime;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TravelTimeDAOImpl implements ITravelTimeDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void persistTravelTime(TravelTime travelTime) {
        CommonDAOUtilities.persistObject(travelTime,sessionFactory);
    }
}
