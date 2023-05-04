package com.lsw.management.admin.model.vo.project;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import static com.lsw.management.common.constants.GlobalConstants.TIME_PATTERN;

/**
 * @Author: lsw
 * @desc
 * @date: 2023/5/4  10:04
 */
@Data
public class ProjectInfoVo {

    private Integer id;

    @JsonFormat(pattern =TIME_PATTERN, timezone = "GMT+8")
    @DateTimeFormat(pattern = TIME_PATTERN)
    private Date startDate;

    @JsonFormat(pattern =TIME_PATTERN, timezone = "GMT+8")
    @DateTimeFormat(pattern = TIME_PATTERN)
    private Date endDate;

    private String title;

    private String teacherName;

    private String major;

    private String direction;

    private String studentType;

    private Integer status;
}
