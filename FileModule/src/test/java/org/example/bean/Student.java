package org.example.bean;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author Genius
 * @date 2023/04/20 13:46
 **/
@Data
public class Student{
    private String name;
    private int age;
    private String school;
    private String major;
    private List<String> hobby;
    private Map<String, String> info;

    public Student(String name, int age, String school, String major, List<String> hobby, Map<String, String> info) {
        this.name = name;
        this.age = age;
        this.school = school;
        this.major = major;
        this.hobby = hobby;
        this.info = info;
    }

}
