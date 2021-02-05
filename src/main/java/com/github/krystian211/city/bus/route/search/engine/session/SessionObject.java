package com.github.krystian211.city.bus.route.search.engine.session;

import com.github.krystian211.city.bus.route.search.engine.model.view.TravelPlanningInputData;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class SessionObject {
    private TravelPlanningInputData travelPlanningInputData;
    private boolean travelPlanningInputDataWaitingForProcessing=false;

    public TravelPlanningInputData getTravelPlanningInputData() {
        return travelPlanningInputData;
    }

    public void setTravelPlanningInputData(TravelPlanningInputData travelPlanningInputData) {
        this.travelPlanningInputData = travelPlanningInputData;
    }

    public boolean isTravelPlanningInputDataAvailable(){
        return this.travelPlanningInputData != null;
    }

    public boolean isTravelPlanningInputDataWaitingForProcessing() {
        return this.travelPlanningInputDataWaitingForProcessing;
    }

    public void setTravelPlanningInputDataWaitingForProcessing(boolean travelPlanningInputDataWaitingForProcessing) {
        this.travelPlanningInputDataWaitingForProcessing = travelPlanningInputDataWaitingForProcessing;
    }

    public TravelPlanningInputData pollTravelPlanningInputData(){
        if (this.getTravelPlanningInputData()==null){
            return null;
        }
        TravelPlanningInputData copyOfTravelPlanningInputData =new TravelPlanningInputData();
        copyOfTravelPlanningInputData.setStartingBusStopId(this.travelPlanningInputData.getStartingBusStopId());
        copyOfTravelPlanningInputData.setEndBusStopId(this.travelPlanningInputData.getEndBusStopId());
        copyOfTravelPlanningInputData.setUsersStartingTime(this.travelPlanningInputData.getUsersStartingTime());
        copyOfTravelPlanningInputData.setTravelDate(this.travelPlanningInputData.getTravelDate());
        copyOfTravelPlanningInputData.setChangeNumber(this.travelPlanningInputData.getChangeNumber());
        this.travelPlanningInputData =null;
        return copyOfTravelPlanningInputData;
    }
}
