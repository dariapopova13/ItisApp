package com.university.itis.itisapp.timetable.google.helpers;

import org.springframework.stereotype.Component;

@Component
public class Keywords {

    private static final String[] LECTURE = {
            "108",
            "109",
            "1310",
            "Методология научных исследований",
            "Кроссплатформенное прикладное программирование",
            //"Введение в робототехнику"
    };


    private final static String[] NOT_LECTURE = {
            "Основы предпринимательства Низамутдинов",
            "Программная инженерия Шамсутдинов",
            "Информатика Ференец",
            "Иностранный язык",
            "Методы оптимизации Фазылов",
            "с 11.04в 109 к.2",
            "Математический анализ Кац"
    };


    private static final String[] PHYS_CULTURE = {
            "Уникс",
            "УНИКС"
    };


    private static final String[] SCIENTIC = {
            "Физика",
            "Биоинформатика"
    };

    private static final String[] TECH = {"Курс по выбору"};


    private static final String[] ENGLISH = {"Иностранный язык"};


    private static final String[] TRASH = {
            "Информатика Абрамский М.М. в 108 к.2 , Марченко А.А. в 1310-1311 к.2 Кремлевская 35  ( 28.02 не будет с перносом на 11.04 в 11.50 в 1310-1311) 9н.							",
            "Физика д.гл. Мутыгуллина А.А. ( лекция) в 112 ф.ф.  ,Основы правоведения и противодействия коррупции Хасанов Р.А. ч.н.1308 для гр.11-508							",
            "Методология научных исследований : Абрамский М.М. 1409, Кугуракова В.В  1408, Шахова И.С. 1412, Голицына И.Н.1308 (гр.Гайсина)							",
            "Методология научных исследований  Голицына И.Н.гр. № 1 ( практика) 1508( гр.401, 404, 407,408)							",
            "Методология научных исследований  Голицына И.Н.гр. № 2 (практика) 1508 (402,403,405,406)							",
    };


    public boolean checkScientic(String content) {
        for (String s : SCIENTIC) {
            if (content.contains(s))
                return true;
        }
        return false;
    }


    public boolean checkEnglish(String content) {
        for (String s : ENGLISH) {
            if (content.contains(s))
                return true;
        }
        return false;
    }

    public boolean checkLecture(String content) {
        for (String s : LECTURE) {
            if (content.contains(s))
                return true;
        }
        return false;
    }

    public boolean checkNotLecture(String content) {
        for (String s : NOT_LECTURE) {
            if (content.contains(s))
                return true;
        }
        return false;
    }


    public boolean checkPhysCulture(String content) {
        for (String s : PHYS_CULTURE) {
            if (content.contains(s))
                return true;
        }
        return false;
    }

    public boolean checkTech(String content) {
        for (String s : TECH) {
            if (content.contains(s))
                return true;
        }
        return false;
    }

    public boolean checkTrash(String content) {
        for (String s : TRASH) {
            if (content.contains(s))
                return true;
        }
        return false;
    }


}
        
