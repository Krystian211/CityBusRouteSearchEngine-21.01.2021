package com.github.krystian211.city.bus.route.search.engine;

import com.github.krystian211.city.bus.route.search.engine.model.view.TravelPlanningInputData;
import com.github.krystian211.city.bus.route.search.engine.services.ITravelPlanner;
import com.github.krystian211.city.bus.route.search.engine.utils.TimetableGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class,args);
      //TODO remove generator
/*        ApplicationContext context = new AnnotationConfigApplicationContext(com.github.krystian211.city.bus.route.search.engine.configuration.AppConfiguration.class);
        context.getBean(TimetableGenerator.class).fillInDatabase();*/
    }
}