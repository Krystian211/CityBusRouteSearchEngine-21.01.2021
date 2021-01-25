package com.github.krystian211.city.bus.route.search.engine.utils;

import java.util.Collections;
import java.util.List;

public class Sorter {
    public static <T extends Comparable<T>> List<T> sort(List<T> list){
        Collections.sort(list);
        return list;
    }
}
