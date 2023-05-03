package com.lsw.management.admin.service.impl;

import com.lsw.management.admin.mapper.DesignProjectAuditFlowMapper;
import com.lsw.management.admin.mapper.ProjectMapper;
import com.lsw.management.admin.model.dto.designProjectAuditFlow.DesignProjectAuditFlowQueryDto;
import com.lsw.management.admin.model.dto.designProjectAuditFlow.DesignProjectAuditFlowsDepartmentAuditAddDto;
import com.lsw.management.admin.model.dto.designProjectAuditFlow.DesignProjectAuditFlowsSchoolAuditAddDto;
import com.lsw.management.admin.model.po.designProjectAuditFlow.DesignProjectAuditFlow;
import com.lsw.management.admin.model.po.user.UserAccount;
import com.lsw.management.admin.model.vo.designProjectAuditFlow.DesignProjectAuditFlowPercent;
import com.lsw.management.admin.model.vo.designProjectAuditFlow.DesignProjectAuditFlowVo;
import com.lsw.management.admin.service.DesignProjectAuditFlowService;
import com.lsw.management.admin.service.UserAccountService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @Author: lsw
 * @desc
 * @date: 2023/4/29  22:11
 */
@Service
public class DesignProjectAuditFlowServiceImpl implements DesignProjectAuditFlowService {

    @Resource
    DesignProjectAuditFlowMapper designProjectAuditFlowMapper;

    @Resource
    UserAccountService userAccountService;

    @Resource
    ProjectMapper projectMapper;


    @Resource
    TransactionTemplate transactionTemplate;

    @Override
    public Integer departmentAuditAdd(DesignProjectAuditFlowsDepartmentAuditAddDto addDto, HttpServletRequest request) {
        DesignProjectAuditFlow designProjectAuditFlow = new DesignProjectAuditFlow();
        BeanUtils.copyProperties(addDto, designProjectAuditFlow);
        designProjectAuditFlow.setState(1);
        designProjectAuditFlow.setDepartmentAuditTime(new Date());
        UserAccount currentUser = userAccountService.getCurrentUser(request);
        designProjectAuditFlow.setDepartmentAuditUserId(currentUser.getId());
        return designProjectAuditFlowMapper.updateByPrimaryKeySelective(designProjectAuditFlow);
    }

    @Override
    public Integer schoolAuditAdd(DesignProjectAuditFlowsSchoolAuditAddDto addDto, HttpServletRequest request) {
        return transactionTemplate.execute(status -> {
            DesignProjectAuditFlow designProjectAuditFlow = new DesignProjectAuditFlow();
            BeanUtils.copyProperties(addDto, designProjectAuditFlow);
            designProjectAuditFlow.setSchoolAuditTime(new Date());
            //院负责人审核通过则将审核状态修改为同通过审核
           designProjectAuditFlow.setState(2);
            return designProjectAuditFlowMapper.updateByPrimaryKeySelective(designProjectAuditFlow);
        });
    }

    @Override
    public List<DesignProjectAuditFlowVo> departmentAuditQuery(DesignProjectAuditFlowQueryDto queryDto) {
        return designProjectAuditFlowMapper.departmentAuditListAll(queryDto);
    }

    @Override
    public List<DesignProjectAuditFlowVo> schoolAuditQuery(DesignProjectAuditFlowQueryDto queryDto) {
        return designProjectAuditFlowMapper.schoolAuditListAll(queryDto);
    }

    @Override
    public List<DesignProjectAuditFlowVo> listAll(DesignProjectAuditFlowQueryDto queryDto) {
        return designProjectAuditFlowMapper.listAll(queryDto);
    }

    @Override
    public List<DesignProjectAuditFlowPercent> designProjectAuditFlowPercent() {
        return  designProjectAuditFlowMapper.designProjectAuditFlowPercent();
    }
}
