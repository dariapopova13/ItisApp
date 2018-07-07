package com.university.itis.itisapp.controller;

import com.university.itis.itisapp.dto.TimetableDto;
import com.university.itis.itisapp.dto.TimetableResponseDto;
import com.university.itis.itisapp.timetable.api.service.GoogleTimetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TimetableController {


    @Autowired
    private GoogleTimetableService timetableService;

    @GetMapping(path = "/timetable/{group}")
    public List<TimetableResponseDto> getTimetable(@PathVariable String group) {
        return timetableService.getTimetableForGroup(group);
    }

    @GetMapping(value = "/subjects/{group}")
    public List<TimetableDto> getSubjects(@PathVariable String group) {
        return timetableService.getSubjects(group);
    }
}
