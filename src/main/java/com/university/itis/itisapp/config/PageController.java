package com.university.itis.itisapp.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PageController {


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginPage() {
        return "/login";
    }
}
