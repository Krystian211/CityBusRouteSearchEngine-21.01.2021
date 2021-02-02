package com.github.krystian211.city.bus.route.search.engine.services;

import com.github.krystian211.city.bus.route.search.engine.model.Timetable;
import java.util.List;

public interface ITimetableService {
    Timetable getTimetable(int busStopId, int busRouteId, int directionId);
    List<Timetable> getTimetablesByBusStop(int busStopId);

}
