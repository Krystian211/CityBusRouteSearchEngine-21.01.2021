package com.github.krystian211.city.bus.route.search.engine.model.view;

import com.github.krystian211.city.bus.route.search.engine.model.BusRoute;
import com.github.krystian211.city.bus.route.search.engine.model.BusStop;

import java.util.ArrayList;
import java.util.List;

public class BothDirectionBusStops {
    private List<List<BusStop>> bothDirectionsBusStops=new ArrayList<>();

    public BothDirectionBusStops(BusRoute busRoute) {
        int busStopsNumber
        busRoute.getPassedBusStops().size()
    }

    public BothDirectionBusStops() {
    }

    public List<List<BusStop>> getBothDirectionsBusStops() {
        return bothDirectionsBusStops;
    }

    public void setBothDirectionsBusStops(List<List<BusStop>> bothDirectionsBusStops) {
        this.bothDirectionsBusStops = bothDirectionsBusStops;
    }
}
