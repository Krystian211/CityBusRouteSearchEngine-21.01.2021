package com.github.krystian211.city.bus.route.search.engine.controllers;

import com.github.krystian211.city.bus.route.search.engine.model.view.BusRouteBusStopsData;
import com.github.krystian211.city.bus.route.search.engine.model.view.TimetableDrawData;
import com.github.krystian211.city.bus.route.search.engine.model.view.TravelPlanningInputData;
import com.github.krystian211.city.bus.route.search.engine.services.*;
import com.github.krystian211.city.bus.route.search.engine.session.SessionObject;
import com.github.krystian211.city.bus.route.search.engine.utils.Sorter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CommonController {

    @Autowired
    IStreetService streetService;

    @Autowired
    IBusStopService busStopService;

    @Autowired
    IBusRouteService busRouteService;

    @Autowired
    ITimetableService timetableService;

    @Autowired
    SessionObject sessionObject;

    @Autowired
    ITravelPlanner travelPlanner;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showMainPage(Model model) {
        model.addAttribute("busRouteList", this.busRouteService.getAllBusRoutes());
        return "main-page";
    }

    @RequestMapping(value = "/streets", method = RequestMethod.GET)
    public String showStreets(Model model) {
        model.addAttribute("streetList", Sorter.sort(this.streetService.getAllStreets()));
        return "streets";
    }

    @RequestMapping(value = {"/streets/bus-stops", "/bus-stops"}, method = RequestMethod.GET)
    public String showBusStopsOnStreet(Model model,
                                       @RequestParam(defaultValue = "0") int streetId) {
        model.addAttribute("busStopList", Sorter.sort(this.busStopService.getBusStopsByStreet(streetId)));
        return "bus-stops";
    }

    @RequestMapping(value = {"/streets/bus-stops/bus-routes", "/bus-stops/bus-routes"}, method = RequestMethod.GET)
    public String showBusRoutesPassingThroughBusStop(Model model,
                                                     @RequestParam int busStopId) {
        model.addAttribute("timetableList", this.timetableService.getTimetablesByBusStop(busStopId));
        return "bus-routes";
    }

    @RequestMapping(value = {"/streets/bus-stops/bus-routes/timetable", "bus-stops/bus-routes/timetable", "bus-route-bus-stops/timetable"}, method = RequestMethod.GET)
    public String showTimetableByBusStopAndBusRouteAndDirection(Model model,
                                                                @RequestParam int busRouteId,
                                                                @RequestParam int busStopId,
                                                                @RequestParam int directionId) {
        model.addAttribute("timetableDrawData", new TimetableDrawData(this.timetableService.getTimetable(busStopId, busRouteId, directionId)));
        return "timetable";
    }

    @RequestMapping(value = {"/bus-route-bus-stops"}, method = RequestMethod.GET)
    public String showOnRouteBusStops(Model model,
                                      @RequestParam int busRouteId) {
        model.addAttribute("busRouteBusStopsData", new BusRouteBusStopsData(this.busRouteService.getBusRouteById(busRouteId)));
        return "bus-route-bus-stops";
    }

    @RequestMapping(value = "/travel-planning", method = RequestMethod.GET)
    public String travelPlanning(Model model) {
        model.addAttribute("travelOptionOutputDataList", this.travelPlanner.planTravel(this.sessionObject.getTravelPlanningInputData()));
        model.addAttribute("busStopList",this.busStopService.getAllBusStops());
        if (this.sessionObject.isTravelPlanningInputDataAvailable()) {
            model.addAttribute("travelPlanningInputData", this.sessionObject.pollTravelPlanningInputData());
        }else {
            model.addAttribute("travelPlanningInputData", TravelPlanningInputData.initialize(new TravelPlanningInputData()));
        }

        return "travel-planning";
    }

    @RequestMapping(value = "/travel-planning", method = RequestMethod.POST)
    public String travelPlanning(@ModelAttribute TravelPlanningInputData travelPlanningInputData) {
        this.sessionObject.setTravelPlanningInputData(travelPlanningInputData);
        this.sessionObject.setTravelPlanningInputDataWaitingForProcessing(true);
        return "redirect:/travel-planning";
    }



}
