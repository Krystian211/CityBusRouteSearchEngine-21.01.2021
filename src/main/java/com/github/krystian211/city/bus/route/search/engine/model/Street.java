package com.github.krystian211.city.bus.route.search.engine.model;

import javax.persistence.*;
import java.text.Collator;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;

@Entity(name = "street")
@Table(name = "tStreet")
public class Street implements Comparable<Street> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @ManyToMany(fetch = FetchType.EAGER,
            mappedBy = "streets")
    private Set<BusStop> onStreetBusStops = new TreeSet<>();

    public Street(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Street() {
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

    public Set<BusStop> getOnStreetBusStops() {
        return this.onStreetBusStops;
    }

    public void setOnStreetBusStops(Set<BusStop> onStreetBusStops) {
        this.onStreetBusStops = onStreetBusStops;
    }

    @Override
    public int compareTo(Street o) {
        Collator collator=Collator.getInstance(new Locale("pl","PL"));
        return collator.compare(this.getName(),o.getName());
    }
}
