package com.hie2j.ad1023;

public class Student {
    private String num;
    private String name;
    private int age;
    private int imgId;

    public Student(){

    }

    public Student(String num, String name, int age, int imgId) {
        this.num = num;
        this.name = name;
        this.age = age;
        this.imgId = imgId;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }
}
