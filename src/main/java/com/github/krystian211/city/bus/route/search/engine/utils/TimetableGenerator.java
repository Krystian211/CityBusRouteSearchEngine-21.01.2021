package com.github.krystian211.city.bus.route.search.engine.utils;

import com.github.krystian211.city.bus.route.search.engine.dao.*;
import com.github.krystian211.city.bus.route.search.engine.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.*;

@Component
public class TimetableGenerator {
    @Autowired
    IBusRouteDAO busRouteDAO;

    @Autowired
    IBusStopDAO busStopDAO;

    @Autowired
    ITravelTimeDAO travelTimeDAO;

    @Autowired
    ITimetableDAO timetableDAO;

    @Autowired
    IStreetDAO streetDAO;

    private int travelTimeIdCounter = 1;
    private int travelTimeMultiplier = 2;

    private List<BusStop> busStopList = new ArrayList<>();
    private List<Street> streetList = new ArrayList<>();
    private List<String> busStopNamesList = new ArrayList<>();
    private List<String> streetNameList = new ArrayList<>();
    private List<List<Street>> busStopStreetsList = new ArrayList<>();
    private List<TravelTime> travelTimeList = new ArrayList<>();
    private List<List<BusStop>> passedBusStopsList = new ArrayList<>();
    private List<BusRoute> busRouteList = new ArrayList<>();
    private List<Timetable> timetableList=new ArrayList<>();

    private LocalTime weekdayStartingTime = LocalTime.of(4, 0);
    private LocalTime weekdayEndTime = LocalTime.of(23, 0);
    private LocalTime saturdayStartingTime = LocalTime.of(4, 0);
    private LocalTime saturdayEndTime = LocalTime.of(23, 0);
    private LocalTime sundayStartingTime = LocalTime.of(6, 0);
    private LocalTime sundayEndTime = LocalTime.of(22, 0);


    public void fillInDatabase() {

        generate();

        for (Street street : streetList) {
            this.streetDAO.persistStreet(street);
        }

        for (BusStop busStop : this.busStopList) {
            this.busStopDAO.persistBusStop(busStop);
        }

        for (BusRoute busRoute : busRouteList) {
            this.busRouteDAO.persistBusRoute(busRoute);
        }

        for (Timetable timetable : this.timetableList) {
            this.timetableDAO.persistTimetable(timetable);
        }

    }

    private void generate() {
        fillInStreetNameList();
        fillInStreetList();
        fillInBusStopNameList();
        fillInBusStopStreetsList();
        fillInBusStopsListWithoutBusRoutes();
        fillInTravelTimeList();
        fillInPassedBusStopsList();
        fillInBusRouteList();
        fillInTimetableList();
    }

    private void fillInStreetNameList() {

        streetNameList.add("Świętokrzyska");
        streetNameList.add("Prosta");
        streetNameList.add("Raciborska");
        streetNameList.add("Osobna");
        streetNameList.add("Niwy");
        streetNameList.add("Ciekoty");
        streetNameList.add("Długa");
        streetNameList.add("Polewska");
        streetNameList.add("Al. Solidarności");
        streetNameList.add("Mała");
        streetNameList.add("Zagórska");
        streetNameList.add("Pawia");
        streetNameList.add("Bronicka");
        streetNameList.add("Witosa");
    }

    private void fillInBusStopNameList() {

        busStopNamesList.add("Świętokrzyska");
        busStopNamesList.add("Raciborska I");
        busStopNamesList.add("Prosta");
        busStopNamesList.add("Świętokrzyska / Prosta");
        busStopNamesList.add("Raciborska II");
        busStopNamesList.add("Osobna / Ciekoty");
        busStopNamesList.add("Osobna");
        busStopNamesList.add("Długa I");
        busStopNamesList.add("Długa II");
        busStopNamesList.add("Raciborska / Długa");
        busStopNamesList.add("Ciekoty");
        busStopNamesList.add("Niwy II");
        busStopNamesList.add("Niwy I");
        busStopNamesList.add("Al. Solidarności II");
        busStopNamesList.add("Mała / Polewska");
        busStopNamesList.add("Raciborska / Polewska");
        busStopNamesList.add("Ciekoty / Zagórska");
        busStopNamesList.add("Zagórska");
        busStopNamesList.add("Mała");
        busStopNamesList.add("Raciborska / Pawia");
        busStopNamesList.add("Al. Solidarności I");
        busStopNamesList.add("Raciborska III");
        busStopNamesList.add("Witosa");
        busStopNamesList.add("Bronicka");
    }

