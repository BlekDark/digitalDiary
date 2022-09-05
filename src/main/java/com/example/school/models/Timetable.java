package com.example.school.models;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


@Entity
public class Timetable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer lessonId;

    @NotNull
    private Integer classId;

    @NotNull
    private LocalDate lessonData;

    @NotNull
    private Integer teacherId;

    @NotNull
    private Integer subjectId;


    @OneToMany
    @JoinColumn(name = "lessonId")
    private List<Marks> marks;


    public Integer getLessonId() {
        return lessonId;
    }

    public void setLessonId(Integer lessonId) {
        this.lessonId = lessonId;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public LocalDate getLessonData() {
        return lessonData;
    }

    public void setLessonData(LocalDate lessonData) {
        this.lessonData = lessonData;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }
}
