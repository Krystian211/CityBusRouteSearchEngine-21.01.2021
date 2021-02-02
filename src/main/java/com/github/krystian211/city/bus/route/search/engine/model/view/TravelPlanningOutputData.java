package com.github.krystian211.city.bus.route.search.engine.model.view;

import com.github.krystian211.city.bus.route.search.engine.model.BusRoute;
import com.github.krystian211.city.bus.route.search.engine.model.BusStop;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TravelPlanningOutputData {
    private Map<BusRoute,List<BusStop>> busStopsForBusRouteMap=new TreeMap<>();
    private LocalTime startingTime;
    private LocalTime endTime;
    private int estimatedTravelTime;

    public TravelPlanningOutputData(Map<BusRoute, List<BusStop>> busStopsForBusRouteMap, LocalTime startingTime, LocalTime endTime, int estimatedTravelTime) {
        this.busStopsForBusRouteMap = busStopsForBusRouteMap;
        this.startingTime = startingTime;
        this.endTime = endTime;
        this.estimatedTravelTime = estimatedTravelTime;
    }

    public TravelPlanningOutputData() {
    }

    public Map<BusRoute, List<BusStop>> getBusStopsForBusRouteMap() {
        return this.busStopsForBusRouteMap;
    }

    public void setBusStopsForBusRouteMap(Map<BusRoute, List<BusStop>> busStopsForBusRouteMap) {
        this.busStopsForBusRouteMap = busStopsForBusRouteMap;
    }

    public LocalTime getStartingTime() {
        return this.startingTime;
    }

    public void setStartingTime(LocalTime startingTime) {
        this.startingTime = startingTime;
    }

    public LocalTime getEndTime() {
        return this.endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public int getEstimatedTravelTime() {
        return this.estimatedTravelTime;
    }

    public void setEstimatedTravelTime(int estimatedTravelTime) {
        this.estimatedTravelTime = estimatedTravelTime;
    }
}
