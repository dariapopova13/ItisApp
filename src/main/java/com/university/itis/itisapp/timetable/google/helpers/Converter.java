package com.university.itis.itisapp.timetable.google.helpers;

import org.springframework.stereotype.Component;

@Component
public class Converter {

    public int normalizeGroupNumber(int year) {
        switch (year) {
            case 1:
                return 7;
            case 2:
                return 6;
            case 3:
                return 5;
            case 4:
                return 4;
            default:
                throw new IllegalArgumentException("Wrong year");
        }
    }

    public String getGroup(int course, int j) {
        return "11-" + normalizeGroupNumber(course) + "0" + (j + 1);
    }
}
