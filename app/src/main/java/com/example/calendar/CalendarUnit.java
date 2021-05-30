package com.example.calendar;

/**
 * The CalendarUnit class encapsulates the attributes of an elementary unit in the calendar.
 * */

public class CalendarUnit {
    private int day;
    private int month;
    private int year;

    private boolean currentMonth;
    private boolean currentDay;

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean isCurrentMonth() {
        return currentMonth;
    }

    public void setCurrentMonth(boolean currentMonth) {
        this.currentMonth = currentMonth;
    }

    public boolean isCurrentDay() {
        return currentDay;
    }

    public void setCurrentDay(boolean currentDay) {
        this.currentDay = currentDay;
    }
}
