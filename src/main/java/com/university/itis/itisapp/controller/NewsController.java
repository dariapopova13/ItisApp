package com.university.itis.itisapp.controller;

import com.university.itis.itisapp.dto.NewsDto;
import com.university.itis.itisapp.dto.NewsFilterDto;
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


    @GetMapping(value = "/month/{group}")
    public Map<String, SingleDayResponse> getWeekNews(@RequestParam(name = "course", required = false) List<Long> courseIds,
                                                      @RequestParam(name = "date", required = false) String date,
                                                      @PathVariable String group) {
        return newsService.getMonthNews(date, group, courseIds);
    }


    @GetMapping(value = "/api/single/{id}")
    public NewsDto getOne(@PathVariable Long id) {
        return newsService.get(id);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/api/add")
    public NewsDto saveNews(@RequestBody NewsDto newsDto) {
        return newsService.saveOrUdpate(newsDto);
    }


    @PreAuthorize("isAuthenticated()")
    @DeleteMapping(value = "/api/delete/{id}")
    public void deleteNews(@PathVariable Long id) {
        newsService.delete(id);
    }


    @GetMapping(value = "/api/{page}")
    @PreAuthorize("isAuthenticated()")
    public List<NewsDto> getNews(@PathVariable int page) {
        return newsService.getNews(page);
    }

    @PostMapping(value = "/api/filter")
    public List<NewsDto> filter(@RequestBody NewsFilterDto newsFilter){
        return newsService.filter(newsFilter);
    }
}
