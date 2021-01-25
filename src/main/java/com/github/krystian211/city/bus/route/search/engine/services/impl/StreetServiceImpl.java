package com.github.krystian211.city.bus.route.search.engine.services.impl;

import com.github.krystian211.city.bus.route.search.engine.dao.IStreetDAO;
import com.github.krystian211.city.bus.route.search.engine.model.Street;
import com.github.krystian211.city.bus.route.search.engine.services.IStreetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StreetServiceImpl implements IStreetService {

    @Autowired
    IStreetDAO streetDAO;

    @Override
    public List<Street> getAllStreets() {
        return streetDAO.getAllStreets();
    }

}
