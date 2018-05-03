package com.university.itis.itisapp.timetable.google.service;


import com.university.itis.itisapp.timetable.google.helpers.Keywords;
import com.university.itis.itisapp.timetable.google.model.ParsedSubject;
import com.university.itis.itisapp.timetable.google.model.ParsedSubjectBuilder;
import com.university.itis.itisapp.timetable.google.model.TmpObject;
import com.university.itis.itisapp.timetable.google.model.enums.ScheduleGroupType;
import com.university.itis.itisapp.timetable.google.parser.ElectiveParser;
import com.university.itis.itisapp.timetable.google.parser.EnglishParser;
import com.university.itis.itisapp.timetable.google.parser.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class SmartSortService {

    @Autowired
    @Qualifier("lectureParser")
    private Parser lectureParser;
    @Autowired
    @Qualifier("seminarParser")
    private Parser seminarParser;
    @Autowired
    @Qualifier("physCultureParser")
    private Parser physCultureParser;
    @Autowired
    private ElectiveParser electiveParser;
    @Autowired
    private EnglishParser englishParser;
    @Autowired
    private Keywords keywords;

    public List<ParsedSubject> sortContent(List<TmpObject> inputSubjects) {
        List<ParsedSubject> parsedSubjects = new ArrayList<>();
        for (TmpObject unparsedSubject : inputSubjects) {
            unparsedSubject.setContent(processTrash(unparsedSubject));
            unparsedSubject.setOnEvenWeek(checkWeeks(unparsedSubject));
            if (keywords.checkScientic(unparsedSubject.getContent())) {
                Collection<ParsedSubject> parsedSubject =
                        electiveParser.parse(unparsedSubject, ScheduleGroupType.PickedScientic);
                for (ParsedSubject subject : parsedSubject) {
                    parsedSubjects.addAll(shareSubjects(subject, setMarker(unparsedSubject)));
                }
                continue;
            }

            if (keywords.checkTech(unparsedSubject.getContent())) {
                Collection<ParsedSubject> parsedSubject =
                        electiveParser.parse(unparsedSubject, ScheduleGroupType.PickedTech);
                for (ParsedSubject subject : parsedSubject) {
                    parsedSubjects.addAll(shareSubjects(subject, setMarker(unparsedSubject)));
                }
                continue;
            }

            if (keywords.checkLecture(unparsedSubject.getContent())
                    && !keywords.checkNotLecture(unparsedSubject.getContent())) {
                ParsedSubject parsedSubject =
                        lectureParser.parse(unparsedSubject);
                parsedSubjects.addAll(shareSubjects(parsedSubject, setMarker(unparsedSubject)));
                continue;
            }

            if (keywords.checkPhysCulture(unparsedSubject.getContent())) {
                ParsedSubject parsedSubject =
                        physCultureParser.parse(unparsedSubject);
                parsedSubjects.addAll(shareSubjects(parsedSubject, setMarker(unparsedSubject)));
                continue;
            }

            if (keywords.checkEnglish(unparsedSubject.getContent())) {
                Collection<ParsedSubject> parsedSubject =
                        englishParser.parse(unparsedSubject);
                parsedSubjects.addAll(parsedSubject);
                continue;
            }

            parsedSubjects.add(seminarParser.parse(unparsedSubject));
        }

        return parsedSubjects;
    }

    private String processTrash(TmpObject input) {
        return input.getContent()
                .replace("на Кремлёвской 35", "(на Кремлёвской 35)")
                .replace("( Введение в исскуственный интеллект)", "Введение в исскуственный интеллект")
                .replace("Основы правоведения и противодействия коррупции Хасанов Р.А. ч.н.1308 для гр.11-508 ,", "")
                .replace("д.гл.(прак.)", "")
                .replace("гр.1", "")
                .replace("гр.2", "")
                .replace("Технологии Net- д.гл.Гумеров К.", "Технологии Net Гумеров К.А.")
                .replace("1310- 1311", "1310")
                .replace("Кугуракова В В.", "Кугуракова В.В.")
                .replace("корпоротивных", "корпоративных")
                .replace("М. 13", "М.Р. 13")
                .replace("ХайруллинА.Ф.", "Хайруллин А.Ф.")
                .replace("Костюк Д.", "Костюк Д.И.")
                .replace("Курс по выбору :   ,Введение в теорию и практику", "Курс по выбору :  Введение в теорию и практику")
                .replace("Проектный практикум( рас LAB),   Курс по выбору: Разработка корпоративных приложений Сидиков М.Р. в 1302", "Курс по выбору: Разработка корпоративных приложений Сидиков М.Р. в 1302");
    }

    private Collection<ParsedSubject> shareSubjects(ParsedSubject parsedSubject, Pair<Integer, Integer> marker) {
        String group = parsedSubject.getGroup().substring(0, parsedSubject.getGroup().length() - 1);
        List<ParsedSubject> sharedSubjects = new ArrayList<>();

        for (int i = marker.getFirst(); i < marker.getSecond(); i++) {
            sharedSubjects.add(new ParsedSubjectBuilder()
                    .setSubjectName(parsedSubject.getSubjectName())
                    .setCabinet(parsedSubject.getCabinet())
                    .setGroup(group + i)
                    .setNotation(parsedSubject.getNotation())
                    .setType(parsedSubject.getType())
                    .setTime(parsedSubject.getTime())
                    .setTeacher(parsedSubject.getTeacher())
                    .createParsedSubject());
        }

        return sharedSubjects;
    }

    private Boolean checkWeeks(TmpObject inputSubject) {
        if (inputSubject.getContent().contains("н.н"))
            return false;
        if (inputSubject.getContent().contains("ч.н"))
            return true;
        return null;
    }

    private Pair<Integer, Integer> setMarker(TmpObject unparsedObject) {
        if (unparsedObject.getContent().contains("Абрамский")) {
            if (unparsedObject.getContent().startsWith("11-7"))
                return Pair.of(1, 5);
            if (unparsedObject.getGroup().startsWith("11-6"))
                return Pair.of(1, 5);
        }
        if (unparsedObject.getContent().contains("Марченко")) {
            if (unparsedObject.getGroup().startsWith("11-7"))
                return Pair.of(6, 9);
            if (unparsedObject.getGroup().startsWith("11-6"))
                return Pair.of(6, 8);
        }

        if ((unparsedObject.getContent().contains("Макаев")
                || unparsedObject.getContent().contains("Мартынова"))
                && unparsedObject.getGroup().startsWith("11-7")
                && !unparsedObject.getContent().contains("Переточкина"))
            return Pair.of(1, 5);

        if (unparsedObject.getContent().contains("Переточкина")
                && unparsedObject.getGroup().startsWith("11-7"))
            return Pair.of(6, 9);

        if (unparsedObject.getGroup().startsWith("11-7"))
            return Pair.of(1, 9);
        return Pair.of(1, 8);
    }


    public List<ParsedSubject> sortContentForGroup(List<TmpObject> tmpObjects, String group) {
        List<ParsedSubject> result = new ArrayList<>();
        for (ParsedSubject parsedSubject : sortContent(tmpObjects)) {
            if (parsedSubject.getGroup().equals(group)
                    && parsedSubject.getSubjectName() != null && !parsedSubject.getSubjectName().equals(""))
                result.add(parsedSubject);
        }
        return result;
    }
}
