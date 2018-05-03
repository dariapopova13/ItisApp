package com.university.itis.itisapp.timetable.google.parser;


//import com.university.itis.itisapp.timetable.google.model.ParsedSubject;
import com.university.itis.itisapp.timetable.google.model.ParsedSubject;
import com.university.itis.itisapp.timetable.google.model.ParsedSubjectBuilder;
import com.university.itis.itisapp.timetable.google.model.TmpObject;
import com.university.itis.itisapp.timetable.google.model.enums.ScheduleGroupType;
import com.university.itis.itisapp.timetable.utils.ParserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ElectiveParser {

    @Autowired
    private ParserUtils parserUtils;

    public Collection<ParsedSubject> parse(TmpObject input, ScheduleGroupType groupType) {
        List<ParsedSubject> result = new ArrayList<>();
        input.setContent(reformContent(input.getContent()));

        String[] subjects = input.getContent().split(",");
        for (String subject : subjects) {
            String notation = "";
            String fixedName = "";
            String lastSubjectName = "";
            if (subject.contains("(")) {
                Pair<String, String> nameNotation = parserUtils.regexGroup(subject, "\\((.*?)\\)");
                fixedName = nameNotation.getFirst();
                notation = nameNotation.getSecond();
            } else {
                fixedName = subject;
            }
            fixedName = parserUtils.regex(fixedName, "[А-ЯA-Z].*");
            String cabinet = parserUtils.regex(fixedName, "\\d+");
            String teacher = parserUtils.regex(fixedName, "[А-Я][а-я]+\\s?[А-Я]\\.[А-Я]?.?");

            String subjectName = fixedName
                    .replace(teacher, "")
                    .replace(cabinet, "")
                    .replace("()", "")
                    .replace("  ", "")
                    .replace("\t", "");

            Matcher matcher = Pattern.compile(subjectName).matcher("[\\d]");
            if (matcher.find()) {
                subjectName = matcher.replaceAll("");
            }

            ParsedSubject parsedSubject = new ParsedSubjectBuilder().setSubjectName(subjectName)
                    .setTime(input.getTime())
                    .setCabinet(cabinet)
                    .setTeacher(teacher)
                    .setCourse(input.getCourse())
                    .setType(groupType)
                    .setGroup(input.getGroup())
                    .setNotation(notation).createParsedSubject();

            if (parsedSubject.getSubjectName().length() < 2) {
                parsedSubject.setSubjectName(lastSubjectName);
            } else {
                lastSubjectName = subjectName;
            }
            result.add(parsedSubject);
        }

        return result;
    }

    private String reformContent(String content) {
        return content.replace("Курс по выбору :", "")
                .replace("Курс по выбору:", "")
                .replace("Курс по выбору :   ,", "");
    }

}
