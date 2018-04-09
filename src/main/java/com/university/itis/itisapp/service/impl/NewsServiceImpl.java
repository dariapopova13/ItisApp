package com.university.itis.itisapp.service.impl;

import com.university.itis.itisapp.dto.NewsDto;
import com.university.itis.itisapp.dto.SingleDayResponse;
import com.university.itis.itisapp.dto.TimetableDto;
import com.university.itis.itisapp.model.News;
import com.university.itis.itisapp.model.Role;
import com.university.itis.itisapp.model.Timetable;
import com.university.itis.itisapp.model.User;
import com.university.itis.itisapp.model.enums.RoleNames;
import com.university.itis.itisapp.repository.NewsRepository;
import com.university.itis.itisapp.repository.TimetabelRepository;
import com.university.itis.itisapp.service.NewsService;
import com.university.itis.itisapp.service.UserService;
import com.university.itis.itisapp.utils.DateUtils;
import com.university.itis.itisapp.utils.DtoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsRepository newsRepository;
    @Autowired
    private DateUtils dateUtils;
    @Autowired
    private TimetabelRepository timetabelRepository;
    @Autowired
    private DtoUtils dtoUtils;

    @Override
    public List<NewsDto> getNewsByYearAndCourses(int year, List<Long> courseIds) {
        if (courseIds == null) {
            courseIds = new ArrayList<>();
        }
        return newsRepository.findAllByCourseIdInOrYearOrderByDeadlineAsc(courseIds, year)
                .stream().map(NewsDto::new).collect(Collectors.toList());
    }

    @Override
    public Map<String, SingleDayResponse> getMonthNews(String date, int year,
                                                       String group, List<Long> courseIds) {

        Pair<Date, Date> dates = dateUtils.getDateMonthRange(date);
        List<News> newsList = newsRepository.getNews(dates.getFirst(), dates.getSecond(), courseIds, year);
        Map<String, List<News>> newsMap = new TreeMap<>();
        for (News news : newsList) {
            if (newsMap.get(dateUtils.getFormatedDate(news.getDeadline())) == null) {
                List<News> list = new ArrayList<>();
                list.add(news);
                newsMap.put(dateUtils.getFormatedDate(news.getDeadline()), list);
            } else {
                newsMap.get(dateUtils.getFormatedDate(news.getDeadline())).add(news);
            }
        }


        List<Timetable> timetableList = timetabelRepository.findByGroup(group);
        Map<Integer, List<Timetable>> timetableMap = new TreeMap<>();
        for (Timetable timetable : timetableList) {
            if (timetableMap.get(timetable.getWeekdayNum()) == null) {
                List<Timetable> list = new ArrayList<>();
                list.add(timetable);
                timetableMap.put(timetable.getWeekdayNum(), list);
            } else {
                timetableMap.get(timetable.getWeekdayNum()).add(timetable);
            }
        }


        Map<String, SingleDayResponse> responseMap = new TreeMap<>();
        SingleDayResponse singleDayResponse;
        for (LocalDate localDate = dateUtils.toLocalDate(dates.getFirst());
             localDate.isBefore(dateUtils.toLocalDate(dates.getSecond()).plusDays(1));
             localDate = localDate.plusDays(1)) {
            Date currentDate = dateUtils.toDate(localDate);
            singleDayResponse = new SingleDayResponse();
            singleDayResponse.setDate(dateUtils.getFormatedDate(currentDate));
            List<Timetable> timetable = timetableMap.get(dateUtils.getWeekday(currentDate));
            if (timetable != null)
                singleDayResponse.setTimetables(timetable.stream()
                        .map(TimetableDto::new)
                        .sorted((t1, t2) -> t1.getTime().compareTo(t2.getTime()))
                        .collect(Collectors.toList()));

            List<News> news = newsMap.get(dateUtils.getFormatedDate(currentDate));
            if (news != null) {
                singleDayResponse.setNews(news.stream().map(NewsDto::new).collect(Collectors.toList()));
            }
            responseMap.put(dateUtils.getFormatedDate(currentDate), singleDayResponse);
        }

        return responseMap;
    }

    @Override
    public NewsDto get(Long id) {
        News news = newsRepository.findOne(id);
        return news == null ? null : new NewsDto(news);
    }

    @Autowired
    private UserService userService;

    @Override
    public NewsDto saveOrUdpate(NewsDto newsDto) {
        News news = dtoUtils.toEntiry(newsDto);
        news = newsRepository.save(news);
        return news == null ? null : new NewsDto(news);
    }

    @Override
    public void delete(Long id) {
        newsRepository.delete(id);
    }
}
