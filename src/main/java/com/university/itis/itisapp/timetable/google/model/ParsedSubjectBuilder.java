package com.university.itis.itisapp.timetable.google.model;

import com.university.itis.itisapp.timetable.google.model.enums.ScheduleGroupType;


public class ParsedSubjectBuilder {

    private String group;
    private String time;
    private String subjectName;
    private String teacher;
    private String cabinet;
    private String notation;
    private ScheduleGroupType type;
    private int flow;
    private int course;
    private Boolean isOnEvenWeek;

    public ParsedSubjectBuilder setGroup(String group) {
        this.group = group;
        return this;
    }

    public ParsedSubjectBuilder setTime(String time) {
        this.time = time;
        return this;
    }

    public ParsedSubjectBuilder setSubjectName(String subjectName) {
        this.subjectName = subjectName;
        return this;
    }

    public ParsedSubjectBuilder setTeacher(String teacher) {
        this.teacher = teacher;
        return this;
    }

    public ParsedSubjectBuilder setCabinet(String cabinet) {
        this.cabinet = cabinet;
        return this;
    }

    public ParsedSubjectBuilder setNotation(String notation) {
        this.notation = notation;
        return this;
    }

    public ParsedSubjectBuilder setType(ScheduleGroupType type) {
        this.type = type;
        return this;
    }

    public ParsedSubjectBuilder setFlow(int flow) {
        this.flow = flow;
        return this;
    }

    public ParsedSubjectBuilder setCourse(int course) {
        this.course = course;
        return this;
    }

    public ParsedSubjectBuilder setIsOnEvenWeek(Boolean isOnEvenWeek) {
        this.isOnEvenWeek = isOnEvenWeek;
        return this;
    }

    public ParsedSubject createParsedSubject() {
        return new ParsedSubject(group, time, subjectName, teacher, cabinet, notation, type, flow, course, isOnEvenWeek);
    }
}