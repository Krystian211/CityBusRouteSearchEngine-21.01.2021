package com.github.krystian211.city.bus.route.search.engine.controllers;

import com.github.krystian211.city.bus.route.search.engine.model.Street;
import com.github.krystian211.city.bus.route.search.engine.model.view.SimpleRouteSearchData;
import com.github.krystian211.city.bus.route.search.engine.services.IBusRouteService;
import com.github.krystian211.city.bus.route.search.engine.services.IBusStopService;
import com.github.krystian211.city.bus.route.search.engine.services.IStreetService;
import com.github.krystian211.city.bus.route.search.engine.utils.Sorter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;

@Controller
public class CommonController {

    @Autowired
    IStreetService streetService;

    @Autowired
    IBusStopService busStopService;

    @Autowired
    IBusRouteService busRouteService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showMainPage(Model model) {
        model.addAttribute("busRouteList", this.busRouteService.getAllBusRoutes());
        return "main-page";
    }

    @RequestMapping(value = "/timetables/streets", method = RequestMethod.GET)
    public String showStreets(Model model) {
        model.addAttribute("streetList", Sorter.sort(this.streetService.getAllStreets()));
        return "streets";
    }

    @RequestMapping(value = "/timetables/streets/bus-stops", method = RequestMethod.GET)
    public String showBusStopsOnStreet(Model model, @RequestParam int streetId) {
        model.addAttribute("busStopList", Sorter.sort(this.busStopService.getBusStopsByStreet(streetId)));
        return "bus-stops";
    }

    @RequestMapping(value = "/timetables/streets/bus-stops/bus-routes", method = RequestMethod.GET)
    public String showBusRoutesPassingThroughBusStop(Model model, @RequestParam int busStopId) {
        model.addAttribute("busRouteList", Sorter.sort(this.busRouteService.getBusRoutesByBusStop(busStopId)));
        return "bus-routes";
    }

    @RequestMapping(value = "/simple-route-search", method = RequestMethod.POST)
    public String simpleRouteSearch(@ModelAttribute SimpleRouteSearchData simpleRouteSearchData) {
        System.out.println(simpleRouteSearchData.getStartingStreetId());
        System.out.println(simpleRouteSearchData.getEndStreetId());
        return "redirect:/";
    }

}