    private void fillInStreetList() {
        int id = 1;
        for (String name : streetNameList) {
            this.streetList.add(new Street(id, name));
            id++;
        }
    }

    private void fillInBusStopStreetsList() {

        busStopStreetsList.add(createBusStopStreetSubList("Świętokrzyska"));
        busStopStreetsList.add(createBusStopStreetSubList("Raciborska"));
        busStopStreetsList.add(createBusStopStreetSubList("Prosta"));
        busStopStreetsList.add(createBusStopStreetSubList("Świętokrzyska", "Prosta"));
        busStopStreetsList.add(createBusStopStreetSubList("Raciborska"));
        busStopStreetsList.add(createBusStopStreetSubList("Osobna", "Ciekoty"));
        busStopStreetsList.add(createBusStopStreetSubList("Osobna"));
        busStopStreetsList.add(createBusStopStreetSubList("Długa"));
        busStopStreetsList.add(createBusStopStreetSubList("Długa"));
        busStopStreetsList.add(createBusStopStreetSubList("Długa", "Raciborska"));
        busStopStreetsList.add(createBusStopStreetSubList("Ciekoty"));
        busStopStreetsList.add(createBusStopStreetSubList("Niwy"));
        busStopStreetsList.add(createBusStopStreetSubList("Niwy"));
        busStopStreetsList.add(createBusStopStreetSubList("Al. Solidarności", "Polewska"));
        busStopStreetsList.add(createBusStopStreetSubList("Mała", "Polewska"));
        busStopStreetsList.add(createBusStopStreetSubList("Raciborska", "Polewska"));
        busStopStreetsList.add(createBusStopStreetSubList("Ciekoty", "Zagórska"));
        busStopStreetsList.add(createBusStopStreetSubList("Zagórska"));
        busStopStreetsList.add(createBusStopStreetSubList("Mała"));
        busStopStreetsList.add(createBusStopStreetSubList("Raciborska", "Pawia"));
        busStopStreetsList.add(createBusStopStreetSubList("Al. Solidarności"));
        busStopStreetsList.add(createBusStopStreetSubList("Raciborska"));
        busStopStreetsList.add(createBusStopStreetSubList("Witosa"));
        busStopStreetsList.add(createBusStopStreetSubList("Bronicka"));
    }

