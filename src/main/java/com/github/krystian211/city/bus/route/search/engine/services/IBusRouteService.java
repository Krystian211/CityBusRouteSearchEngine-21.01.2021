package com.github.krystian211.city.bus.route.search.engine.services;

import com.github.krystian211.city.bus.route.search.engine.model.BusRoute;

import java.util.List;

public interface IBusRouteService {
    List<BusRoute> getAllBusRoutes();
    List<BusRoute> getBusRoutesByBusStop(int busStopId);
}
