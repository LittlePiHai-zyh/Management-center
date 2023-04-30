package com.lsw.management.common.constants;

import lombok.Getter;

/**
 * @Author: lsw
 * @desc
 * @date: 2023/4/20  21:55
 */
@Getter
public enum ProfessionalEnum {
    TEACHER(1, "教师"),
    LECTURER(2, "讲师"),
    ASSOCIATE_PROFESSOR(3, "副教授"),
    PROFESSOR(4, "教授"),
    ;

    private Integer code;

    private String name;

    ProfessionalEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
}
