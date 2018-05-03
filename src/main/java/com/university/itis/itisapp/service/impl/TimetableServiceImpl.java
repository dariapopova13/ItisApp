package com.university.itis.itisapp.service.impl;

import com.university.itis.itisapp.dto.TimetableDto;
import com.university.itis.itisapp.dto.TimetableResponseDto;
import com.university.itis.itisapp.service.TimetableService;
import com.university.itis.itisapp.timetable.api.service.GoogleTimetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@PropertySource("classpath:application.properties")
public class TimetableServiceImpl implements TimetableService {

    @Autowired
    private GoogleTimetableService googleTimetableService;

    @Override
    public Map<Integer, Map<String, TimetableDto>> getTimetableRespone(String group) {

        List<TimetableResponseDto> responses = googleTimetableService.getTimetableForGroup(group);

        return responses == null ? null : responses.stream()
                .collect(Collectors.toMap(o -> o.getDay(), o -> o.getTimetableMap()));
    }

}
