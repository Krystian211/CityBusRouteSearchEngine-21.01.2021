package com.github.krystian211.city.bus.route.search.engine.services.impl;

import com.github.krystian211.city.bus.route.search.engine.dao.IBusRouteDAO;
import com.github.krystian211.city.bus.route.search.engine.dao.IBusStopDAO;
import com.github.krystian211.city.bus.route.search.engine.dao.ITimetableDAO;
import com.github.krystian211.city.bus.route.search.engine.dao.ITravelTimeDAO;
import com.github.krystian211.city.bus.route.search.engine.model.BusRoute;
import com.github.krystian211.city.bus.route.search.engine.model.BusStop;
import com.github.krystian211.city.bus.route.search.engine.model.view.BusRouteTrip;
import com.github.krystian211.city.bus.route.search.engine.model.view.TravelPlanningInputData;
import com.github.krystian211.city.bus.route.search.engine.model.view.TravelOptionOutputData;
import com.github.krystian211.city.bus.route.search.engine.services.ITravelPlanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Service
public class TravelPlannerImpl implements ITravelPlanner {

    private static final int TRAVEL_TIME_MULTIPLIER=2;

    @Autowired
    IBusRouteDAO busRouteDAO;

    @Autowired
    IBusStopDAO busStopDAO;

    @Autowired
    ITravelTimeDAO travelTimeDAO;

    @Autowired
    ITimetableDAO timetableDAO;

    @Override
    public List<TravelOptionOutputData> planTravel(TravelPlanningInputData travelPlanningInputData) {
        if (travelPlanningInputData == null) {
            return null;
        } else {
            List<TravelOptionOutputData> travelOptionOutputDataList = new ArrayList<>();
            for (TravelOptionWithoutTime travelOptionWithoutTime : getTravelOptionWithoutTimeList(travelPlanningInputData)) {
                travelOptionOutputDataList.add(travelOptionWithoutTimeToTravelOptionOutputData(travelOptionWithoutTime, travelPlanningInputData));
            }
            return travelOptionOutputDataList;
        }
    }

    private List<TravelOptionWithoutTime> getTravelOptionWithoutTimeList(TravelPlanningInputData travelPlanningInputData) {
        List<TravelOptionWithoutTime> travelOptionWithoutTimeList = new ArrayList<>();
        List<TravelOptionWithoutTime> tmpTravelOptionWithoutTimeList;
        if ((tmpTravelOptionWithoutTimeList = findTravelOptionsWithoutChanges(travelPlanningInputData)).size() != 0) {
            travelOptionWithoutTimeList.addAll(tmpTravelOptionWithoutTimeList);
        } else if ((travelPlanningInputData.getChangeNumber() >= 1) && ((tmpTravelOptionWithoutTimeList = findTravelOptionsWithOneChange(travelPlanningInputData)).size() != 0)) {
            travelOptionWithoutTimeList.addAll(tmpTravelOptionWithoutTimeList);
        }
        return travelOptionWithoutTimeList;
    }

    private TravelOptionOutputData travelOptionWithoutTimeToTravelOptionOutputData(TravelOptionWithoutTime travelOptionWithoutTime, TravelPlanningInputData travelPlanningInputData) {
        List<BusRouteTrip> busRouteTripList = new ArrayList<>();
        LocalTime usersStartingTime = travelPlanningInputData.getUsersStartingTime();
        Iterator<BusRouteTripWithoutTime> iterator = travelOptionWithoutTime.getBusRouteTripWithoutTimeList().iterator();
        while (iterator.hasNext()) {
            busRouteTripList.add(busRouteTripWithoutTimeToBusRouteTrip(iterator.next(), usersStartingTime, travelPlanningInputData.getTravelDate()));
            if (iterator.hasNext()) {
                usersStartingTime = busRouteTripList.get(busRouteTripList.size() - 1).getActualEndTime();
            }
        }
        TravelOptionOutputData travelOptionOutputData = new TravelOptionOutputData();
        travelOptionOutputData.setBusRouteTripList(busRouteTripList);
        travelOptionOutputData.setEstimatedTravelTime(Duration.between(busRouteTripList.get(0).getActualStartingTime(),
                busRouteTripList.get(busRouteTripList.size() - 1).getActualEndTime()).toMinutes());
        return travelOptionOutputData;
    }

