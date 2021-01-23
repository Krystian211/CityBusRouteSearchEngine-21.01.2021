package com.github.krystian211.city.bus.route.search.engine.model;

import javax.persistence.*;

@Entity(name = "travelTime")
@Table(name = "tTravelTime")
public class TravelTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private BusStop startingPoint;
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private BusStop endpoint;
    //Travel time in minutes
    private Integer travelTime;

    public TravelTime(int id, BusStop startingPoint, BusStop endpoint, Integer travelTime) {
        this.id = id;
        this.startingPoint = startingPoint;
        this.endpoint = endpoint;
        this.travelTime = travelTime;
    }

    public TravelTime() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BusStop getStartingPoint() {
        return this.startingPoint;
    }

    public void setStartingPoint(BusStop startingPoint) {
        this.startingPoint = startingPoint;
    }

    public BusStop getEndpoint() {
        return this.endpoint;
    }

    public void setEndpoint(BusStop endpoint) {
        this.endpoint = endpoint;
    }

    public Integer getTravelTime() {
        return this.travelTime;
    }

    public void setTravelTime(Integer travelTime) {
        this.travelTime = travelTime;
    }
}
