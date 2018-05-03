package com.university.itis.itisapp.timetable.google.parser;

import com.university.itis.itisapp.timetable.google.model.ParsedSubject;
import com.university.itis.itisapp.timetable.google.model.ParsedSubjectBuilder;
import com.university.itis.itisapp.timetable.google.model.TmpObject;
import com.university.itis.itisapp.timetable.google.model.enums.ScheduleGroupType;
import com.university.itis.itisapp.timetable.utils.ParserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class EnglishParser {

    @Autowired
    private ParserUtils parserUtils;

    public Collection<ParsedSubject> parse(TmpObject input) {
        List<ParsedSubject> result = new ArrayList<>();
        input.setContent(reformContent(input.getContent()));
        String[] subjects = input.getContent().split(",");
        for (String subject : subjects) {
            String cabinet = parserUtils.regex(subject, "\\d+");
            String teacher = subject.replace(cabinet, "");
            int flow = flow = input.getGroup()
                    .charAt(input.getGroup().length() - 1) == '1' ? 1 : 2;
            result.add(new ParsedSubjectBuilder()
                    .setSubjectName("Иностранный язык")
                    .setTime(input.getTime())
                    .setCabinet(cabinet)
                    .setType(ScheduleGroupType.Eng)
                    .setGroup(input.getGroup())
                    .setFlow(flow)
                    .setCourse(input.getCourse())
                    .createParsedSubject());
        }

        return result;
    }


    private String reformContent(String content) {
        return content.replace("Иностранный язык (английский язык) \r\n ", "")
                .replace("Иностранный язык (английский язык) \n", "")
                .replace("Иностранный язык ( английский язык)", "")
                .replace("  ", "");
    }
}
