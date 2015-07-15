package org.lunding.deliciousclock.data;

import java.io.Serializable;

/**
 * Created by Lunding on 13/07/15.
 */
public class Time implements Serializable {

    private int hourOfDay;
    private int minute;
    private int offset;

    public Time() {
        hourOfDay = 7;
        minute = 0;
        offset = 10;
    }

    public Time(int hourOfDay, int minute, int offset) {
        this.hourOfDay = hourOfDay;
        this.minute = minute;
        this.offset = offset;
    }

    public int getHourOfDay() {
        return hourOfDay;
    }

    public void setHourOfDay(int hourOfDay) {
        this.hourOfDay = hourOfDay;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    @Override
    public String toString() {
        return "Time: " + hourOfDay + ":" + minute + ", " + offset + " before";
    }
}