    private void fillInTravelTimeList() {
        travelTimeList.add(createTravelTime(1, 4, 7));
        travelTimeList.add(createTravelTime(4, 1, 7));

        travelTimeList.add(createTravelTime(2, 5, 8));
        travelTimeList.add(createTravelTime(5, 2, 8));

        travelTimeList.add(createTravelTime(3, 4, 2));
        travelTimeList.add(createTravelTime(4, 3, 2));

        travelTimeList.add(createTravelTime(4, 5, 6));
        travelTimeList.add(createTravelTime(5, 4, 6));

        travelTimeList.add(createTravelTime(5, 6, 4));
        travelTimeList.add(createTravelTime(6, 5, 4));

        travelTimeList.add(createTravelTime(5, 10, 3));
        travelTimeList.add(createTravelTime(10, 5, 3));

        travelTimeList.add(createTravelTime(6, 7, 4));
        travelTimeList.add(createTravelTime(7, 6, 4));

        travelTimeList.add(createTravelTime(6, 11, 4));
        travelTimeList.add(createTravelTime(11, 6, 4));

        travelTimeList.add(createTravelTime(8, 9, 5));
        travelTimeList.add(createTravelTime(9, 8, 5));

        travelTimeList.add(createTravelTime(9, 10, 4));
        travelTimeList.add(createTravelTime(10, 9, 4));

        travelTimeList.add(createTravelTime(10, 11, 3));
        travelTimeList.add(createTravelTime(11, 10, 3));

        travelTimeList.add(createTravelTime(10, 16, 3));
        travelTimeList.add(createTravelTime(16, 10, 3));

        travelTimeList.add(createTravelTime(11, 12, 4));
        travelTimeList.add(createTravelTime(12, 11, 4));

        travelTimeList.add(createTravelTime(11, 16, 6));
        travelTimeList.add(createTravelTime(16, 11, 6));

        travelTimeList.add(createTravelTime(11, 17, 6));
        travelTimeList.add(createTravelTime(17, 11, 6));

        travelTimeList.add(createTravelTime(12, 13, 3));
        travelTimeList.add(createTravelTime(13, 12, 3));

        travelTimeList.add(createTravelTime(14, 15, 3));
        travelTimeList.add(createTravelTime(15, 14, 3));

        travelTimeList.add(createTravelTime(14, 21, 9));
        travelTimeList.add(createTravelTime(21, 14, 9));

        travelTimeList.add(createTravelTime(15, 16, 4));
        travelTimeList.add(createTravelTime(16, 15, 4));

        travelTimeList.add(createTravelTime(15, 19, 4));
        travelTimeList.add(createTravelTime(19, 15, 4));

        travelTimeList.add(createTravelTime(16, 17, 4));
        travelTimeList.add(createTravelTime(17, 16, 4));

        travelTimeList.add(createTravelTime(16, 20, 4));
        travelTimeList.add(createTravelTime(20, 16, 4));

        travelTimeList.add(createTravelTime(17, 18, 6));
        travelTimeList.add(createTravelTime(18, 17, 6));

        travelTimeList.add(createTravelTime(17, 20, 3));
        travelTimeList.add(createTravelTime(20, 17, 3));

        travelTimeList.add(createTravelTime(20, 22, 8));
        travelTimeList.add(createTravelTime(22, 20, 8));

        travelTimeList.add(createTravelTime(20, 24, 5));
        travelTimeList.add(createTravelTime(24, 20, 5));

        travelTimeList.add(createTravelTime(23, 24, 3));
        travelTimeList.add(createTravelTime(24, 23, 3));
    }

    private void fillInPassedBusStopsList() {
        this.passedBusStopsList.add(createPassedBusStopsSublist(1, 4, 5, 10, 16, 17, 18));
        this.passedBusStopsList.add(createPassedBusStopsSublist(2, 5, 10, 16, 20, 22));
        this.passedBusStopsList.add(createPassedBusStopsSublist(3, 4, 5, 10, 16, 20, 24, 23));
        this.passedBusStopsList.add(createPassedBusStopsSublist(13, 12, 11, 16, 15, 19));
        this.passedBusStopsList.add(createPassedBusStopsSublist(7, 6, 5, 10, 16, 15, 14, 21));
        this.passedBusStopsList.add(createPassedBusStopsSublist(8, 9, 10, 11, 12, 13));
        this.passedBusStopsList.add(createPassedBusStopsSublist(7, 6, 11, 17, 20, 22));
    }

    private void fillInBusRouteList() {
        for (int i = 1; i < 8; i++) {
            busRouteList.add(createBusRoute(i));
        }
    }

    private void fillInBusStopsListWithoutBusRoutes() {
        if (this.busStopStreetsList.size() != this.busStopNamesList.size()) {
            throw new IllegalArgumentException("List sizes differs!");
        }
        for (int i = 0; i < busStopNamesList.size(); i++) {
            BusStop busStop = new BusStop();
            busStop.setId(i + 1);
            busStop.setBusStopName(this.busStopNamesList.get(i));
            for (Street street : this.busStopStreetsList.get(i)) {
                busStop.getStreets().add(street);
            }
            this.busStopList.add(busStop);
        }
    }

    private List<Street> createBusStopStreetSubList(String street1) {
        List<Street> streets = new ArrayList<>();
        streets.add(findStreetByName(street1));
        return streets;
    }

    private List<Street> createBusStopStreetSubList(String street1, String street2) {
        List<Street> streets = new ArrayList<>();
        streets.add(findStreetByName(street1));
        streets.add(findStreetByName(street2));
        return streets;
    }

    private Street findStreetByName(String name) {
        for (Street street : this.streetList) {
            if (street.getName().equals(name)) {
                return street;
            }
        }
        throw new IllegalArgumentException("No street found!");
    }

