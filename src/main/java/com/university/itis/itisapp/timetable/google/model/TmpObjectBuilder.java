package com.university.itis.itisapp.timetable.google.model;

public class TmpObjectBuilder {

    private String time;
    private String content;
    private String group;
    private int course;
    private Boolean isOnEvenWeek;

    public TmpObjectBuilder setTime(String time) {
        this.time = time;
        return this;
    }

    public TmpObjectBuilder setContent(String content) {
        this.content = content;
        return this;
    }

    public TmpObjectBuilder setGroup(String group) {
        this.group = group;
        return this;
    }

    public TmpObjectBuilder setCourse(int course) {
        this.course = course;
        return this;
    }

    public TmpObjectBuilder setIsOnEvenWeek(Boolean isOnEvenWeek) {
        this.isOnEvenWeek = isOnEvenWeek;
        return this;
    }

    public TmpObject createTmpObject() {
        return new TmpObject(time, content, group, course, isOnEvenWeek);
    }
}