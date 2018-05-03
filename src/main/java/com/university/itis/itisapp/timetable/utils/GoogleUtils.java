package com.university.itis.itisapp.timetable.utils;


import org.springframework.stereotype.Component;

@Component
public class GoogleUtils {

    public int getCourse(String group) {
        if (!assertItis(group))
            return 0;
        char first = group.charAt(group.indexOf('-') + 1);
        switch (first) {
            case '4':
                return 4;
            case '5':
                return 3;
            case '6':
                return 2;
            case '7':
                return 1;
            default:
                return 0;
        }
    }

    private boolean assertItis(String group) {
        return group.startsWith("11-");
    }

    public String getTime(int num) {
        String[] times = {"08.30-10.00", "10.10-11.40", "11.50-13.20",
                "13.40-15.10", "15.20-16.50", "17.00-18.30", "18.40-20.10"};
        return times[num];
    }

    public int getGroupNum(String group) {
        try {
            return Integer.valueOf("" + group.charAt(group.length() - 1));
        } catch (Exception e) {
            return 0;
        }
    }
}
