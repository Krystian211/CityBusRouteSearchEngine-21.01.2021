package com.github.krystian211.city.bus.route.search.engine.model;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity(name = "tBusRoute")
public class BusRoute implements Comparable<BusRoute>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int busRouteNumber;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<BusStop> firstDirectionBusStops=new LinkedHashSet<>();
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<BusStop> oppositeDirectionBusStops=new LinkedHashSet<>();

    public BusRoute(int id, int busRouteNumber) {
        this.id = id;
        this.busRouteNumber = busRouteNumber;
    }

    public BusRoute() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBusRouteNumber() {
        return this.busRouteNumber;
    }

    public void setBusRouteNumber(int busRouteNumber) {
        this.busRouteNumber = busRouteNumber;
    }

    public Set<BusStop> getFirstDirectionBusStops() {
        return this.firstDirectionBusStops;
    }

    public void setFirstDirectionBusStops(Set<BusStop> firstDirectionBusStops) {
        this.firstDirectionBusStops = firstDirectionBusStops;
    }

    public Set<BusStop> getOppositeDirectionBusStops() {
        return this.oppositeDirectionBusStops;
    }

    public void setOppositeDirectionBusStops(Set<BusStop> oppositeDirectionBusStops) {
        this.oppositeDirectionBusStops = oppositeDirectionBusStops;
    }

    @Override
    public int compareTo(BusRoute o) {
        return Integer.compare(this.busRouteNumber, o.busRouteNumber);
    }
}
