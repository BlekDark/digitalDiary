package com.example.school.models;

import com.sun.istack.NotNull;
import javax.persistence.*;
import java.util.List;


@Entity // This tells Hibernate to make a table out of this class
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = { "classNumber", "letter" }) })
public class Classes {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer classId;

    @NotNull
    private Integer classNumber;


    @NotNull
    private Character letter;

    @OneToMany
    @JoinColumn(name = "classId")
    private List<Students> students;

    @OneToMany
    @JoinColumn(name = "classId")
    private List<Timetable> timeTable;

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Integer getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(Integer classNumber) {
        this.classNumber = classNumber;
    }

    public Character getLetter() {
        return letter;
    }

    public void setLetter(Character letter) {
        this.letter = letter;
    }
}


