package com.github.krystian211.city.bus.route.search.engine.dao;

import com.github.krystian211.city.bus.route.search.engine.model.BusRoute;

import java.util.List;

public interface IBusRouteDAO {
    void persistBusRoute(BusRoute busRoute);
    List<BusRoute> getAllBusRoutes();
    List<BusRoute> getBusRoutesByBusStop(int busStopId);
    BusRoute getBusRouteById(int busRouteId);
}
