package com.university.itis.itisapp.service.impl;

import com.university.itis.itisapp.dao.SearchDao;
import com.university.itis.itisapp.dto.NewsDto;
import com.university.itis.itisapp.dto.NewsFilterDto;
import com.university.itis.itisapp.dto.SingleDayResponse;
import com.university.itis.itisapp.dto.TimetableDto;
import com.university.itis.itisapp.model.News;
import com.university.itis.itisapp.repository.NewsRepository;
import com.university.itis.itisapp.repository.ProfessorRepository;
import com.university.itis.itisapp.service.NewsService;
import com.university.itis.itisapp.service.TimetableService;
import com.university.itis.itisapp.service.UserService;
import com.university.itis.itisapp.utils.AppUtils;
import com.university.itis.itisapp.utils.DateUtils;
import com.university.itis.itisapp.utils.DtoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@PropertySource("classpath:application.properties")
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsRepository newsRepository;
    @Autowired
    private DateUtils dateUtils;
    @Autowired
    private DtoUtils dtoUtils;
    @Autowired
    private TimetableService timetableService;
    @Autowired
    private AppUtils appUtils;
    @Value("${default_page_count}")
    private int pageCount;
    @Autowired
    private ProfessorRepository professorRepository;
    @Autowired
    private UserService userService;
    @Autowired
    @Qualifier(value = "newsSearchDao")
    private SearchDao<News> newsSearchDao;

    @Override
    public List<NewsDto> getNewsByYearAndCourses(int year, List<Long> courseIds) {
        if (courseIds == null) {
            courseIds = new ArrayList<>();
        }
        return newsRepository.findAllByCourseIdInOrYearOrderByDeadlineAsc(courseIds, year)
                .stream().map(NewsDto::new).collect(Collectors.toList());
    }

    @Override
    public List<NewsDto> getNews(int page) {
//        User current = userService.getCurrentUser();
        Pageable request = new PageRequest(page, pageCount);
        Page<News> news = null;
//        if (current.getRole().getSimpleName().equals(RoleNames.DEAN.name())) {
//            news = newsRepository.findByYearNotNullOrderByDeadlineAsc(request);
//        } else if (current.getRole().getSimpleName().equals(RoleNames.PROFESSOR.name())) {
//            Professor professor = professorRepository.findByUserUsername(current.getUsername());
//            if (professor != null) {
//                news = newsRepository.findAllByCourseIn(professor.getCourses(), request);
//            }
//        } else if (current.getRole().getSimpleName().equals(RoleNames.ADMIN.name())) {
//            news = newsRepository.findAll(request);
//        }
        news = newsRepository.getAllByDeleteDateNull(request);
        return news == null ? Collections.EMPTY_LIST : news.map(NewsDto::new).getContent();
    }

    @Override
    public List<NewsDto> getDeletedNews(int page) {
//        User current = userService.getCurrentUser();
        Pageable request = new PageRequest(page, pageCount);
        Page<News> news = null;
//        if (current.getRole().getSimpleName().equals(RoleNames.DEAN.name())) {
//            news = newsRepository.findByYearNotNullOrderByDeadlineAsc(request);
//        } else if (current.getRole().getSimpleName().equals(RoleNames.PROFESSOR.name())) {
//            Professor professor = professorRepository.findByUserUsername(current.getUsername());
//            if (professor != null) {
//                news = newsRepository.findAllByCourseIn(professor.getCourses(), request);
//            }
//        } else if (current.getRole().getSimpleName().equals(RoleNames.ADMIN.name())) {
//            news = newsRepository.findAll(request);
//        }
        news = newsRepository.getAllByDeleteDateIsNotNull(request);
        return news == null ? Collections.EMPTY_LIST : news.map(NewsDto::new).getContent();
    }

    @Override
    public Map<String, SingleDayResponse> getMonthNews(String date, String group, List<Long> courseIds) {
        int year = appUtils.getCourse(group);
        Map<Integer, Map<String, TimetableDto>> timetable = timetableService.getTimetableRespone(group);

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

        Map<String, SingleDayResponse> responseMap = new TreeMap<>();
        SingleDayResponse singleDayResponse;
        for (LocalDate localDate = dateUtils.toLocalDate(dates.getFirst());
             localDate.isBefore(dateUtils.toLocalDate(dates.getSecond()).plusDays(1));
             localDate = localDate.plusDays(1)) {
            Date currentDate = dateUtils.toDate(localDate);
            int weekday = dateUtils.getWeekday(currentDate);
//            if (weekday == 0) continue;

            singleDayResponse = new SingleDayResponse();
            singleDayResponse.setWeekday(weekday);
            singleDayResponse.setDate(dateUtils.getFormatedDate(currentDate));
            if (timetable != null)
                singleDayResponse.setTimetables(timetable.get(weekday));
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

    @Override
    public NewsDto saveOrUdpate(NewsDto newsDto) {
        News news = dtoUtils.toEntiry(newsDto);
        news = newsRepository.save(news);
        return news == null ? null : new NewsDto(news);
    }

    @Override
    public void delete(Long id) {
        News news = newsRepository.findOne(id);
//        if (news != null && userService.checkNews(news)) {
            news.setDeleteDate(new Date());
            newsRepository.save(news);
//        }
    }

    @Override
    public List<NewsDto> filter(NewsFilterDto filter) {
        return newsSearchDao.filter(filter).stream()
                .map(NewsDto::new).collect(Collectors.toList());
    }

    @Override
    public void restore(Long id) {
        News news = newsRepository.findOne(id);
//        if (news != null && userService.checkNews(news)) {
        news.setDeleteDate(null);
        newsRepository.save(news);
//        }
    }
}
