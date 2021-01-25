package com.github.krystian211.city.bus.route.search.engine.utils;

import com.github.krystian211.city.bus.route.search.engine.model.BusRoute;
import com.github.krystian211.city.bus.route.search.engine.model.BusStop;
import com.github.krystian211.city.bus.route.search.engine.model.Street;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BusRouteSearchEngine {


    public static List<BusRoute> getBusRoutesByStreet(List<BusRoute> inputBusRouteList, Street inputStreet) {
        List<BusRoute> outputBusRouteList = new ArrayList<>();
        for (BusRoute verifiedBusRoute : inputBusRouteList) {
            for (Map.Entry<Integer, BusStop> integerBusStopEntry : verifiedBusRoute.getPassedBusStops().entrySet()) {
                for (Street busStopStreet : integerBusStopEntry.getValue().getStreets()) {
                    if (busStopStreet.equals(inputStreet)) {
                        outputBusRouteList.add(verifiedBusRoute);
                    }
                }
            }
        }
        return outputBusRouteList;
    }

    public static List<BusRoute> getBusRoutesByBusStop(List<BusRoute> inputBusRouteList, BusStop inputBusStop) {
        List<BusRoute> outputBusRouteList = new ArrayList<>();
        for (BusRoute verifiedBusRoute : inputBusRouteList) {
            for (Map.Entry<Integer, BusStop> integerBusStopEntry : verifiedBusRoute.getPassedBusStops().entrySet()) {
                if (integerBusStopEntry.getValue().equals(inputBusStop)) {
                    outputBusRouteList.add(verifiedBusRoute);
                }
            }
        }
        return outputBusRouteList;
    }

}
