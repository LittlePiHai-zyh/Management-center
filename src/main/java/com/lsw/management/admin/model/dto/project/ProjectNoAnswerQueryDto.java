package com.lsw.management.admin.model.dto.project;

import lombok.Data;

/**
 * @Author: lsw
 * @desc
 * @date: 2023/5/3  17:05
 */
@Data
public class ProjectNoAnswerQueryDto {

    private String teacherName;

    private String studentName;

    private String title;

    private Integer major;

    private Integer studentType;

    private String direction;
}
