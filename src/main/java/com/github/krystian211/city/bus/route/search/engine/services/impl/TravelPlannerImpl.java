package com.github.krystian211.city.bus.route.search.engine.services.impl;

import com.github.krystian211.city.bus.route.search.engine.dao.IBusRouteDAO;
import com.github.krystian211.city.bus.route.search.engine.dao.IBusStopDAO;
import com.github.krystian211.city.bus.route.search.engine.dao.ITimetableDAO;
import com.github.krystian211.city.bus.route.search.engine.dao.ITravelTimeDAO;
import com.github.krystian211.city.bus.route.search.engine.model.BusRoute;
import com.github.krystian211.city.bus.route.search.engine.model.BusStop;
import com.github.krystian211.city.bus.route.search.engine.model.view.TravelPlanningInputData;
import com.github.krystian211.city.bus.route.search.engine.model.view.TravelPlanningOutputData;
import com.github.krystian211.city.bus.route.search.engine.services.ITravelPlanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class TravelPlannerImpl implements ITravelPlanner {

    @Autowired
    IBusRouteDAO busRouteDAO;

    @Autowired
    IBusStopDAO busStopDAO;

    @Autowired
    ITravelTimeDAO travelTimeDAO;

    @Autowired
    ITimetableDAO timetableDAO;

    @Override
    public List<TravelPlanningOutputData> planTravel(TravelPlanningInputData travelPlanningInputData) {

        System.out.println();
        System.out.println(findClosestDepartureTime(LocalDate.of(2021,1,31),LocalTime.of(11,20),1,4,18));

        return null;
    }

    private List<TravelOption> findTravelOptionsWithoutChanges(TravelPlanningInputData travelPlanningInputData) {
        List<TravelOption> travelOptionList = new ArrayList<>();
        for (BusRoute busRoute : this.busRouteDAO.getBusRoutesByBusStop(travelPlanningInputData.getStartingBusStopId())) {
            for (Map.Entry<Integer, BusStop> busStopNumberBusStopEntry : busRoute.getPassedBusStops().entrySet()) {
                if (busStopNumberBusStopEntry.getValue().getId() == travelPlanningInputData.getEndBusStopId()) {
                    TravelOption travelOption = new TravelOption();
                    travelOption.getBusRouteTripList().add(new BusRouteTrip(busRoute, travelPlanningInputData.getStartingBusStopId(), travelPlanningInputData.getEndBusStopId()));
                    travelOptionList.add(travelOption);
                }
            }
        }
        return travelOptionList;
    }

    private List<TravelOption> findTravelOptionsWithOneChange(TravelPlanningInputData travelPlanningInputData) {
        List<TravelOption> travelOptionList = new ArrayList<>();
        BusStop commonBusStop;
        List<BusRoute> startingBusRouteList = this.busRouteDAO.getBusRoutesByBusStop(travelPlanningInputData.getStartingBusStopId());
        List<BusRoute> endBusRouteList = this.busRouteDAO.getBusRoutesByBusStop(travelPlanningInputData.getEndBusStopId());

        for (BusRoute startingBusRoute : startingBusRouteList) {
            for (BusRoute endBusRoute : endBusRouteList) {
                if ((commonBusStop = findCommonBusRoutesPoint(startingBusRoute, endBusRoute)) != null) {
                    TravelOption travelOption = new TravelOption();
                    BusRouteTrip firstTrip = new BusRouteTrip(startingBusRoute, travelPlanningInputData.getStartingBusStopId(), commonBusStop.getId());
                    BusRouteTrip secondTrip = new BusRouteTrip(endBusRoute, commonBusStop.getId(), travelPlanningInputData.getEndBusStopId());
                    travelOption.getBusRouteTripList().add(firstTrip);
                    travelOption.getBusRouteTripList().add(secondTrip);
                    travelOptionList.add(travelOption);
                }
            }
        }
        return travelOptionList;
    }

    private LocalTime findClosestDepartureTime(LocalDate travelDate, LocalTime travelStartingTime, int busRouteId, int busStopId, int directionId) {
        List<LocalTime> departureTimeList;
        if (travelDate.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
            departureTimeList=new ArrayList<>(this.timetableDAO.getTimetable(busStopId, busRouteId, directionId).getSundayAndHolidayDepartureTimes());
        } else if(travelDate.getDayOfWeek().equals(DayOfWeek.SATURDAY)){
            departureTimeList=new ArrayList<>(this.timetableDAO.getTimetable(busStopId, busRouteId, directionId).getSaturdayDepartureTimes());
        } else {
            departureTimeList=new ArrayList<>(this.timetableDAO.getTimetable(busStopId, busRouteId, directionId).getWeekdayDepartureTimes());
        }
        Collections.sort(departureTimeList);
        for (LocalTime departureTime : departureTimeList) {
            if (departureTime.isAfter(travelStartingTime)) {
                return departureTime;
            }
        }
        throw new IllegalArgumentException("No departure time for given travel starting time!");
    }

    private BusStop findCommonBusRoutesPoint(BusRoute firstBusRoute, BusRoute secondBusRoute) {
        for (Map.Entry<Integer, BusStop> integerBusStopEntry1 : firstBusRoute.getPassedBusStops().entrySet()) {
            for (Map.Entry<Integer, BusStop> integerBusStopEntry2 : secondBusRoute.getPassedBusStops().entrySet()) {
                if (integerBusStopEntry1.getValue().getId() == integerBusStopEntry2.getValue().getId()) {
                    return integerBusStopEntry1.getValue();
                }
            }
        }
        return null;
    }

    private class TravelOption {

        private List<BusRouteTrip> busRouteTripList = new ArrayList<>();

        public TravelOption() {

        }

        public List<BusRouteTrip> getBusRouteTripList() {
            return this.busRouteTripList;
        }

        public void setBusRouteTripList(List<BusRouteTrip> busRouteTripList) {
            this.busRouteTripList = busRouteTripList;
        }
    }

    private class BusRouteTrip {
        private BusRoute busRoute;
        private List<Integer> passedBusStopIdList = new ArrayList<>();

        public BusRouteTrip(BusRoute busRoute, int startingBusStopId, int endBusStopId) {
            this.busRoute = busRoute;
            fillInPassedBusStopList(startingBusStopId, endBusStopId);
        }

        public BusRoute getBusRoute() {
            return this.busRoute;
        }

        public void setBusRoute(BusRoute busRoute) {
            this.busRoute = busRoute;
        }

        public List<Integer> getPassedBusStopIdList() {
            return this.passedBusStopIdList;
        }

        public void setPassedBusStopIdList(List<Integer> passedBusStopIdList) {
            this.passedBusStopIdList = passedBusStopIdList;
        }

        private void fillInPassedBusStopList(int startingBusStopId, int endBusStopId) {
            int startingBusStopNumber = getBusStopNumber(startingBusStopId);
            int endBusStopNumber = getBusStopNumber(endBusStopId);
            if (startingBusStopNumber < endBusStopNumber) {
                for (int i = startingBusStopNumber; i < endBusStopNumber + 1; i++) {
                    this.passedBusStopIdList.add(this.busRoute.getPassedBusStops().get(i).getId());
                }
            } else {
                for (int i = startingBusStopNumber; i > endBusStopNumber - 1; i--) {
                    this.passedBusStopIdList.add(this.busRoute.getPassedBusStops().get(i).getId());
                }
            }
        }

        private int getBusStopNumber(int busStopId) {
            for (Map.Entry<Integer, BusStop> busStopNumberBusStopEntry : this.busRoute.getPassedBusStops().entrySet()) {
                if (busStopNumberBusStopEntry.getValue().getId() == busStopId) {
                    return busStopNumberBusStopEntry.getKey();
                }
            }
            throw new IllegalArgumentException("Wrong bus stop id!");
        }


    }

}
