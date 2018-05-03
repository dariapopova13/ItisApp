package com.university.itis.itisapp.timetable.google.parser;

import com.university.itis.itisapp.timetable.google.model.ParsedSubject;
import com.university.itis.itisapp.timetable.google.model.ParsedSubjectBuilder;
import com.university.itis.itisapp.timetable.google.model.TmpObject;
import com.university.itis.itisapp.timetable.google.model.enums.ScheduleGroupType;
import org.springframework.stereotype.Component;

@Component(value = "lectureParser")
public class LectureParser implements Parser {

    @Override
    public ParsedSubject parse(TmpObject input) {
        int i = 0, fmCheck = 0, cabCount = 0;
        boolean upCheck = false, notationCheck = false;

        ParsedSubject parsedSubject = new ParsedSubjectBuilder()
                .setCabinet("")
                .setNotation("")
                .setSubjectName("")
                .setTeacher("")
                .setGroup(input.getGroup())
                .setTime(input.getTime())
                .setType(ScheduleGroupType.Lecture)
                .setIsOnEvenWeek(input.getOnEvenWeek())
                .createParsedSubject();

        int contentLenght = input.getContent().length();
        for (int j = 0; j < contentLenght; j++) {
            i++;
            char c = input.getContent().charAt(j);
            if (c == '(') notationCheck = true;
            if (i > 1)
                if (Character.isUpperCase(c))
                    upCheck = true;
            if (Character.isDigit(c) && cabCount < 4 && !notationCheck) {
                if (cabCount == 1 && c != '3') cabCount++;
                parsedSubject.renewCabinet(c);
                if (parsedSubject.getCabinet().equals("140"))
                    cabCount--;
                if (parsedSubject.getCabinet().equals("115"))
                    parsedSubject.setCabinet("1508");
                cabCount++;
            } else {
                if (!upCheck) {
                    parsedSubject.renewSubjectName(c);
                } else if (notationCheck) {
                    parsedSubject.renewNotation(c);
                } else if (fmCheck < 3) {
                    parsedSubject.renewTeacher(c);
                    if (Character.isUpperCase(c))
                        fmCheck++;
                }
            }
            if (c == ')')
                notationCheck = false;
        }

        return parsedSubject;
    }
}
