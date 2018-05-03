package com.university.itis.itisapp.timetable.google.parser;

import com.university.itis.itisapp.timetable.google.model.ParsedSubject;
import com.university.itis.itisapp.timetable.google.model.ParsedSubjectBuilder;
import com.university.itis.itisapp.timetable.google.model.TmpObject;
import com.university.itis.itisapp.timetable.google.model.enums.ScheduleGroupType;
import org.springframework.stereotype.Component;

@Component(value = "seminarParser")
public class SeminarParser implements Parser {

    @Override
    public ParsedSubject parse(TmpObject input) {
        int initialsCounter = 0;
        ParsedSubject parsedSubject = new ParsedSubjectBuilder()
                .setCabinet("")
                .setNotation("")
                .setTeacher("")
                .setType(ScheduleGroupType.Seminar)
                .setTime(input.getTime())
                .setGroup(input.getGroup())
                .setIsOnEvenWeek(input.getOnEvenWeek())
                .createParsedSubject();
        boolean notationCheck = false, upperCaseCheck = false;
        int i = 0;

        int contentLenght = input.getContent().length();
        for (int j = 0; j < contentLenght; j++) {
            i++;
            char c = input.getContent().charAt(j);
            if (c == '(') notationCheck = true;
            if (i > 1)
                if (Character.isUpperCase(c))
                    upperCaseCheck = true;
            if (Character.isDigit(c) && parsedSubject.getCabinet().length() < 4 && !notationCheck) {
                parsedSubject.renewCabinet(c);
            } else {
                if (!upperCaseCheck) {
                    parsedSubject.renewSubjectName(c);
                } else if (notationCheck) {
                    parsedSubject.renewNotation(c);
                } else if (initialsCounter < 3) {
                    parsedSubject.renewTeacher(c);
                    if (Character.isUpperCase(c))
                        initialsCounter++;
                }
            }

            if (c == ')')
                notationCheck = false;
        }
        parsedSubject.renewTeacher('.');
        return parsedSubject;
    }
}
