package com.lsw.management.admin.model.po.project;

import lombok.Data;

import java.util.Date;

/**
 * @Author: lsw
 * @desc
 * @date: 2023/5/4  10:04
 */
@Data
public class ProjectInfo {

    private Integer id;

    private Date startDate;

    private Date endDate;

    private String title;

    private String teacherName;

    private Integer major;

    private String direction;

    private Integer studentType;

    private Integer status;
}
