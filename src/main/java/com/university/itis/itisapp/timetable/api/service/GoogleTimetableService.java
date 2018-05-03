package com.university.itis.itisapp.timetable.api.service;


import com.university.itis.itisapp.dto.TimetableResponseDto;

import java.sql.Time;
import java.util.List;

public interface GoogleTimetableService {


    List<TimetableResponseDto> getTimetableForGroup(String group);

    TimetableResponseDto getTimetableForGroup(String group, int day);
}
