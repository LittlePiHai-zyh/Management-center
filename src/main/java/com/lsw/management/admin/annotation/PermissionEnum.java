package com.lsw.management.admin.annotation;

import lombok.Getter;

/**
 * @author lsw
 * @Date 2023/4/6 14:20
 * @desc 权限信息
 */
@Getter
public enum PermissionEnum {
    USER(1, "学生权限"),
    ADMIN(2, "管理员权限"),
    ADVISOR(3, "指导老师"),
    PROFESSIONAL_LEADER(4, "系负责人"),
    HEAD_OF_COLLEGE(5, "院负责人");
    ;

    PermissionEnum( Integer code,String name) {
        this.name = name;
        this.code = code;
    }

    /**
     * 权限名
     */
    private  String name;

    /**
     * 权限编号
     */
    private  Integer code;
}
