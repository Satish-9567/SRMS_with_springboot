package com.example.SRMS.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Result {
    @Id
    private Integer id;
    private Integer hindi;
    private Integer english;
    private Integer science;
    private Integer math;
    private Integer social_science;

    public Result() {
    }

    public Result(Integer id, Integer hindi, Integer english, Integer science, Integer math, Integer social_science) {
        this.id = id;
        this.hindi = hindi;
        this.english = english;
        this.science = science;
        this.math = math;
        this.social_science = social_science;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHindi() {
        return hindi;
    }

    public void setHindi(Integer hindi) {
        this.hindi = hindi;
    }

    public Integer getEnglish() {
        return english;
    }

    public void setEnglish(Integer english) {
        this.english = english;
    }

    public Integer getScience() {
        return science;
    }

    public void setScience(Integer science) {
        this.science = science;
    }

    public Integer getMath() {
        return math;
    }

    public void setMath(Integer math) {
        this.math = math;
    }

    public Integer getSocial_science() {
        return social_science;
    }

    public void setSocial_science(Integer social_science) {
        this.social_science = social_science;
    }
}
