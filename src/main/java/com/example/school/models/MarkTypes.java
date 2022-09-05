package com.example.school.models;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.List;


@Entity
public class MarkTypes {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer markId;

    @NotNull
    private String type;

    @OneToMany
    @JoinColumn(name = "markId")
    private List<Marks> marks;

    public Integer getMarkId() {
        return markId;
    }

    public void setMarkId(Integer markId) {
        this.markId = markId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
