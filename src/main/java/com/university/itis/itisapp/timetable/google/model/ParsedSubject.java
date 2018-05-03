package com.university.itis.itisapp.timetable.google.model;


import com.university.itis.itisapp.timetable.google.model.enums.ScheduleGroupType;

public class ParsedSubject {

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

    public ParsedSubject(String group, String time, String subjectName, String teacher,
                         String cabinet, String notation, ScheduleGroupType type,
                         int flow, int course, Boolean isOnEvenWeek) {
        this.group = group;
        this.time = time;
        this.subjectName = subjectName;
        this.teacher = teacher;
        this.cabinet = cabinet;
        this.notation = notation;
        this.type = type;
        this.flow = flow;
        this.course = course;
        this.isOnEvenWeek = isOnEvenWeek;
    }

    public ParsedSubject() {
    }

    public ScheduleGroupType getType() {
        return type;
    }

    public void setType(ScheduleGroupType type) {
        this.type = type;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getCabinet() {
        return cabinet;
    }

    public void setCabinet(String cabinet) {
        this.cabinet = cabinet;
    }

    public String getNotation() {
        return notation;
    }

    public void setNotation(String notation) {
        this.notation = notation;
    }

    public int getFlow() {
        return flow;
    }

    public void setFlow(int flow) {
        this.flow = flow;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public Boolean getOnEvenWeek() {
        return isOnEvenWeek;
    }

    public void setOnEvenWeek(Boolean onEvenWeek) {
        isOnEvenWeek = onEvenWeek;
    }

    public void renewCabinet(char c) {
        if (cabinet != null) cabinet += c;
        else cabinet = "" + c;
    }

    public void renewSubjectName(char c) {
        if (subjectName != null) subjectName += c;
        else subjectName = "" + c;
    }

    public void renewNotation(char c) {
        if (notation != null) notation += c;
        else notation = "" + c;
    }

    public void renewTeacher(char c) {
        if (teacher != null) teacher += c;
        else teacher = "" + c;
    }
}
