package com.lsw.management.admin.mapper;

import com.lsw.management.admin.model.dto.designProjectAuditFlow.DesignProjectAuditFlowQueryDto;
import com.lsw.management.admin.model.po.designProjectAuditFlow.DesignProjectAuditFlow;
import com.lsw.management.admin.model.vo.designProjectAuditFlow.DesignProjectAuditFlowPercent;
import com.lsw.management.admin.model.vo.designProjectAuditFlow.DesignProjectAuditFlowVo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
* @author 17533
* @description 针对表【design_project_audit_flow】的数据库操作Mapper
* @createDate 2023-04-29 21:16:50
* @Entity com.lsw.management.admin.model.po.designProjectAuditFlow.DesignProjectAuditFlow
*/
public interface DesignProjectAuditFlowMapper extends Mapper<DesignProjectAuditFlow> {

    /**
     * 系未审核查询
     * @param queryDto /
     * @return /
     */
    List<DesignProjectAuditFlowVo> departmentAuditListAll(@Param("queryDto") DesignProjectAuditFlowQueryDto queryDto);

    /**
     * 院未审核查询
     * @param queryDto /
     * @return /
     */
    List<DesignProjectAuditFlowVo> schoolAuditListAll(@Param("queryDto") DesignProjectAuditFlowQueryDto queryDto);

    List<DesignProjectAuditFlowVo> listAll(@Param("queryDto") DesignProjectAuditFlowQueryDto queryDto);

    List<DesignProjectAuditFlowPercent> designProjectAuditFlowPercent();

    DesignProjectAuditFlow selectCanDeleteInfo(@Param("id") Integer id);
}




