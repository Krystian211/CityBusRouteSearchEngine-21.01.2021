package com.github.krystian211.city.bus.route.search.engine.model.view;

import java.util.ArrayList;
import java.util.List;

public class TravelOptionOutputData {
    private List<BusRouteTrip> busRouteTripList=new ArrayList<>();
    private long estimatedTravelTime;

    public TravelOptionOutputData(int estimatedTravelTime) {
        this.estimatedTravelTime = estimatedTravelTime;
    }

    public TravelOptionOutputData() {
    }

    public List<BusRouteTrip> getBusRouteTripList() {
        return this.busRouteTripList;
    }

    public void setBusRouteTripList(List<BusRouteTrip> busRouteTripList) {
        this.busRouteTripList = busRouteTripList;
    }

    public long getEstimatedTravelTime() {
        return this.estimatedTravelTime;
    }

    public void setEstimatedTravelTime(long estimatedTravelTime) {
        this.estimatedTravelTime = estimatedTravelTime;
    }
}
