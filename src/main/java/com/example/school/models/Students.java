package com.example.school.models;


import com.sun.istack.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.*;
import java.util.List;

@Entity
@Component("fooStudents")
public class Students {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer studentsId;

    @NotNull
    private String name;

    @NotNull
    private Integer age;

    @NotNull
    private Integer classId;

    @OneToMany
    @JoinColumn(name = "studentsId")
    private List<Marks> marks;

    public Integer getStudentsId() {
        return studentsId;
    }

    public void setStudentsId(Integer studentsId) {
        this.studentsId = studentsId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        String prefixErrorAgeText = "Age ";
        String suffixErrorAgeText =  " is small/large number age("+ age + ")!";
        int minAge = 6;
        int maxAge = 19;
        if (age < minAge || age > maxAge || age == 9) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, prefixErrorAgeText + age + suffixErrorAgeText);
        }
        this.age = age;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }
}
