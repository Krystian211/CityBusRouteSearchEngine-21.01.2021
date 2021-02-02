package com.github.krystian211.city.bus.route.search.engine.services.impl;

import com.github.krystian211.city.bus.route.search.engine.dao.IBusRouteDAO;
import com.github.krystian211.city.bus.route.search.engine.model.BusRoute;
import com.github.krystian211.city.bus.route.search.engine.services.IBusRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusRouteServiceImpl implements IBusRouteService {

    @Autowired
    IBusRouteDAO busRouteDAO;

    @Override
    public List<BusRoute> getAllBusRoutes() {
        return busRouteDAO.getAllBusRoutes();
    }

    @Override
    public List<BusRoute> getBusRoutesByBusStop(int busStopId) {
        return busRouteDAO.getBusRoutesByBusStop(busStopId);
    }

    @Override
    public BusRoute getBusRouteById(int busRouteId) {
        return this.busRouteDAO.getBusRouteById(busRouteId);
    }
}
