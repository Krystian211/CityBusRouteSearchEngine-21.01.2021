package com.github.krystian211.city.bus.route.search.engine.dao;

import com.github.krystian211.city.bus.route.search.engine.model.Timetable;

import java.time.LocalTime;
import java.util.List;

public interface ITimetableDAO {
    void persistTimetable(Timetable timetable);
    Timetable getTimetable(int busStopId,int busRouteId,int directionId);
    List<Timetable> getTimetablesByBusStop(int busStopId);
}
