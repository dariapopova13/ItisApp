package com.university.itis.itisapp.model;

import com.university.itis.itisapp.model.common.AbstractEntity;
import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.core.WhitespaceTokenizerFactory;
import org.apache.lucene.analysis.ngram.EdgeNGramFilterFactory;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.search.annotations.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "news", schema = "itis_app")
@Indexed
@AnalyzerDef(name = "customanalyzer", tokenizer = @TokenizerDef(factory = WhitespaceTokenizerFactory.class), filters = {
        @TokenFilterDef(factory = LowerCaseFilterFactory.class),
        @TokenFilterDef(factory = EdgeNGramFilterFactory.class, params =
                {@Parameter(name = "maxGramSize", value = "15")})

})
public class News extends AbstractEntity {

    @Column(name = "news_text")
    @Field(index = Index.YES, store = Store.YES, analyze = Analyze.YES,
            analyzer = @Analyzer(definition = "customanalyzer"))
    private String newsText;
    @Column(name = "deadline")
    private Date deadline;
    @Column(name = "news_title")
    @Field(index = Index.YES, store = Store.YES, analyze = Analyze.YES,
            analyzer = @Analyzer(definition = "customanalyzer"))
    private String title;
    @ManyToOne
    @IndexedEmbedded
    private Course course;
    @Column(name = "year")
    private Integer year;
    @Column(name = "delete_date")
    private Date deleteDate;

    public Date getDeleteDate() {
        return deleteDate;
    }

    public void setDeleteDate(Date deleteDate) {
        this.deleteDate = deleteDate;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
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
