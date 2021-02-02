package com.github.krystian211.city.bus.route.search.engine.model;

import javax.persistence.*;
import java.util.Map;
import java.util.TreeMap;

@Entity(name = "busRoute")
@Table(name = "tBusRoute")
public class BusRoute implements Comparable<BusRoute> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int number;
    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(joinColumns = @JoinColumn(name = "busRouteId"),
            inverseJoinColumns = @JoinColumn(name = "busStopId"))
    private Map<Integer, BusStop> passedBusStops = new TreeMap<>();

    public BusRoute(int id, int number) {
        this.id = id;
        this.number = number;
    }

    public BusRoute() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return this.number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Map<Integer, BusStop> getPassedBusStops() {
        return this.passedBusStops;
    }

    public void setPassedBusStops(Map<Integer, BusStop> passedBusStops) {
        this.passedBusStops = passedBusStops;
    }

    @Override
    public int compareTo(BusRoute o) {
        return Integer.compare(this.number, o.number);
    }

}
