package com.lsw.management.admin.model.dto.menu;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @Author: lsw
 * @desc
 * @date: 2023/4/30  8:23
 */
@Data
public class MenuAddDto {

    private Integer pid;

    @NotEmpty(message = "CAN_NOT_EMPTY")
    private String title;

    @NotEmpty(message = "CAN_NOT_EMPTY")
    private String icon;

    @NotEmpty(message = "CAN_NOT_EMPTY")
    private String iconColor;

    @NotEmpty(message = "CAN_NOT_EMPTY")
    private String path;

    @NotEmpty(message = "CAN_NOT_EMPTY")
    private String name;

    private Integer hidden;

    @NotEmpty(message = "CAN_NOT_EMPTY")
    private String permissions;

    @NotEmpty(message = "CAN_NOT_EMPTY")
    private String component;
}
