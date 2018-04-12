package com.university.itis.itisapp.controller;

import com.university.itis.itisapp.dto.NewsDto;
import com.university.itis.itisapp.dto.SingleDayResponse;
import com.university.itis.itisapp.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/news")
public class NewsController {


    @Autowired
    private NewsService newsService;

    @GetMapping(value = "/{year}")
    public List<NewsDto> getNews(@RequestParam(name = "course", required = false) List<Long> courseIds,
                                 @PathVariable int year) {
        return newsService.getNewsByYearAndCourses(year, courseIds);
    }

    @GetMapping(value = "/week/{group}")
    public Map<String, SingleDayResponse> getMonthNews(@RequestParam(name = "course", required = false) List<Long> courseIds,
                                                       @RequestParam(name = "date", required = false) String date,
                                                       @PathVariable String group) {
        return newsService.getMonthNews(date, group, courseIds);
    }

    @GetMapping(value = "/month/{group}")
    public Map<String, SingleDayResponse> getWeekNews(@RequestParam(name = "course", required = false) List<Long> courseIds,
                                                      @RequestParam(name = "date", required = false) String date,
                                                      @PathVariable String group) {
        return newsService.getMonthNews(date, group, courseIds);
    }


    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/add")
    public NewsDto saveNews(NewsDto newsDto) {
        return newsService.saveOrUdpate(newsDto);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/delete/{id}")
    public void deleteNews(@PathVariable Long id) {
        newsService.delete(id);
    }

}
