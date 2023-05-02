package com.lsw.management.admin.service;

import com.lsw.management.admin.model.dto.designProjectAuditFlow.DesignProjectAuditFlowQueryDto;
import com.lsw.management.admin.model.dto.designProjectAuditFlow.DesignProjectAuditFlowsDepartmentAuditAddDto;
import com.lsw.management.admin.model.dto.designProjectAuditFlow.DesignProjectAuditFlowsSchoolAuditAddDto;
import com.lsw.management.admin.model.vo.designProjectAuditFlow.DesignProjectAuditFlowVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author: lsw
 * @desc
 * @date: 2023/4/29  22:10
 */
public interface DesignProjectAuditFlowService {
    Integer departmentAuditAdd(DesignProjectAuditFlowsDepartmentAuditAddDto addDto, HttpServletRequest request);

    Integer schoolAuditAdd(DesignProjectAuditFlowsSchoolAuditAddDto addDto, HttpServletRequest request);

    List<DesignProjectAuditFlowVo> departmentAuditQuery(DesignProjectAuditFlowQueryDto queryDto);

    List<DesignProjectAuditFlowVo> schoolAuditQuery(DesignProjectAuditFlowQueryDto queryDto);

    List<DesignProjectAuditFlowVo> listAll(DesignProjectAuditFlowQueryDto queryDto);
}
