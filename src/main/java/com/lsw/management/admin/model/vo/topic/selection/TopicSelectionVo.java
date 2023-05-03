package com.lsw.management.admin.model.vo.topic.selection;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import static com.lsw.management.common.constants.GlobalConstants.TIME_PATTERN;

/**
 * @Author: lsw
 * @desc
 * @date: 2023/4/30  18:25
 */
@Data
public class TopicSelectionVo {

    private Integer id;

    private String title;

    private String teacherName;

    private String major;

    private String studentType;

    private Integer studentNum;

    @JsonFormat(pattern =TIME_PATTERN, timezone = "GMT+8")
    @DateTimeFormat(pattern = TIME_PATTERN)
    private Date createTime;

    private String direction;

    private Integer state;

    private Integer schoolAuditResult;

    private Integer departmentAuditResult;

    private String result;
}
