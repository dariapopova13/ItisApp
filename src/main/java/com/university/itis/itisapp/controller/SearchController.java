package com.university.itis.itisapp.controller;

import com.university.itis.itisapp.dao.SearchDao;
import com.university.itis.itisapp.dto.NewsDto;
import com.university.itis.itisapp.model.News;
import com.university.itis.itisapp.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/search")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @GetMapping(value = "/news")
    public List<NewsDto> searchNews(@RequestParam(name = "q") String q){
        return searchService.searchNews(q);
    }

}
