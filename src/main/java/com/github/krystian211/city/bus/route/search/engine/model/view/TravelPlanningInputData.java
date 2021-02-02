package com.github.krystian211.city.bus.route.search.engine.model.view;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

public class TravelPlanningData {
    private int startingBusStopId;
    private int endBusStopId;
    private LocalTime travelStartingTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate travelDate;
    private int changeNumber;

    public TravelPlanningData(int startingBusStopId, int endBusStopId, LocalTime travelStartingTime, LocalDate travelDate, int changeNumber) {
        this.startingBusStopId = startingBusStopId;
        this.endBusStopId = endBusStopId;
        this.travelStartingTime = travelStartingTime;
        this.travelDate = travelDate;
        this.changeNumber = changeNumber;
    }

    public TravelPlanningData() {
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

    public LocalTime getTravelStartingTime() {
        return this.travelStartingTime;
    }

    public void setTravelStartingTime(LocalTime travelStartingTime) {
        this.travelStartingTime = travelStartingTime;
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

    public static TravelPlanningData initialize(TravelPlanningData travelPlanningData) {
        travelPlanningData.setTravelStartingTime(LocalTime.now());
        travelPlanningData.setTravelDate(LocalDate.now());
        travelPlanningData.setChangeNumber(0);
        return travelPlanningData;
    }
}