    private BusRouteTrip busRouteTripWithoutTimeToBusRouteTrip(BusRouteTripWithoutTime busRouteTripWithoutTime, LocalTime usersStartingTime, LocalDate travelDate) {
        int travelTimeInMinutes = 0;
        BusRouteTrip busRouteTrip = new BusRouteTrip();
        busRouteTrip.setBusRoute(busRouteTripWithoutTime.getBusRoute());
        busRouteTrip.setActualStartingTime(findClosestDepartureTime(travelDate,
                usersStartingTime,
                busRouteTripWithoutTime.getBusRoute().getId(),
                busRouteTripWithoutTime.getPassedBusStopIdList().get(0),
                getDirectionId(busRouteTripWithoutTime.getBusRoute(), busRouteTripWithoutTime.getPassedBusStopIdList().get(0), busRouteTripWithoutTime.getPassedBusStopIdList().get(busRouteTripWithoutTime.getPassedBusStopIdList().size()-1))));
        for (int i = 0; i < busRouteTripWithoutTime.getPassedBusStopIdList().size() - 1; i++) {
            int previousBusStopId = busRouteTripWithoutTime.getPassedBusStopIdList().get(i);
            int currentBusStopId = busRouteTripWithoutTime.getPassedBusStopIdList().get(i + 1);
            travelTimeInMinutes += this.travelTimeDAO.getTravelTimeByBusStopIds(previousBusStopId, currentBusStopId).getTravelTime()*TRAVEL_TIME_MULTIPLIER;
        }
        busRouteTrip.setActualEndTime(busRouteTrip.getActualStartingTime().plusMinutes(travelTimeInMinutes));
        busRouteTrip.setStartingBusStop(this.busStopDAO.getBusStopById(busRouteTripWithoutTime.passedBusStopIdList.get(0)));
        busRouteTrip.setEndBusStop(this.busStopDAO.getBusStopById(busRouteTripWithoutTime.getPassedBusStopIdList().get(busRouteTripWithoutTime.passedBusStopIdList.size()-1)));
        return busRouteTrip;
    }

    private int getDirectionId(BusRoute busRoute, int firstBusStopId, int secondBusStopId) {
        for (Map.Entry<Integer, BusStop> busStopNumberBusStopEntry : busRoute.getPassedBusStops().entrySet()) {
            if (busStopNumberBusStopEntry.getValue().getId() == firstBusStopId) {
                System.out.println(busRoute.getPassedBusStops().get(busRoute.getPassedBusStops().size() - 1).getId());
                return busRoute.getPassedBusStops().get(busRoute.getPassedBusStops().size() - 1).getId();
            } else if (busStopNumberBusStopEntry.getValue().getId() == secondBusStopId) {
                System.out.println(busStopNumberBusStopEntry.getValue().getId() == secondBusStopId);
                return busRoute.getPassedBusStops().get(0).getId();
            }
        }
        throw new IllegalArgumentException("Incorrect bus stops!");
    }

    private List<TravelOptionWithoutTime> findTravelOptionsWithoutChanges(TravelPlanningInputData travelPlanningInputData) {
        List<TravelOptionWithoutTime> travelOptionWithoutTimeList = new ArrayList<>();
        for (BusRoute busRoute : this.busRouteDAO.getBusRoutesByBusStop(travelPlanningInputData.getStartingBusStopId())) {
            for (Map.Entry<Integer, BusStop> busStopNumberBusStopEntry : busRoute.getPassedBusStops().entrySet()) {
                if (busStopNumberBusStopEntry.getValue().getId() == travelPlanningInputData.getEndBusStopId()) {
                    TravelOptionWithoutTime travelOptionWithoutTime = new TravelOptionWithoutTime();
                    travelOptionWithoutTime.getBusRouteTripWithoutTimeList().add(new BusRouteTripWithoutTime(busRoute, travelPlanningInputData.getStartingBusStopId(), travelPlanningInputData.getEndBusStopId()));
                    travelOptionWithoutTimeList.add(travelOptionWithoutTime);
                }
            }
        }
        return travelOptionWithoutTimeList;
    }

