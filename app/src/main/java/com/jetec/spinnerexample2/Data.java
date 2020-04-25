package com.jetec.spinnerexample2;

public class Data {

    private String name;
    private int age;
    private Boolean student;

    public Data(String name, int age, Boolean student) {
        this.name = name;
        this.age = age;
        this.student = student;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Boolean getStudent() {
        return student;
    }
}
