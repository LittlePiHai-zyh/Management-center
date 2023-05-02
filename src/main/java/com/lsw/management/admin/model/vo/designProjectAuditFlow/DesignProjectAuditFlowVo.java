package com.lsw.management.admin.model.vo.designProjectAuditFlow;

import lombok.Data;

import java.util.Date;

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

    private Date schoolAuditTime;

    private String schoolAuditResult;

    private String state;

    private Date departmentAuditTime;

    private String title;

    private String schoolAuditUserOpinion;

    private String departmentAuditOpinion;

    private String teacherName;
}
