package com.github.krystian211.city.bus.route.search.engine.services.impl;

import com.github.krystian211.city.bus.route.search.engine.dao.ITimetableDAO;
import com.github.krystian211.city.bus.route.search.engine.model.Timetable;
import com.github.krystian211.city.bus.route.search.engine.services.ITimetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimetableServiceImpl implements ITimetableService {

    @Autowired
    ITimetableDAO timetableDAO;

    @Override
    public Timetable getTimetable(int busStopId, int busRouteId, int directionId) {
        return timetableDAO.getTimetable(busStopId, busRouteId, directionId);
    }

    @Override
    public List<Timetable> getTimetablesByBusStop(int busStopId) {
        return this.timetableDAO.getTimetablesByBusStop(busStopId);
    }
}
