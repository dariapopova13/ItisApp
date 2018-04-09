package com.university.itis.itisapp.dto;

import com.university.itis.itisapp.dto.common.AbstractDto;
import com.university.itis.itisapp.model.Timetable;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TimetableDto extends AbstractDto {

    private String weekDay;
    private String lesson;
    private String professorName;
    private String lessonType;
    private String address;
    private String classroom;
    private String time;
    private String group;

    public TimetableDto(Timetable timetable) {
        super(timetable);
        this.weekDay = timetable.getWeekDay() == null ? null : timetable.getWeekDay().name();
        this.lesson = timetable.getLesson();
        this.professorName = timetable.getProfessorName();
        this.lessonType = timetable.getLesson() == null ? null : timetable.getLessonType().name();
        this.address = timetable.getAddress();
        this.classroom = timetable.getClassroom();
        this.time = timetable.getTime();
        this.group = timetable.getGroup();

    }

    public TimetableDto() {
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(String weekDay) {
        this.weekDay = weekDay;
    }

    public String getLesson() {
        return lesson;
    }

    public void setLesson(String lesson) {
        this.lesson = lesson;
    }

    public String getProfessorName() {
        return professorName;
    }

    public void setProfessorName(String professorName) {
        this.professorName = professorName;
    }

    public String getLessonType() {
        return lessonType;
    }

    public void setLessonType(String lessonType) {
        this.lessonType = lessonType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
