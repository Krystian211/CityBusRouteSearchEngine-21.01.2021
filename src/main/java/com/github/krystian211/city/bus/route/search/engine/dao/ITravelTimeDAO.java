package com.github.krystian211.city.bus.route.search.engine.dao;

import com.github.krystian211.city.bus.route.search.engine.model.TravelTime;

public interface ITravelTimeDAO {
    void persistTravelTime(TravelTime travelTime);
    TravelTime getTravelTimeByBusStopIds(int startingBusStopId, int endBusStopId);
}
