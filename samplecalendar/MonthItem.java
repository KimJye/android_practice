package org.androidtown.samplecalendar;

/**
 * Created by 김지혜 on 2017-12-02.
 */

public class MonthItem {
    private int dayValue;

    public MonthItem() {

    }

    public MonthItem(int day) {
        dayValue = day;
    }

    public int getDay() {
        return dayValue;
    }

    public void setDay(int day) {
        this.dayValue = day;
    }


}
