package com.university.itis.itisapp.dto;

import com.university.itis.itisapp.dto.common.AbstractDto;
import com.university.itis.itisapp.model.News;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NewsDto extends AbstractDto {

    private String newsText;
    private Date deadline;
    private String title;
    private Integer year;
    private CourseDto course;

    public NewsDto() {
    }

    public NewsDto(News news) {
        super(news);
        this.newsText = news.getNewsText();
        this.deadline = news.getDeadline();
        this.title = news.getTitle();
        if (news.getCourse() != null) {
            this.course= new CourseDto(news.getCourse());
        }
        this.year = news.getYear();
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public CourseDto getCourse() {
        return course;
    }

    public void setCourse(CourseDto course) {
        this.course = course;
    }

    public String getNewsText() {
        return newsText;
    }

    public void setNewsText(String newsText) {
        this.newsText = newsText;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
