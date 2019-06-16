package com.icsoft.reflection.model;

import com.icsoft.reflection.anotation.Column;
import com.icsoft.reflection.anotation.PrimaryKey;
import lombok.Data;

@Data
public class Person {

    @PrimaryKey
    private long id;

    @Column
    private String name;

    @Column
    private int age;


    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public static Person of(String name, int age) {
        return new Person(name, age);
    }

    @Override
    public String toString() {
        return "Person [id=" + id + ", name=" + name + ", age=" + age + "]";
    }
}

