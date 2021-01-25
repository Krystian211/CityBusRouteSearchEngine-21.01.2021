package com.github.krystian211.city.bus.route.search.engine.model.view;

import java.util.Objects;

public class SimpleRouteSearchData {
    private int startingStreetId;
    private int endStreetId;

    public SimpleRouteSearchData(int startingStreetId, int endStreetId) {
        this.startingStreetId = startingStreetId;
        this.endStreetId = endStreetId;
    }

    public SimpleRouteSearchData() {
    }

    public int getStartingStreetId() {
        return startingStreetId;
    }

    public void setStartingStreetId(int startingStreetId) {
        this.startingStreetId = startingStreetId;
    }

    public int getEndStreetId() {
        return endStreetId;
    }

    public void setEndStreetId(int endStreetId) {
        this.endStreetId = endStreetId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleRouteSearchData that = (SimpleRouteSearchData) o;
        return startingStreetId == that.startingStreetId &&
                endStreetId == that.endStreetId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(startingStreetId, endStreetId);
    }
}
