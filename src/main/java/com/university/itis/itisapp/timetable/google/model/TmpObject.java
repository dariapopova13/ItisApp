package com.university.itis.itisapp.timetable.google.model;

public class TmpObject {

    private String time;
    private String content;
    private String group;
    private int course;
    private Boolean isOnEvenWeek;

    public TmpObject(String time, String content,
                     String group, int course, Boolean isOnEvenWeek) {
        this.time = time;
        this.content = content;
        this.group = group;
        this.course = course;
        this.isOnEvenWeek = isOnEvenWeek;
    }

    public TmpObject() {

    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
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
}
