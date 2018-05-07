package com.university.itis.itisapp.service;

import com.university.itis.itisapp.dto.NewsDto;
import com.university.itis.itisapp.dto.NewsFilterDto;
import com.university.itis.itisapp.dto.SingleDayResponse;

import java.util.List;
import java.util.Map;


public interface NewsService extends BaseService<NewsDto> {

    public List<NewsDto> getNewsByYearAndCourses(int year, List<Long> courseIds);

    Map<String, SingleDayResponse> getMonthNews(String date, String group, List<Long> courseIds);

    List<NewsDto> getNews(int page);

    List<NewsDto> filter(NewsFilterDto filter);

}
