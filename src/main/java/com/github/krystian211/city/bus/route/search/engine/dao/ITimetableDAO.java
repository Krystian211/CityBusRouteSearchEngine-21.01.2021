package com.github.krystian211.city.bus.route.search.engine.dao;

import com.github.krystian211.city.bus.route.search.engine.model.Timetable;

public interface ITimetableDAO {
    void persistTimetable(Timetable timetable);
}
