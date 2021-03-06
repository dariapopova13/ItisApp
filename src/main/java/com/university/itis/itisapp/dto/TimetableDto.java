package com.university.itis.itisapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.university.itis.itisapp.timetable.google.model.ParsedSubject;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimetableDto that = (TimetableDto) o;
        return Objects.equals(subjectType, that.subjectType) &&
                Objects.equals(subject, that.subject) &&
                Objects.equals(professorName, that.professorName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(subjectType, subject, professorName);
    }

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

    public TimetableDto() {
    }

    private boolean checkNull(String field) {
        return field == null;
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
