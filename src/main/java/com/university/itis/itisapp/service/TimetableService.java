package com.university.itis.itisapp.service;

import com.university.itis.itisapp.dto.TimetableDto;

import java.util.Map;

public interface TimetableService {

    Map<Integer, Map<String, TimetableDto>> getTimetableRespone(String group);
}
