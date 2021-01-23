package com.github.krystian211.city.bus.route.search.engine.model;

import javax.persistence.*;
import java.util.Set;
import java.util.TreeSet;

@Entity(name = "busStop")
@Table(name="tBusStop")
public class BusStop implements Comparable<BusStop>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String busStopName;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            joinColumns = @JoinColumn(name="busStopId"),
            inverseJoinColumns = @JoinColumn(name = "streetId")
    )
    private Set<Street> streets=new TreeSet<>();
    @ManyToMany(fetch = FetchType.EAGER,
    mappedBy = "passedBusStops")
    private Set<BusRoute> passingBusRoutes=new TreeSet<>();

    public BusStop(int id, String busStopName) {
        this.id = id;
        this.busStopName = busStopName;
    }

    public BusStop() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBusStopName() {
        return this.busStopName;
    }

    public void setBusStopName(String busStopName) {
        this.busStopName = busStopName;
    }

    public Set<BusRoute> getPassingBusRoutes() {
        return this.passingBusRoutes;
    }

    public void setPassingBusRoutes(Set<BusRoute> passingBusRoutes) {
        this.passingBusRoutes = passingBusRoutes;
    }

    public Set<Street> getStreets() {
        return this.streets;
    }

    public void setStreets(Set<Street> streets) {
        this.streets = streets;
    }

    @Override
    public int compareTo(BusStop o) {
        return this.getBusStopName().compareToIgnoreCase(o.getBusStopName());
    }

}
