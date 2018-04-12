package com.university.itis.itisapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TimetableResponseDto {

    private int day;
    private Map<String, TimetableDto> timetableMap;

    public TimetableResponseDto() {
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public Map<String, TimetableDto> getTimetableMap() {
        return timetableMap;
    }

    public void setTimetableMap(Map<String, TimetableDto> timetableMap) {
        this.timetableMap = timetableMap;
    }
}
