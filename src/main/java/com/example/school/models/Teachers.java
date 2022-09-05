package com.example.school.models;

import com.sun.istack.NotNull;


import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Teachers {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer teacherId;

    @NotNull
    private String name;

    @OneToMany
    @JoinColumn(name = "teacherId")
    private List<Timetable> timeTable;

    @ManyToMany(mappedBy = "teachers")
    private Set<Subjects> subjects = new HashSet<>();

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
