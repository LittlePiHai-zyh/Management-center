package com.lsw.management.admin.model.dto.project;

import lombok.Data;

import java.util.Date;

/**
 * @Author: lsw
 * @desc
 * @date: 2023/5/2  8:45
 */
@Data
public class ProjectAddDto {

    private Integer id;

    private Integer topicId;

    private Integer status;

    private Date startDate;

    private Date endDate;

    private Integer studentId;
}
