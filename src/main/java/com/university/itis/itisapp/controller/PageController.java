package com.university.itis.itisapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class PageController {


    @RequestMapping(value = "/login", method = GET)
    public String getLoginPage() {
        return "login";
    }

    @RequestMapping(value = "/user/main")
    public String getMain() {
        return "navbar";
    }

    @RequestMapping(value = "/user/main/courses")
    public String getCourses() {
        return "courses";
    }

    @RequestMapping(value = "/user/main/professors")
    public String getProfessors() {
        return "professors";
    }

    @RequestMapping(value = "/user/main/news")
    public String getNews() {
        return "news";
    }

    @RequestMapping(value = "/user/main/me")
    public String getMe() {
        return "me";
    }
}
