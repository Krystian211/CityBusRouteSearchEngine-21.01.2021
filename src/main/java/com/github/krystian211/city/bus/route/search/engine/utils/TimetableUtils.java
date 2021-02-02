package com.github.krystian211.city.bus.route.search.engine.utils;

import com.github.krystian211.city.bus.route.search.engine.model.Timetable;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Set;

public class TimetableUtils {
    public static int[] calculateMinutesRowNumbers(Timetable timetable) {
        int[] rowNumbers = new int[3];
        rowNumbers[0]=findRowNumber(timetable.getWeekdayDepartureTimes());
        rowNumbers[1]=findRowNumber(timetable.getSaturdayDepartureTimes());
        rowNumbers[2]=findRowNumber(timetable.getSundayAndHolidayDepartureTimes());
        return rowNumbers;
    }

    private static int findRowNumber(Set<LocalTime> localTimeList) {
        int[] rowNumberForEachHour=new int[24];
        for (LocalTime localTime : localTimeList) {
            rowNumberForEachHour[localTime.getHour()]=+1;
        }
        Arrays.sort(rowNumberForEachHour);
        return rowNumberForEachHour[23];
    }
}
