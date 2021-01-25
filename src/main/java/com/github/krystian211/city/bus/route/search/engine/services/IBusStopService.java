package com.github.krystian211.city.bus.route.search.engine.services;

import com.github.krystian211.city.bus.route.search.engine.model.BusStop;

import java.util.List;

public interface IBusStopService {
    List<BusStop> getAllBusStops();
    List<BusStop> getBusStopsByStreet(int streetId);
}
