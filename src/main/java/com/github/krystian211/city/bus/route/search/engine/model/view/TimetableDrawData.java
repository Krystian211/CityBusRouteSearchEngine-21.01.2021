package com.github.krystian211.city.bus.route.search.engine.model.view;

import com.github.krystian211.city.bus.route.search.engine.model.Timetable;

import java.time.LocalTime;
import java.util.*;

public class TimetableDrawData {

    private String busStopName;
    private int busRouteNumber;
    private String direction;

    private List<List<Integer>> weekdayMinutesTable;
    private List<List<Integer>> saturdayMinutesTable;
    private List<List<Integer>> sundayMinutesTable;

    public TimetableDrawData(Timetable timetable) {
        this.busStopName=timetable.getParentBusStop().getName();
        this.busRouteNumber=timetable.getBusRoute().getNumber();
        this.direction=timetable.getDirection().getName();

        this.weekdayMinutesTable=getMinutesTable(timetable.getWeekdayDepartureTimes(),getMinutesRowNumber(timetable.getWeekdayDepartureTimes()));
        this.saturdayMinutesTable=getMinutesTable(timetable.getSaturdayDepartureTimes(),getMinutesRowNumber(timetable.getSaturdayDepartureTimes()));
        this.sundayMinutesTable=getMinutesTable(timetable.getSundayAndHolidayDepartureTimes(),getMinutesRowNumber(timetable.getSundayAndHolidayDepartureTimes()));

        for (LocalTime weekdayArrivalTime : timetable.getWeekdayDepartureTimes()) {
        }
    }

    public TimetableDrawData() {
    }

    public String getBusStopName() {
        return busStopName;
    }

    public void setBusStopName(String busStopName) {
        this.busStopName = busStopName;
    }

    public int getBusRouteNumber() {
        return busRouteNumber;
    }

    public void setBusRouteNumber(int busRouteNumber) {
        this.busRouteNumber = busRouteNumber;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public List<List<Integer>> getWeekdayMinutesTable() {
        return this.weekdayMinutesTable;
    }

    public void setWeekdayMinutesTable(List<List<Integer>> weekdayMinutesTable) {
        this.weekdayMinutesTable = weekdayMinutesTable;
    }

    public List<List<Integer>> getSaturdayMinutesTable() {
        return this.saturdayMinutesTable;
    }

    public void setSaturdayMinutesTable(List<List<Integer>> saturdayMinutesTable) {
        this.saturdayMinutesTable = saturdayMinutesTable;
    }

    public List<List<Integer>> getSundayMinutesTable() {
        return this.sundayMinutesTable;
    }

    public void setSundayMinutesTable(List<List<Integer>> sundayMinutesTable) {
        this.sundayMinutesTable = sundayMinutesTable;
    }

    private int getMinutesRowNumber(Set<LocalTime> localTimeList) {
        int[] minutesRowNumberForEachHour = new int[24];
        for (LocalTime localTime : localTimeList) {
            minutesRowNumberForEachHour[localTime.getHour()] += 1;
        }
        Arrays.sort(minutesRowNumberForEachHour);
        return minutesRowNumberForEachHour[minutesRowNumberForEachHour.length - 1];
    }

    private List<List<Integer>> getMinutesTable(Set<LocalTime> localTimeList, int minutesRowNumber) {
        List<LocalTime> arrivalTimeList=new ArrayList<>(localTimeList);
        Collections.sort(arrivalTimeList);
        List<List<Integer>> minutesTable = new ArrayList<List<Integer>>();
        int currentHour=99;
        int sameHourCounter=0;
        for (int i = 0; i < minutesRowNumber; i++) {
            minutesTable.add(new ArrayList<>(Collections.nCopies(24, null)));
        }
        for (LocalTime localTime : arrivalTimeList) {
            if (localTime.getHour()==currentHour) {
                sameHourCounter++;
            }else {
                currentHour=localTime.getHour();
                sameHourCounter=0;
            }
            minutesTable.get(sameHourCounter).set(localTime.getHour(),localTime.getMinute());
        }
        return minutesTable;
    }
}
