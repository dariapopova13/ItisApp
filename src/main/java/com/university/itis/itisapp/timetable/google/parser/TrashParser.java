package com.university.itis.itisapp.timetable.google.parser;

import com.university.itis.itisapp.timetable.google.model.ParsedSubject;
import com.university.itis.itisapp.timetable.google.model.ParsedSubjectBuilder;
import com.university.itis.itisapp.timetable.google.model.TmpObject;

public class TrashParser implements Parser {

    @Override
    public ParsedSubject parse(TmpObject input) {
        return new ParsedSubjectBuilder().createParsedSubject();
    }
}
