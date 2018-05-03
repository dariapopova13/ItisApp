package com.university.itis.itisapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.university.itis.itisapp.timetable.google.model.ParsedSubject;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TimetableResponseDto {

    private int day;
    private Map<String, TimetableDto> timetableMap;

    public TimetableResponseDto() {
    }

    public TimetableResponseDto(List<ParsedSubject> input, int day) {
        timetableMap = new TreeMap<>();
        this.day = day;
        for (ParsedSubject subject : input) {
            timetableMap.put(subject.getTime(), new TimetableDto(subject));
        }
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
