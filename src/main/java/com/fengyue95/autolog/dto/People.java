package com.fengyue95.autolog.dto;

import lombok.Data;

/**
 * @author fengyue
 * @date 2022/1/10
 */
@Data
public class People {
    private String name;

    private Integer age;

    public People(String name, Integer age) {
        this.name = name;
        this.age = age;
    }


}
