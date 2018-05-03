package com.university.itis.itisapp.timetable.google.parser;
import com.university.itis.itisapp.timetable.google.model.ParsedSubject;
import com.university.itis.itisapp.timetable.google.model.ParsedSubjectBuilder;
import com.university.itis.itisapp.timetable.google.model.TmpObject;
import com.university.itis.itisapp.timetable.google.model.enums.ScheduleGroupType;
import org.springframework.stereotype.Component;

@Component(value = "physCultureParser")
public class PhysCultureParser implements Parser{


    @Override
    public ParsedSubject parse(TmpObject input) {
        switch (input.getTime())
        {
            case "08.30-10.00":
                input.setTime("8.00-9.30");
                break;
            case "10.10-11.40":
                input.setTime("10.05-11.35");
                break;
            case "11.50-13.20":
                input.setTime("12.00-13.30");
                break;
            case "15.20-16.50":
                input.setTime("16.00-17.30");
                break;
        }

        return new ParsedSubjectBuilder()
                .setCabinet("")
                .setNotation("")
                .setType(ScheduleGroupType.PhysCulture)
                .setSubjectName("Физическая культура")
                .setTime(input.getTime())
                .setGroup(input.getGroup())
                .createParsedSubject();
    }
}
