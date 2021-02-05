package com.github.krystian211.city.bus.route.search.engine.model.view;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

public class TravelPlanningInputData {
    private int startingBusStopId;
    private int endBusStopId;
    private LocalTime usersStartingTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate travelDate;
    private int changeNumber;

    public TravelPlanningInputData(int startingBusStopId, int endBusStopId, LocalTime usersStartingTime, LocalDate travelDate, int changeNumber) {
        this.startingBusStopId = startingBusStopId;
        this.endBusStopId = endBusStopId;
        this.usersStartingTime = usersStartingTime;
        this.travelDate = travelDate;
        this.changeNumber = changeNumber;
    }

    public TravelPlanningInputData() {
    }

    public int getStartingBusStopId() {
        return this.startingBusStopId;
    }

    public void setStartingBusStopId(int startingBusStopId) {
        this.startingBusStopId = startingBusStopId;
    }

    public int getEndBusStopId() {
        return this.endBusStopId;
    }

    public void setEndBusStopId(int endBusStopId) {
        this.endBusStopId = endBusStopId;
    }

    public LocalTime getUsersStartingTime() {
        return this.usersStartingTime;
    }

    public void setUsersStartingTime(LocalTime usersStartingTime) {
        this.usersStartingTime = usersStartingTime;
    }

    public LocalDate getTravelDate() {
        return this.travelDate;
    }

    public void setTravelDate(LocalDate travelDate) {
        this.travelDate = travelDate;
    }

    public int getChangeNumber() {
        return this.changeNumber;
    }

    public void setChangeNumber(int changeNumber) {
        this.changeNumber = changeNumber;
    }

    public static TravelPlanningInputData initialize(TravelPlanningInputData travelPlanningInputData) {
        travelPlanningInputData.setUsersStartingTime(LocalTime.now());
        travelPlanningInputData.setTravelDate(LocalDate.now());
        travelPlanningInputData.setChangeNumber(0);
        return travelPlanningInputData;
    }
}
