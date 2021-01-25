package com.github.krystian211.city.bus.route.search.engine.dao;

import com.github.krystian211.city.bus.route.search.engine.model.BusStop;

import java.util.List;

public interface IBusStopDAO {
    void persistBusStop(BusStop busStop);
    List<BusStop> getAllBusStop();
    List<BusStop> getBusStopsByStreet(int streetId);

}
