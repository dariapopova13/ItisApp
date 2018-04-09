package com.university.itis.itisapp.model;

import com.university.itis.itisapp.model.common.AbstractEntity;
import com.university.itis.itisapp.model.enums.LessonType;
import com.university.itis.itisapp.model.enums.StudingWeek;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "timetables")
@DynamicInsert
@DynamicUpdate
public class Timetable extends AbstractEntity {

    @Column(name = "week_day", nullable = false)
    @Enumerated(EnumType.STRING)
    private StudingWeek weekDay;
    @Column(name = "lesson")
    private String lesson;
    @Column(name = "professor_name")
    private String professorName;
    @Enumerated(EnumType.STRING)
    @Column(name = "lesson_type")
    private LessonType lessonType;
    @Column(name = "address")
    private String address;
    @Column(name = "classroom")
    private String classroom;
    @Column(name = "time")
    private String time;
    @Column(name = "group_code", nullable = false)
    private String group;

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public StudingWeek getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(StudingWeek weekDay) {
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

    public LessonType getLessonType() {
        return lessonType;
    }

    public void setLessonType(LessonType lessonType) {
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

    public int getWeekdayNum() {
        return weekDay == null ? 0 : weekDay.getWeekdayNum();
    }
}
