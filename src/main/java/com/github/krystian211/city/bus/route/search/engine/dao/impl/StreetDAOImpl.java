package com.github.krystian211.city.bus.route.search.engine.dao.impl;

import com.github.krystian211.city.bus.route.search.engine.dao.IStreetDAO;
import com.github.krystian211.city.bus.route.search.engine.model.Street;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StreetDAOImpl implements IStreetDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void persistStreet(Street street) {
        CommonDAOUtilities.persistObject(street,sessionFactory);
    }
}
