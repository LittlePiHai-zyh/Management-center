package com.lsw.management.admin.service;

import com.lsw.management.admin.model.dto.topic.selection.TopicSelectionAddDto;
import com.lsw.management.admin.model.dto.topic.selection.TopicSelectionQueryDto;
import com.lsw.management.admin.model.dto.topic.selection.TopicSelectionUpdateDto;
import com.lsw.management.admin.model.vo.PageInfoVo;
import com.lsw.management.admin.model.vo.topic.selection.MajorVo;
import com.lsw.management.admin.model.vo.topic.selection.TopicSelectionVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author: lsw
 * @desc
 * @date: 2023/4/29  22:13
 */
public interface TopicSelectionService {
    /**
     * 题目新增
     * @param addDto /
     * @param request /
     * @return /
     */
    Integer add(TopicSelectionAddDto addDto, HttpServletRequest request);

    /**
     * 删除题目
     * @param ids /
     * @return /
     */
    Integer delete(String ids);

    /**
     * 修改题目
     * @param updateDto /
     * @return /
     */
    Integer update(TopicSelectionUpdateDto updateDto);

    /**
     * 查询全部
     * @param queryDto /
     * @return /
     */
    List<TopicSelectionVo> listAll(TopicSelectionQueryDto queryDto);

    /**
     * 分页查询
     * @param queryDto /
     * @return /
     */
    PageInfoVo<TopicSelectionVo> pageList(TopicSelectionQueryDto queryDto);

    List<MajorVo> getMajor();
}
