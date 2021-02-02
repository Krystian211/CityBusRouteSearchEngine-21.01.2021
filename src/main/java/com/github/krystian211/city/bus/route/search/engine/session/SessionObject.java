package com.github.krystian211.city.bus.route.search.engine.session;

import com.github.krystian211.city.bus.route.search.engine.model.view.TravelPlanningInputData;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class SessionObject {
    private TravelPlanningInputData travelPlanningInputData;

    public TravelPlanningInputData getTravelPlanningInputData() {
        return travelPlanningInputData;
    }

    public void setTravelPlanningInputData(TravelPlanningInputData travelPlanningInputData) {
        this.travelPlanningInputData = travelPlanningInputData;
    }

    public TravelPlanningInputData pollTravelPlanningData(){
        TravelPlanningInputData copyOfTravelPlanningInputData =new TravelPlanningInputData();
        copyOfTravelPlanningInputData.setStartingBusStopId(this.travelPlanningInputData.getStartingBusStopId());
        copyOfTravelPlanningInputData.setEndBusStopId(this.travelPlanningInputData.getEndBusStopId());
        copyOfTravelPlanningInputData.setTravelStartingTime(this.travelPlanningInputData.getTravelStartingTime());
        copyOfTravelPlanningInputData.setTravelDate(this.travelPlanningInputData.getTravelDate());
        copyOfTravelPlanningInputData.setChangeNumber(this.travelPlanningInputData.getChangeNumber());
        this.travelPlanningInputData =null;
        return copyOfTravelPlanningInputData;
    }
}
