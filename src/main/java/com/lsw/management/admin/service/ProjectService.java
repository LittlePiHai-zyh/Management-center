package com.lsw.management.admin.service;

import com.lsw.management.admin.model.dto.project.ProjectAddDto;
import com.lsw.management.admin.model.dto.project.ProjectQueryDto;
import com.lsw.management.admin.model.dto.project.ProjectUpdateDto;
import com.lsw.management.admin.model.vo.project.ProjectVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author: lsw
 * @desc
 * @date: 2023/4/29  22:13
 */
public interface ProjectService {
    Integer studentTopicSelection(ProjectAddDto addDto, HttpServletRequest request);

    Integer studentTopicSelectionCancel(ProjectUpdateDto updateDto);

    List<ProjectVo> studentTopicSelectionProcess(ProjectQueryDto addDto);

    Integer studentTopicSelectionUpdate(ProjectUpdateDto updateDto);
}
