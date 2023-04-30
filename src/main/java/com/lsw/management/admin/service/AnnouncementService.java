package com.lsw.management.admin.service;

import com.lsw.management.admin.model.dto.announcement.AnnouncementAddDto;
import com.lsw.management.admin.model.dto.announcement.AnnouncementQueryDto;
import com.lsw.management.admin.model.dto.announcement.AnnouncementUpdateDto;
import com.lsw.management.admin.model.vo.PageInfoVo;
import com.lsw.management.admin.model.vo.announcement.AnnouncementVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author: lsw
 * @desc
 * @date: 2023/4/29  22:10
 */
public interface AnnouncementService {

    /**
     * 新增公告
     * @param addDto /
     * @param request /
     * @return /
     */
    Integer add(AnnouncementAddDto addDto, HttpServletRequest request);


    /**
     * 公告删除
     * @param ids /
     * @return /
     */
    Integer delete(String ids);

    /**
     * 查询全部公告
     * @param queryDto /
     * @param permissions /
     * @return /
     */
    List<AnnouncementVo> listAll(AnnouncementQueryDto queryDto, String permissions);

    /**
     * 公告分页查询
     * @param queryDto /
     * @return /
     */
    PageInfoVo<AnnouncementVo> pageList(AnnouncementQueryDto queryDto);

    Integer update(AnnouncementUpdateDto updateDto);
}
