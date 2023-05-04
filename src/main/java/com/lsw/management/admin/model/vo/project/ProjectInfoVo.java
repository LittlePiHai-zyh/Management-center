package com.lsw.management.admin.model.vo.project;

import lombok.Data;

import java.util.Date;

/**
 * @Author: lsw
 * @desc
 * @date: 2023/5/4  10:04
 */
@Data
public class ProjectInfoVo {

    private Integer id;

    private Date startDate;

    private Date endDate;

    private String title;

    private String teacherName;

    private String major;

    private String direction;

    private String studentType;

    private Integer status;
}
