package com.github.krystian211.city.bus.route.search.engine.model.view;

import com.github.krystian211.city.bus.route.search.engine.model.BusRoute;
import com.github.krystian211.city.bus.route.search.engine.model.BusStop;

import java.time.LocalTime;
import java.util.List;

public class BusRouteTrip {
    private BusRoute busRoute;
    private LocalTime actualStartingTime;
    private LocalTime actualEndTime;
    private BusStop startingBusStop;
    private BusStop endBusStop;

    public BusRouteTrip(BusRoute busRoute, LocalTime actualStartingTime, LocalTime actualEndTime, BusStop startingBusStop, BusStop endBusStop) {
        this.busRoute = busRoute;
        this.actualStartingTime = actualStartingTime;
        this.actualEndTime = actualEndTime;
        this.startingBusStop = startingBusStop;
        this.endBusStop = endBusStop;
    }

    public BusRouteTrip() {
    }

    public BusRoute getBusRoute() {
        return this.busRoute;
    }

    public void setBusRoute(BusRoute busRoute) {
        this.busRoute = busRoute;
    }

    public LocalTime getActualStartingTime() {
        return this.actualStartingTime;
    }

    public void setActualStartingTime(LocalTime actualStartingTime) {
        this.actualStartingTime = actualStartingTime;
    }

    public LocalTime getActualEndTime() {
        return this.actualEndTime;
    }

    public void setActualEndTime(LocalTime actualEndTime) {
        this.actualEndTime = actualEndTime;
    }

    public BusStop getStartingBusStop() {
        return this.startingBusStop;
    }

    public void setStartingBusStop(BusStop startingBusStop) {
        this.startingBusStop = startingBusStop;
    }

    public BusStop getEndBusStop() {
        return this.endBusStop;
    }

    public void setEndBusStop(BusStop endBusStop) {
        this.endBusStop = endBusStop;
    }
}
