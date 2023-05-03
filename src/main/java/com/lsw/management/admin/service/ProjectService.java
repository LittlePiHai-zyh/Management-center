package com.lsw.management.admin.service;

import com.lsw.management.admin.model.dto.project.*;
import com.lsw.management.admin.model.vo.project.ProjectNoAnswerVo;
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

    Integer noAnswer(String id);

    List<ProjectNoAnswerVo> noAnswerListAll(ProjectNoAnswerQueryDto queryDto);

    Integer noAnswerAdd(ProjectNoAnswerAddDto addDto);
}
