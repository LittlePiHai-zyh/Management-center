package com.lsw.management.admin.service.impl;

import com.lsw.management.admin.mapper.DesignProjectAuditFlowMapper;
import com.lsw.management.admin.mapper.ProjectMapper;
import com.lsw.management.admin.mapper.TopicSelectionMapper;
import com.lsw.management.admin.model.dto.project.*;
import com.lsw.management.admin.model.po.designProjectAuditFlow.DesignProjectAuditFlow;
import com.lsw.management.admin.model.po.project.Project;
import com.lsw.management.admin.model.po.topic.selection.TopicSelection;
import com.lsw.management.admin.model.po.user.UserAccount;
import com.lsw.management.admin.model.vo.project.ProjectNoAnswerVo;
import com.lsw.management.admin.model.vo.project.ProjectVo;
import com.lsw.management.admin.service.ProjectService;
import com.lsw.management.admin.service.UserAccountService;
import com.lsw.management.common.constants.ErrorCode;
import com.lsw.management.common.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @Author: lsw
 * @desc
 * @date: 2023/4/29  22:13
 */
@Service
public class ProjectServiceImpl implements ProjectService {

    @Resource
    ProjectMapper projectMapper;

    @Resource
    UserAccountService userAccountService;

    @Resource
    DesignProjectAuditFlowMapper designProjectAuditFlowMapper;

    @Resource
    TopicSelectionMapper topicSelectionMapper;

    @Resource
    TransactionTemplate transactionTemplate;

    @Override
    public Integer studentTopicSelection(ProjectAddDto addDto, HttpServletRequest request) {
        return transactionTemplate.execute(status -> {
            Project project = new Project();
            BeanUtils.copyProperties(addDto, project);
            UserAccount currentUser = userAccountService.getCurrentUser(request);
            project.setStudentId(currentUser.getId());
            project.setStatus(0);
            project.setDeleted(0);
            project.setCreateTime(new Date());
            int res = projectMapper.insert(project);
            //查询可选学生数量
            Example example1 = new Example(TopicSelection.class);
            example1.createCriteria()
                    .andEqualTo(TopicSelection.DELETED, 0)
                    .andEqualTo(TopicSelection.ID, addDto.getTopicId());
            TopicSelection topicSelection = topicSelectionMapper.selectOneByExample(example1);
            if (topicSelection != null && (topicSelection.getStudent() + 1) == topicSelection.getStudentNum()) {
                //更新状态为已被选
                Example example = new Example(DesignProjectAuditFlow.class);
                example.createCriteria()
                        .andEqualTo(DesignProjectAuditFlow.DELETED, 0)
                        .andEqualTo(DesignProjectAuditFlow.TOPIC_ID, addDto.getTopicId());
                DesignProjectAuditFlow designProjectAuditFlow = new DesignProjectAuditFlow();
                designProjectAuditFlow.setState(3);
                designProjectAuditFlowMapper.updateByExampleSelective(designProjectAuditFlow, example);
            } else {
                TopicSelection topicSelection1 = new TopicSelection();
                topicSelection1.setId(addDto.getTopicId());
                topicSelection1.setStudent(topicSelection.getStudent() + 1);
                topicSelectionMapper.updateByPrimaryKeySelective(topicSelection1);
            }
            return res;
        });
    }

    @Override
    public Integer studentTopicSelectionCancel(ProjectUpdateDto updateDto) {
        Project project = new Project();
        BeanUtils.copyProperties(updateDto, project);
        return projectMapper.updateByPrimaryKeySelective(project);
    }

    @Override
    public List<ProjectVo> studentTopicSelectionProcess(ProjectQueryDto addDto) {
        return projectMapper.listAll(addDto);
    }

    @Override
    public Integer studentTopicSelectionUpdate(ProjectUpdateDto updateDto) {
        Project project = new Project();
        BeanUtils.copyProperties(updateDto, project);
        project.setUpdateTime(new Date());
        return projectMapper.updateByPrimaryKeySelective(project);
    }

    @Override
    public Integer noAnswer(String idStr) {
        if(StringUtils.isBlank(idStr)){
            throw new BusinessException(ErrorCode.INVALID_PARAMS);
        }
        int id=Integer.parseInt(idStr);
        return transactionTemplate.execute(status -> {
            Project build = Project.builder()
                    .id(id)
                    .updateTime(new Date())
                    .status(3)
                    .build();
            return projectMapper.updateByPrimaryKeySelective(build);
        });
    }


    @Override
    public List<ProjectNoAnswerVo> noAnswerListAll(ProjectNoAnswerQueryDto queryDto) {
        return projectMapper.noAnswerListAll(queryDto);
    }

    @Override
    public Integer noAnswerAdd(ProjectNoAnswerAddDto addDto) {
        return transactionTemplate.execute(status -> {
            Project build = Project.builder()
                    .id(addDto.getId())
                    .updateTime(new Date())
                    .build();
            if(addDto.getAgree()==1){
                build.setStatus(1);
            }else {
                build.setStatus(0);
            }
            int i = projectMapper.updateByPrimaryKeySelective(build);
            DesignProjectAuditFlow designProjectAuditFlow = new DesignProjectAuditFlow();
            designProjectAuditFlow.setNoAnswerOpinion(addDto.getNoAnswerOpinion());
            Example example = new Example(DesignProjectAuditFlow.class);
            example.createCriteria()
                    .andEqualTo(DesignProjectAuditFlow.DELETED, 0)
                    .andEqualTo(DesignProjectAuditFlow.TOPIC_ID, addDto.getTopicId());
            designProjectAuditFlowMapper.updateByExampleSelective(designProjectAuditFlow, example);
            return i;
        });
    }
}
