package com.github.krystian211.city.bus.route.search.engine.services.impl;

import com.github.krystian211.city.bus.route.search.engine.dao.IBusStopDAO;
import com.github.krystian211.city.bus.route.search.engine.model.BusStop;
import com.github.krystian211.city.bus.route.search.engine.services.IBusStopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusStopServiceImpl implements IBusStopService {

    @Autowired
    IBusStopDAO busStopDAO;

    @Override
    public List<BusStop> getAllBusStops() {
        return busStopDAO.getAllBusStop();
    }

    @Override
    public List<BusStop> getBusStopsByStreet(int streetId) {
        return this.busStopDAO.getBusStopsByStreet(streetId);
    }
}
