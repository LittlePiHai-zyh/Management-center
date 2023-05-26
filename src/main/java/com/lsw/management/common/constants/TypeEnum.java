package com.lsw.management.common.constants;

import lombok.Getter;

/**
 * @Author: lsw
 * @desc
 * @date: 2023/4/20  21:56
 */
@Getter
public enum TypeEnum {

    UNDERGRADUATE(1, "本科生"),
    MASTER(2, "研究生"),
    DOCTOR(3, "博士生"),
    TEACHER(4, "老师"),
    ADMIN(5, "管理员"),
    ;

    private Integer code;

    private String name;

    TypeEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
}
