package com.university.itis.itisapp.timetable.google.parser;

import com.university.itis.itisapp.timetable.google.model.ParsedSubject;
import com.university.itis.itisapp.timetable.google.model.TmpObject;

public interface Parser {

    ParsedSubject parse(TmpObject input);
}
