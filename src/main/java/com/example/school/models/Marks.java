package com.example.school.models;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
public class Marks {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer MarkId;

    @NotNull
    private Integer lessonId;

    @NotNull
    private Integer mark;

    @NotNull
    private Integer markType;

    @NotNull
    private Integer studentsId;

    @NotNull
    public Integer getMarkId() {
        return MarkId;
    }


    public void setMarkId(Integer markId) {
        MarkId = markId;
    }

    public Integer getLessonId() {
        return lessonId;
    }

    public void setLessonId(Integer lessonId) {
        this.lessonId = lessonId;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public Integer getMarkType() {
        return markType;
    }

    public void setMarkType(Integer markType) {
        this.markType = markType;
    }

    public Integer getStudentsId() {
        return studentsId;
    }

    public void setStudentsId(Integer studentsId) {
        this.studentsId = studentsId;
    }
}
