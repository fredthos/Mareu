package com.example.mareu.model;

import java.util.Calendar;

public class Time {

    private Calendar mCalendar = Calendar.getInstance();

    public int getCurrentHour() {
        return mCalendar.get(Calendar.HOUR_OF_DAY);

    }

    public int getCurrentMin() {
        return mCalendar.get(Calendar.MINUTE);
    }
}
