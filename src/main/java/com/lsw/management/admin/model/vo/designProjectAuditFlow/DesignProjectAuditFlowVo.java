package com.lsw.management.admin.model.vo.designProjectAuditFlow;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import static com.lsw.management.common.constants.GlobalConstants.TIME_PATTERN;

/**
 * @Author: lsw
 * @desc
 * @date: 2023/5/1  11:26
 */
@Data
public class DesignProjectAuditFlowVo {

    private Integer id;

    private String departmentAuditResult;

    private String departmentAuditUserName;

    private String schoolAuditUserName;

    @JsonFormat(pattern =TIME_PATTERN, timezone = "GMT+8")
    @DateTimeFormat(pattern = TIME_PATTERN)
    private Date schoolAuditTime;

    private String schoolAuditResult;

    private String state;

    @JsonFormat(pattern =TIME_PATTERN, timezone = "GMT+8")
    @DateTimeFormat(pattern = TIME_PATTERN)
    private Date departmentAuditTime;

    private String title;

    private String schoolAuditUserOpinion;

    private String departmentAuditOpinion;

    private String teacherName;

    private String studentNum;

    private String direction;

    private Integer major;

    private Integer studentType;
}
