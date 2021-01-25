package com.github.krystian211.city.bus.route.search.engine.dao;

import com.github.krystian211.city.bus.route.search.engine.model.Street;

import java.util.List;

public interface IStreetDAO {
    void persistStreet(Street street);
    List<Street> getAllStreets();
}
