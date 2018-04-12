package com.university.itis.itisapp.service.impl;

import com.university.itis.itisapp.dto.TimetableDto;
import com.university.itis.itisapp.dto.TimetableResponseDto;
import com.university.itis.itisapp.service.TimetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@PropertySource("classpath:application.properties")
public class TimetableServiceImpl implements TimetableService {

    @Autowired
    private RestTemplate restTemplate;
    @Value("${timetable_url}")
    private String timetableUrl;
    @Value("${timetable_url_week_path}")
    private String weekPath;

    @Override
    public Map<Integer, Map<String, TimetableDto>> getTimetableRespone(String group) {
        TimetableResponseDto[] responseEntity =
                restTemplate.getForObject(getWeekPath(group), TimetableResponseDto[].class);

//        return responseEntity == null ?
//                null : Arrays.stream(responseEntity)
//                .collect(Collectors.toMap(o -> o.getDay(), o -> o.getTimetableMap()));
        return responseEntity == null ? null : Arrays.stream(responseEntity)
                .collect(Collectors.toMap(o -> o.getDay(), o -> o.getTimetableMap()));
    }

    private String getWeekPath(String group) {
        return timetableUrl.concat(weekPath).concat(group);
    }
}
