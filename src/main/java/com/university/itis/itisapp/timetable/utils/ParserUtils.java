package com.university.itis.itisapp.timetable.utils;

import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ParserUtils {


    public String regex(String input, String patter) {
        Matcher matcher = Pattern.compile(patter).matcher(input);
        if (matcher.find()) {
            return matcher.group(0);
        }
        return "";
    }

    public Pair<String, String> regexGroup(String input, String patter) {
        Matcher matcher = Pattern.compile(patter).matcher(input);
        String notation = "";
        while (matcher.find()) {
            notation += matcher.group(0)
                    .replace("(", "")
                    .replace(")", "") + "; ";
            input = matcher.replaceFirst("");
            matcher = Pattern.compile(patter).matcher(input);
        }
        return Pair.of(input, notation);
    }
}
