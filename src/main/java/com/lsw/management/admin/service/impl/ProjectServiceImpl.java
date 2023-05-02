package com.lsw.management.admin.service.impl;

import com.lsw.management.admin.mapper.ProjectMapper;
import com.lsw.management.admin.model.dto.project.ProjectAddDto;
import com.lsw.management.admin.model.dto.project.ProjectQueryDto;
import com.lsw.management.admin.model.dto.project.ProjectUpdateDto;
import com.lsw.management.admin.model.po.project.Project;
import com.lsw.management.admin.model.po.user.UserAccount;
import com.lsw.management.admin.model.vo.project.ProjectVo;
import com.lsw.management.admin.service.ProjectService;
import com.lsw.management.admin.service.UserAccountService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

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

    @Override
    public Integer studentTopicSelection(ProjectAddDto addDto, HttpServletRequest request) {
        Project project = new Project();
        BeanUtils.copyProperties(addDto,project);
        UserAccount currentUser = userAccountService.getCurrentUser(request);
        project.setStudentId(currentUser.getId());
        project.setStatus(1);
        project.setUpdateTime(new Date());
       return projectMapper.updateByPrimaryKeySelective(project);
    }

    @Override
    public Integer studentTopicSelectionCancel(ProjectUpdateDto updateDto) {
        Project project = new Project();
        BeanUtils.copyProperties(updateDto,project);
        return projectMapper.updateByPrimaryKeySelective(project);
    }

    @Override
    public List<ProjectVo> studentTopicSelectionProcess(ProjectQueryDto addDto) {
        return projectMapper.listAll(addDto);
    }

    @Override
    public Integer studentTopicSelectionUpdate(ProjectUpdateDto updateDto) {
        Project project = new Project();
        BeanUtils.copyProperties(updateDto,project);
        project.setUpdateTime(new Date());
        return projectMapper.updateByPrimaryKeySelective(project);
    }
}
