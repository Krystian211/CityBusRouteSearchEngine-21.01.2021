package com.github.krystian211.city.bus.route.search.engine.services;

import com.github.krystian211.city.bus.route.search.engine.model.view.TravelPlanningInputData;
import com.github.krystian211.city.bus.route.search.engine.model.view.TravelPlanningOutputData;

import java.util.List;

public interface ITravelPlanner {
    public List<TravelPlanningOutputData> planTravel(TravelPlanningInputData travelPlanningInputData);
}
