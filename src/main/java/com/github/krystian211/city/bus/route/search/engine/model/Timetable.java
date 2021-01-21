package com.github.krystian211.city.bus.route.search.engine.model;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Set;

@Entity(name = "tTimetable")
public class Timetable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.EAGER)
    private BusRoute busRoute;
    @ManyToOne(fetch = FetchType.EAGER)
    private BusStop actualBusStop;
    @ManyToOne(fetch = FetchType.EAGER)
    private BusStop direction;
    private Set<LocalTime> weekdaysArrivalTimes;
    private Set<LocalTime> saturdayArrivalTimes;
    private Set<LocalTime> sundayAndHolidaysArrivalTimes;

    public Timetable(int id, BusRoute busRoute, BusStop actualBusStop, BusStop direction) {
        this.id = id;
        this.busRoute = busRoute;
        this.actualBusStop = actualBusStop;
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

    public BusStop getActualBusStop() {
        return this.actualBusStop;
    }

    public void setActualBusStop(BusStop actualBusStop) {
        this.actualBusStop = actualBusStop;
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