    private void listBusStops() {
        for (BusStop busStop : busStopList) {
            System.out.println(busStop.getId() + " - " + busStop.getBusStopName());
        }
    }

    private BusStop findBusStopById(int id) {
        for (BusStop busStop : busStopList) {
            if (busStop.getId() == id) {
                return busStop;
            }
        }
        throw new IllegalArgumentException("No bus stop found!");
    }

    private TravelTime createTravelTime(int startingPoint, int endPoint, int travelTimeInMinutes) {
        TravelTime travelTime = new TravelTime(travelTimeIdCounter, findBusStopById(startingPoint), findBusStopById(endPoint), travelTimeInMinutes);
        travelTimeIdCounter++;
        return travelTime;
    }

    private List<BusStop> createPassedBusStopsSublist(int... busStopIds) {
        List<BusStop> busStopList = new ArrayList<>();
        for (int busStopId : busStopIds) {
            busStopList.add(findBusStopById(busStopId));
        }
        return busStopList;
    }

    private BusRoute createBusRoute(int busRouteNumber) {
        BusRoute busRoute = new BusRoute();
        busRoute.setId(busRouteNumber);
        busRoute.setBusRouteNumber(busRouteNumber);
        int order = 0;
        for (BusStop busStop : this.passedBusStopsList.get(busRouteNumber - 1)) {
            busRoute.getPassedBusStops().put(order, busStop);
            order++;
        }
        return busRoute;
    }

    private List<Timetable> generateTimetablesForBusRoute(BusRoute busRoute) {
        List<Timetable> timetables = new ArrayList<>();
        Timetable tmpTimetable;
        for (Map.Entry<Integer, BusStop> entry : busRoute.getPassedBusStops().entrySet()) {
            //generating empty timetables in both directions
            tmpTimetable = new Timetable();
            tmpTimetable.setId(0);
            tmpTimetable.setBusRoute(busRoute);
            tmpTimetable.setParentBusStop(entry.getValue());
            tmpTimetable.setDirection(busRoute.getPassedBusStops().get(0));
            timetables.add(tmpTimetable);

            tmpTimetable = new Timetable();
            tmpTimetable.setId(0);
            tmpTimetable.setBusRoute(busRoute);
            tmpTimetable.setParentBusStop(entry.getValue());
            tmpTimetable.setDirection(busRoute.getPassedBusStops().get(busRoute.getPassedBusStops().size() - 1));
            timetables.add(tmpTimetable);
        }

        Bus bus = new Bus(busRoute,
                busRoute.getPassedBusStops().get(0),
                busRoute.getPassedBusStops().get(busRoute.getPassedBusStops().size() - 1),
                timetables);
        bus.fillInTimetables();

        bus = new Bus(busRoute,
                busRoute.getPassedBusStops().get(busRoute.getPassedBusStops().size() - 1),
                busRoute.getPassedBusStops().get(0),
                timetables);
        bus.fillInTimetables();

        return timetables;
    }

    private void fillInTimetableList(){
        for (BusRoute busRoute : this.busRouteList) {
            this.timetableList.addAll(generateTimetablesForBusRoute(busRoute));
        }
    }

    private Timetable getTimetableByDirectionAndBusStop(List<Timetable> timetables, BusStop direction, BusStop actualBustStop) {
        for (Timetable timetable : timetables) {
            if ((timetable.getDirection() == direction) && (timetable.getParentBusStop() == actualBustStop)) {
                return timetable;
            }
        }
        throw new IllegalArgumentException("No timetable found!");
    }

    private void updateTimetable(List<Timetable> timetables, BusStop direction, BusStop actualBustStop, int dayOfWeek, LocalTime arrivalTime) {
        Timetable tmp = getTimetableByDirectionAndBusStop(timetables, direction, actualBustStop);
        if ((dayOfWeek >= 1) && (dayOfWeek <= 5)) {
            tmp.getWeekdaysArrivalTimes().add(arrivalTime);
        } else if (dayOfWeek == 6) {
            tmp.getSaturdayArrivalTimes().add(arrivalTime);
        } else {
            tmp.getSaturdayArrivalTimes().add(arrivalTime);
        }
    }