    private List<TravelOptionWithoutTime> findTravelOptionsWithOneChange(TravelPlanningInputData travelPlanningInputData) {
        List<TravelOptionWithoutTime> travelOptionWithoutTimeList = new ArrayList<>();
        BusStop commonBusStop;
        List<BusRoute> startingBusRouteList = this.busRouteDAO.getBusRoutesByBusStop(travelPlanningInputData.getStartingBusStopId());
        List<BusRoute> endBusRouteList = this.busRouteDAO.getBusRoutesByBusStop(travelPlanningInputData.getEndBusStopId());

        for (BusRoute startingBusRoute : startingBusRouteList) {
            for (BusRoute endBusRoute : endBusRouteList) {
                if ((commonBusStop = findCommonBusRoutesPoint(startingBusRoute, endBusRoute)) != null) {
                    TravelOptionWithoutTime travelOptionWithoutTime = new TravelOptionWithoutTime();
                    BusRouteTripWithoutTime firstTrip = new BusRouteTripWithoutTime(startingBusRoute, travelPlanningInputData.getStartingBusStopId(), commonBusStop.getId());
                    BusRouteTripWithoutTime secondTrip = new BusRouteTripWithoutTime(endBusRoute, commonBusStop.getId(), travelPlanningInputData.getEndBusStopId());
                    travelOptionWithoutTime.getBusRouteTripWithoutTimeList().add(firstTrip);
                    travelOptionWithoutTime.getBusRouteTripWithoutTimeList().add(secondTrip);
                    travelOptionWithoutTimeList.add(travelOptionWithoutTime);
                }
            }
        }
        return travelOptionWithoutTimeList;
    }

    private LocalTime findClosestDepartureTime(LocalDate travelDate, LocalTime usersStartingTime, int busRouteId, int busStopId, int directionId) {
        List<LocalTime> departureTimeList;
        if (travelDate.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
            departureTimeList = new ArrayList<>(this.timetableDAO.getTimetable(busStopId, busRouteId, directionId).getSundayAndHolidayDepartureTimes());
        } else if (travelDate.getDayOfWeek().equals(DayOfWeek.SATURDAY)) {
            departureTimeList = new ArrayList<>(this.timetableDAO.getTimetable(busStopId, busRouteId, directionId).getSaturdayDepartureTimes());
        } else {
            departureTimeList = new ArrayList<>(this.timetableDAO.getTimetable(busStopId, busRouteId, directionId).getWeekdayDepartureTimes());
        }
        Collections.sort(departureTimeList);
        for (LocalTime departureTime : departureTimeList) {
            if (departureTime.isAfter(usersStartingTime) || usersStartingTime.equals(departureTime)) {
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

    private class TravelOptionWithoutTime {

        private List<BusRouteTripWithoutTime> busRouteTripWithoutTimeList = new ArrayList<>();

        public TravelOptionWithoutTime() {

        }

        public List<BusRouteTripWithoutTime> getBusRouteTripWithoutTimeList() {
            return this.busRouteTripWithoutTimeList;
        }

        public void setBusRouteTripWithoutTimeList(List<BusRouteTripWithoutTime> busRouteTripWithoutTimeList) {
            this.busRouteTripWithoutTimeList = busRouteTripWithoutTimeList;
        }
    }

    private class BusRouteTripWithoutTime {
        private BusRoute busRoute;
        private List<Integer> passedBusStopIdList = new ArrayList<>();

        public BusRouteTripWithoutTime(BusRoute busRoute, int startingBusStopId, int endBusStopId) {
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
