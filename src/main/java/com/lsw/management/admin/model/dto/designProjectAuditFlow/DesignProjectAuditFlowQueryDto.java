package com.lsw.management.admin.model.dto.designProjectAuditFlow;

import lombok.Data;

/**
 * @Author: lsw
 * @desc
 * @date: 2023/5/1  11:23
 */
@Data
public class DesignProjectAuditFlowQueryDto {

    /**
     * 院负责人名字
     */
    private String schoolAuditName;

    /**
     * 系负责人名字
     */
    private String departmentAuditName;

    /**
     * 状态
     */
    private Integer state;

    /**
     * 毕设题目
     */
    private String title;
}
