package com.design.mode.ioc.day01;

/**
 * Created by fengye on 2017/9/13.
 */
public class ManWhoLovesPet {
    private String name;
    private int age;
    private String sex;
    private Pet pet;

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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public ManWhoLovesPet() {
    }

    public ManWhoLovesPet(String name, int age, String sex, Pet pet) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.pet = pet;
    }

    public boolean hasPet(){
        return this.pet != null;
    }

    @Override
    public String toString() {
        return "ManWhoLovesPet{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", pet=" + pet +
                '}';
    }
}
