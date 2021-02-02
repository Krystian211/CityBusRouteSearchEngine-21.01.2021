package com.github.krystian211.city.bus.route.search.engine.model;

import javax.persistence.*;
import java.text.Collator;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;

@Entity(name = "busStop")
@Table(name = "tBusStop")
public class BusStop implements Comparable<BusStop> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(joinColumns = @JoinColumn(name = "busStopId"),
            inverseJoinColumns = @JoinColumn(name = "streetId"))
    private Set<Street> streets = new TreeSet<>();
    @ManyToMany(fetch = FetchType.EAGER,
            mappedBy = "passedBusStops")
    private Set<BusRoute> passingBusRoutes = new TreeSet<>();

    public BusStop(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public BusStop() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<BusRoute> getPassingBusRoutes() {
        return this.passingBusRoutes;
    }

    public void setPassingBusRoutes(Set<BusRoute> passingBusRoutes) {
        this.passingBusRoutes = passingBusRoutes;
    }

    public Set<Street> getStreets() {
        return this.streets;
    }

    public void setStreets(Set<Street> streets) {
        this.streets = streets;
    }

    @Override
    public int compareTo(BusStop o) {
        Collator collator=Collator.getInstance(new Locale("pl","PL"));
        return collator.compare(this.getName(),o.getName());
    }

}
