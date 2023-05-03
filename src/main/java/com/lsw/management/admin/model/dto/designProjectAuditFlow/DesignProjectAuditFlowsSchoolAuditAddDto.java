package com.lsw.management.admin.model.dto.designProjectAuditFlow;

import lombok.Data;

import java.util.Date;

/**
 * @Author: lsw
 * @desc 院审核类
 * @date: 2023/5/1  11:23
 */
@Data
public class DesignProjectAuditFlowsSchoolAuditAddDto {

    private Integer id;

    private Date schoolAuditTime;

    private Integer schoolAuditResult;

    private Date departmentAuditTime;

    private String departmentAuditOpinion;

    private String schoolAuditUserOpinion;

    private Integer departmentAuditResult;
}
