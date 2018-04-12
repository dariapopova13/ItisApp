package com.university.itis.itisapp.dto;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.Date;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SingleDayResponse {

    private String date;
    private Map<String, TimetableDto> timetables;
    private List<NewsDto> news;
    private int weekday;

    public SingleDayResponse() {
    }

    public int getWeekday() {
        return weekday;
    }

    public void setWeekday(int weekday) {
        this.weekday = weekday;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Map<String, TimetableDto> getTimetables() {
        return timetables;
    }

    public void setTimetables(Map<String, TimetableDto> timetables) {
        this.timetables = timetables;
    }

    public List<NewsDto> getNews() {
        return news;
    }

    public void setNews(List<NewsDto> news) {
        this.news = news;
    }
}
