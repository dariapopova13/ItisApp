package com.university.itis.itisapp.service;

import com.university.itis.itisapp.dto.TimetableDto;

import java.util.List;
import java.util.Map;

public interface TimetableService {

    Map<Integer, List<TimetableDto>> getTimetableRespone(String group);
}
