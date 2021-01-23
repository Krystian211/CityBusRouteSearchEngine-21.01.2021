package com.github.krystian211.city.bus.route.search.engine.dao.impl;

import com.github.krystian211.city.bus.route.search.engine.dao.ITimetableDAO;
import com.github.krystian211.city.bus.route.search.engine.model.Timetable;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TimetableDAOImpl implements ITimetableDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void persistTimetable(Timetable timetable) {
        CommonDAOUtilities.persistObject(timetable,sessionFactory);
    }
}
