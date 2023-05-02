package com.lsw.management.admin.model.dto.designProjectAuditFlow;

import lombok.Data;

import java.util.Date;

/**
 * @Author: lsw
 * @desc 系审核类
 * @date: 2023/5/1  11:29
 */
@Data
public class DesignProjectAuditFlowsDepartmentAuditAddDto {

    private Integer id;

    private Date departmentAuditTime;

    private String departmentAuditOpinion;

    private Integer topicId;

    private Integer departmentAuditResult;
}
