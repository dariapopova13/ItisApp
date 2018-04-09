package com.university.itis.itisapp.model.enums;

public enum StudingWeek {

    Monday(2),
    Tuesday(3),
    Wednesday(4),
    Thursday(5),
    Friday(6),
    Saturday(7);

    private int weekdayNum;

    StudingWeek(int i) {
        this.weekdayNum = i;
    }

    public int getWeekdayNum() {
        return weekdayNum;
    }
}
