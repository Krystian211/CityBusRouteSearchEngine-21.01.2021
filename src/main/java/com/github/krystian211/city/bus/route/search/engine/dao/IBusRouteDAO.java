package com.github.krystian211.city.bus.route.search.engine.dao;

import com.github.krystian211.city.bus.route.search.engine.model.BusRoute;

public interface IBusRouteDAO {
    void persistBusRoute(BusRoute busRoute);
    BusRoute getBusRouteById(int busRouteId);
}
