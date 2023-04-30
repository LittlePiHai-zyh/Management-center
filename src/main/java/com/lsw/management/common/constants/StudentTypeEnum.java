package com.lsw.management.common.constants;

import lombok.Getter;

/**
 * @Author: lsw
 * @desc
 * @date: 2023/4/20  21:56
 */
@Getter
public enum StudentTypeEnum {

    UNDERGRADUATE(1, "本科生"),
    MASTER(2, "研究生"),
    DOCTOR(3, "博士生"),
    ;

    private Integer code;

    private String name;

    StudentTypeEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
}
