package com.university.itis.itisapp.dto;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SingleDayResponse {

    private String date;
    private List<TimetableDto> timetables;
    private List<NewsDto> news;

    public SingleDayResponse() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<TimetableDto> getTimetables() {
        return timetables;
    }

    public void setTimetables(List<TimetableDto> timetables) {
        this.timetables = timetables;
    }

    public List<NewsDto> getNews() {
        return news;
    }

    public void setNews(List<NewsDto> news) {
        this.news = news;
    }
}
