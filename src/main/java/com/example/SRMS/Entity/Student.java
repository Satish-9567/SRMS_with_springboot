package com.example.SRMS.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer rollnumber;
    private String Name;
    private String Password;

    public Student() {
    }

    public Student(Integer rollnumber, String name, String password) {
        this.rollnumber = rollnumber;
        Name = name;
        Password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRollnumber() {
        return rollnumber;
    }

    public void setRollnumber(Integer rollnumber) {
        this.rollnumber = rollnumber;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
