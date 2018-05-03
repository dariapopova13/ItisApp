package com.university.itis.itisapp.timetable.api.service;

import com.university.itis.itisapp.dto.TimetableResponseDto;
import com.university.itis.itisapp.timetable.google.model.ParsedSubject;
import com.university.itis.itisapp.timetable.google.service.GoogleApiService;
import com.university.itis.itisapp.timetable.google.service.SmartSortService;
import com.university.itis.itisapp.timetable.utils.GoogleUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class GoogleTimetableServiceImpl implements GoogleTimetableService {

    @Autowired
    private GoogleUtils googleUtils;
    @Autowired
    private GoogleApiService googleApiService;
    @Autowired
    private SmartSortService smartSortService;

    @Override
    public List<TimetableResponseDto> getTimetableForGroup(String group) {
        List<TimetableResponseDto> responses = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            List<ParsedSubject> tmp = getResult(group, i);
            if (tmp != null){
                responses.add(new TimetableResponseDto(tmp, i));
            }
        }

        return responses;
    }

    @Override
    public TimetableResponseDto getTimetableForGroup(String group, int day) {
        List<ParsedSubject> list = getResult(group, day);
        if (list == null) return null;
        return new TimetableResponseDto(list, day);
    }


    private List<ParsedSubject> getResult(String group, int day) {
        try {
            return smartSortService.sortContentForGroup(
                    googleApiService.sendRequest(group, day), group);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
