package com.github.krystian211.city.bus.route.search.engine.model;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Set;
import java.util.TreeSet;

@Entity(name = "timetable")
@Table(name = "tTimetable")
public class Timetable implements Comparable<Timetable>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "busRoute_id")
    private BusRoute busRoute;
    @ManyToOne(fetch = FetchType.EAGER)
    private BusStop parentBusStop;
    @ManyToOne(fetch = FetchType.EAGER)
    private BusStop direction;
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<LocalTime> weekdayDepartureTimes =new TreeSet<>();
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<LocalTime> saturdayDepartureTimes =new TreeSet<>();
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<LocalTime> sundayAndHolidayDepartureTimes =new TreeSet<>();

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

    public Set<LocalTime> getWeekdayDepartureTimes() {
        return this.weekdayDepartureTimes;
    }

    public void setWeekdayDepartureTimes(Set<LocalTime> weekdayDepartureTimes) {
        this.weekdayDepartureTimes = weekdayDepartureTimes;
    }

    public Set<LocalTime> getSaturdayDepartureTimes() {
        return this.saturdayDepartureTimes;
    }

    public void setSaturdayDepartureTimes(Set<LocalTime> saturdayDepartureTimes) {
        this.saturdayDepartureTimes = saturdayDepartureTimes;
    }

    public Set<LocalTime> getSundayAndHolidayDepartureTimes() {
        return this.sundayAndHolidayDepartureTimes;
    }

    public void setSundayAndHolidayDepartureTimes(Set<LocalTime> sundayAndHolidayDepartureTimes) {
        this.sundayAndHolidayDepartureTimes = sundayAndHolidayDepartureTimes;
    }

    @Override
    public int compareTo(Timetable o) {
        if (this.busRoute.getNumber()>o.busRoute.getNumber()) {
            return 1;
        }else if (this.busRoute.getNumber()<o.busRoute.getNumber()){
            return -1;

        }else {
            return this.direction.getName().compareTo(o.direction.getName());
        }


    }
}
