package com.lsw.management.admin.model.dto.project;

import lombok.Data;

/**
 * @Author: lsw
 * @desc
 * @date: 2023/5/2  8:45
 */
@Data
public class ProjectQueryDto {

    private Integer status;

    private String studentName;

    private String teacherName;
}
