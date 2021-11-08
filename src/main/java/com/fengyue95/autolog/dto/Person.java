package com.fengyue95.autolog.dto;

import lombok.Getter;

/**
 * @author fengyue
 * @date 2021/11/8
 */
@Getter
public class Person {

    private String name;

    private Integer age;

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }


}
