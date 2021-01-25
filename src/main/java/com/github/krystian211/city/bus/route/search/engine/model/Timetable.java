package com.github.krystian211.city.bus.route.search.engine.model;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Set;
import java.util.TreeSet;

@Entity(name = "timetable")
@Table(name = "tTimetable")
public class Timetable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.EAGER)
    private BusRoute busRoute;
    @ManyToOne(fetch = FetchType.EAGER)
    private BusStop parentBusStop;
    @ManyToOne(fetch = FetchType.EAGER)
    private BusStop direction;
    @ElementCollection
    private Set<LocalTime> weekdaysArrivalTimes=new TreeSet<>();
    @ElementCollection
    private Set<LocalTime> saturdayArrivalTimes=new TreeSet<>();
    @ElementCollection
    private Set<LocalTime> sundayAndHolidaysArrivalTimes=new TreeSet<>();

    public Timetable(int id, BusRoute busRoute, BusStop parentBusStop, BusStop direction) {
        this.id = id;
        this.busRoute = busRoute;
        this.parentBusStop = parentBusStop;
        this.direction = direction;
    }

    public Timetable() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BusRoute getBusRoute() {
        return this.busRoute;
    }

    public void setBusRoute(BusRoute busRoute) {
        this.busRoute = busRoute;
    }

    public BusStop getParentBusStop() {
        return this.parentBusStop;
    }

    public void setParentBusStop(BusStop parentBusStop) {
        this.parentBusStop = parentBusStop;
    }

    public BusStop getDirection() {
        return this.direction;
    }

    public void setDirection(BusStop direction) {
        this.direction = direction;
    }

    public Set<LocalTime> getWeekdaysArrivalTimes() {
        return this.weekdaysArrivalTimes;
    }

    public void setWeekdaysArrivalTimes(Set<LocalTime> weekdaysArrivalTimes) {
        this.weekdaysArrivalTimes = weekdaysArrivalTimes;
    }

    public Set<LocalTime> getSaturdayArrivalTimes() {
        return this.saturdayArrivalTimes;
    }

    public void setSaturdayArrivalTimes(Set<LocalTime> saturdayArrivalTimes) {
        this.saturdayArrivalTimes = saturdayArrivalTimes;
    }

    public Set<LocalTime> getSundayAndHolidaysArrivalTimes() {
        return this.sundayAndHolidaysArrivalTimes;
    }

    public void setSundayAndHolidaysArrivalTimes(Set<LocalTime> sundayAndHolidaysArrivalTimes) {
        this.sundayAndHolidaysArrivalTimes = sundayAndHolidaysArrivalTimes;
    }

}
