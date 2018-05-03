package com.university.itis.itisapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.university.itis.itisapp.timetable.google.model.ParsedSubject;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TimetableDto {

    private String subjectType;
    private String subject;
    private String group;
    private String professorName;
    private String classroom;
    private String time;
    private int flow;
    private Boolean isEven;
    private String notation;

    public TimetableDto(ParsedSubject input) {
        if (!checkNull(input.getSubjectName()))
            this.subject = input.getSubjectName().trim();
        if (!checkNull(input.getGroup()))
            this.group = input.getGroup().trim();
        if (!checkNull(input.getTeacher()))
            this.professorName = input.getTeacher().trim();
        if (!checkNull(input.getTime()))
            this.time = input.getTime().trim();
        if (!checkNull(input.getCabinet()))
            this.classroom = input.getCabinet().trim();
        if (!checkNull(input.getNotation()))
            this.notation = input.getNotation().trim();

        this.isEven = input.getOnEvenWeek();
        this.subjectType = input.getType() == null ? null : input.getType().name();
        this.flow = input.getFlow();
    }

    private boolean checkNull(String field) {
        return field == null;
    }

    public TimetableDto() {
    }

    public Boolean getEven() {
        return isEven;
    }

    public void setEven(Boolean even) {
        isEven = even;
    }

    public String getNotation() {
        return notation;
    }

    public void setNotation(String notation) {
        this.notation = notation;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getFlow() {
        return flow;
    }

    public void setFlow(int flow) {
        this.flow = flow;
    }

    public String getSubjectType() {
        return subjectType;
    }

    public void setSubjectType(String subjectType) {
        this.subjectType = subjectType;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getProfessorName() {
        return professorName;
    }

    public void setProfessorName(String professorName) {
        this.professorName = professorName;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }
}
