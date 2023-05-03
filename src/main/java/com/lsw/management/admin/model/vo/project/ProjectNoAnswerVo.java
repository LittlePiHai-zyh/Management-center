package com.lsw.management.admin.model.vo.project;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import static com.lsw.management.common.constants.GlobalConstants.TIME_PATTERN;

/**
 * @Author: lsw
 * @desc
 * @date: 2023/5/3  17:07
 */
@Data
public class ProjectNoAnswerVo {

    private Integer id;

    /**
     * 毕设题目
     */
    private String title;

    private Integer status;

    @JsonFormat(pattern =TIME_PATTERN, timezone = "GMT+8")
    @DateTimeFormat(pattern = TIME_PATTERN)
    private Date startDate;

    @JsonFormat(pattern =TIME_PATTERN, timezone = "GMT+8")
    @DateTimeFormat(pattern = TIME_PATTERN)
    private Date endDate;

    private String studentName;

    private String teacherName;

    private String direction;

    private Integer major;

    private Integer topicId;

    private Integer studentType;
}
