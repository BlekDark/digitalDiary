package com.example.school.models;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
public class Subjects {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer subjectId;

    @NotNull
    @Column(unique = true)
    private String name;

    @OneToMany
    @JoinColumn(name = "subjectId")
    private List<Timetable> timeTable;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "teachersSubjects",
            joinColumns = { @JoinColumn(name = "subjectId") },
            inverseJoinColumns = { @JoinColumn(name = "teachersId") }
    )
    Set<Teachers> teachers = new HashSet<>();

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