    private int getTravelTime(BusStop start, BusStop stop) {
        for (TravelTime travelTime : this.travelTimeList) {
            if ((travelTime.getStartingPoint() == start) && (travelTime.getEndpoint() == stop)) {
                return this.travelTimeMultiplier * travelTime.getTravelTime();
            }
        }
        System.out.println();
        System.out.println(start.getId()+" - "+stop.getId());
        throw new IllegalArgumentException("No travel time found!");
    }

    private class Bus {
        private BusRoute busRoute;
        private BusStop startingPoint;
        private BusStop direction;
        private BusStop actualPosition;
        private List<Timetable> emptyTimetables;
        private int lastStopNumber;
        private LocalTime presentTime;

        private int delay;

        public Bus(BusRoute busRoute, BusStop startingPoint, BusStop direction, List<Timetable> emptyTimetables) {
            this.busRoute = busRoute;
            this.startingPoint = startingPoint;
            this.direction = direction;
            this.emptyTimetables = emptyTimetables;
            this.actualPosition = startingPoint;
            this.lastStopNumber = this.busRoute.getPassedBusStops().size() - 1;
            Random random = new Random();
            this.delay = random.nextInt(11);
        }

        private void goToAnotherBusStop() {
            int actualStopNumber = 0;
            BusStop earlierBusStop = this.actualPosition;
            for (Map.Entry<Integer, BusStop> entry : busRoute.getPassedBusStops().entrySet()) {
                if (entry.getValue() == this.actualPosition) {
                    actualStopNumber = entry.getKey();
                }
            }
            if (this.direction == this.busRoute.getPassedBusStops().get(lastStopNumber)) {
                if (actualStopNumber < lastStopNumber) {
                    actualStopNumber++;
                    this.actualPosition = this.busRoute.getPassedBusStops().get(actualStopNumber);
                    this.presentTime = this.presentTime.plusMinutes(getTravelTime(earlierBusStop, this.actualPosition));
                } else {
                    this.direction = this.busRoute.getPassedBusStops().get(0);
                    this.presentTime = this.presentTime.plusMinutes(1);
                }
            } else if (this.direction == this.busRoute.getPassedBusStops().get(0)) {
                if (actualStopNumber > 0) {
                    actualStopNumber--;
                    this.actualPosition = this.busRoute.getPassedBusStops().get(actualStopNumber);
                } else {
                    this.direction = this.busRoute.getPassedBusStops().get(lastStopNumber);
                    this.presentTime = this.presentTime.plusMinutes(1);
                }
            }
        }

        public void fillInWeekdayTimetables() {
            this.presentTime = weekdayStartingTime.plusMinutes(delay);
            updateTimetable(this.emptyTimetables, this.direction, this.actualPosition, 1, presentTime);
            while ((this.presentTime.isBefore(weekdayEndTime.minusMinutes(30)) || (this.actualPosition != this.direction))) {
                goToAnotherBusStop();
                updateTimetable(this.emptyTimetables, this.direction, this.actualPosition, 1, presentTime);
            }
        }

        public void fillInSaturdayTimetables() {
            this.presentTime = saturdayStartingTime.plusMinutes(delay);
            updateTimetable(this.emptyTimetables, this.direction, this.actualPosition, 6, presentTime);
            while ((this.presentTime.isBefore(saturdayEndTime.minusMinutes(30)) || (this.actualPosition != this.direction))) {
                goToAnotherBusStop();
                updateTimetable(this.emptyTimetables, this.direction, this.actualPosition, 1, presentTime);
            }
        }

        public void fillInSundayTimetables() {
            this.presentTime = sundayStartingTime.plusMinutes(delay);
            updateTimetable(this.emptyTimetables, this.direction, this.actualPosition, 7, presentTime);
            while ((this.presentTime.isBefore(sundayEndTime.minusMinutes(30)) || (this.actualPosition != this.direction))) {
                goToAnotherBusStop();
                updateTimetable(this.emptyTimetables, this.direction, this.actualPosition, 1, presentTime);
            }
        }

        public void fillInTimetables() {
            fillInWeekdayTimetables();
            fillInSaturdayTimetables();
            fillInSundayTimetables();
        }
    }


}
