package com.university.itis.itisapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.university.itis.itisapp.timetable.google.model.ParsedSubject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TimetableResponseDto {

    private int day;
    private List<TimetableDto> timetables;

    public TimetableResponseDto() {
    }

    public TimetableResponseDto(List<ParsedSubject> input, int day) {
        this.day = day;
        timetables = new ArrayList<>(input.size());
        for (ParsedSubject subject : input) {
            timetables.add(new TimetableDto(subject));
        }
        timetables.sort((timetableDto, t1) -> timetableDto.getTime().compareTo(t1.getTime()));
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public List<TimetableDto> getTimetables() {
        return timetables;
    }

    public void setTimetables(List<TimetableDto> timetables) {
        this.timetables = timetables;
    }
}
