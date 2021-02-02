package com.github.krystian211.city.bus.route.search.engine.model.view;

import com.github.krystian211.city.bus.route.search.engine.model.BusRoute;
import com.github.krystian211.city.bus.route.search.engine.model.BusStop;

import java.util.ArrayList;
import java.util.List;

public class BusRouteBusStopsData {
    private int busRouteId;
    private int busRouteNumber;
    private List<List<BusStop>> bothDirectionsBusStops=new ArrayList<>();

    public BusRouteBusStopsData(BusRoute busRoute) {
        this.busRouteId=busRoute.getId();
        this.busRouteNumber=busRoute.getNumber();
        int busStopsNumber= busRoute.getPassedBusStops().size();
        List<BusStop> subList;
        for (int i = 0; i < busStopsNumber; i++) {
            subList=new ArrayList<>();
            subList.add(busRoute.getPassedBusStops().get(i));
            subList.add(busRoute.getPassedBusStops().get(busStopsNumber-1-i));
            this.bothDirectionsBusStops.add(subList);
        }
    }

    public BusRouteBusStopsData() {
    }

    public int getBusRouteId() {
        return busRouteId;
    }

    public void setBusRouteId(int busRouteId) {
        this.busRouteId = busRouteId;
    }

    public int getBusRouteNumber() {
        return busRouteNumber;
    }

    public void setBusRouteNumber(int busRouteNumber) {
        this.busRouteNumber = busRouteNumber;
    }

    public List<List<BusStop>> getBothDirectionsBusStops() {
        return bothDirectionsBusStops;
    }

    public void setBothDirectionsBusStops(List<List<BusStop>> bothDirectionsBusStops) {
        this.bothDirectionsBusStops = bothDirectionsBusStops;
    }
}
