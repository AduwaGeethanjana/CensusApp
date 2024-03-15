package com.example.censusap_1;

public class Firebase{

    String name,gender;
    int age;

    public Firebase(String name, String gender, int age) {
        this.name = name;
        this.gender = gender;
        this.age = age;
    }


    public Firebase() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
