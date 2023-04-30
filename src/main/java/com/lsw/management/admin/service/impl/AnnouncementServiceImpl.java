package com.lsw.management.admin.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lsw.management.admin.mapper.AnnouncementMapper;
import com.lsw.management.admin.model.dto.announcement.AnnouncementAddDto;
import com.lsw.management.admin.model.dto.announcement.AnnouncementQueryDto;
import com.lsw.management.admin.model.dto.announcement.AnnouncementUpdateDto;
import com.lsw.management.admin.model.po.announcement.Announcement;
import com.lsw.management.admin.model.po.user.UserAccount;
import com.lsw.management.admin.model.vo.PageInfoVo;
import com.lsw.management.admin.model.vo.announcement.AnnouncementVo;
import com.lsw.management.admin.service.AnnouncementService;
import com.lsw.management.admin.service.UserAccountService;
import com.lsw.management.common.constants.ErrorCode;
import com.lsw.management.common.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: lsw
 * @desc
 * @date: 2023/4/29  22:11
 */
@Service
public class AnnouncementServiceImpl implements AnnouncementService {

    @Resource
    AnnouncementMapper announcementMapper;

    @Resource
    UserAccountService userAccountService;

    @Override
    public Integer add(AnnouncementAddDto addDto, HttpServletRequest request) {
        Announcement announcement = new Announcement();
        BeanUtils.copyProperties(addDto, announcement);
        announcement.setCreateTime(new Date());
        announcement.setDeleted(0);
        UserAccount currentUser = userAccountService.getCurrentUser(request);
        announcement.setAccountId(currentUser.getId());
        return announcementMapper.insert(announcement);
    }

    @Override
    public Integer delete(String ids) {
        if (StringUtils.isBlank(ids)) {
            throw new BusinessException(ErrorCode.INVALID_PARAMS);
        }
        AtomicInteger atomicInteger = new AtomicInteger();
        String[] idArr = ids.split(",");
        Arrays.stream(idArr).map(Integer::parseInt).forEach(id -> {
            Announcement deleteAnnouncement = Announcement.builder()
                    .id(id)
                    .deleted(1)
                    .updateTime(new Date())
                    .build();
            int i = announcementMapper.updateByPrimaryKeySelective(deleteAnnouncement);
            atomicInteger.getAndAdd(i);
        });
        return atomicInteger.get();
    }

    @Override
    public List<AnnouncementVo> listAll(AnnouncementQueryDto queryDto, String permissions) {
        return announcementMapper.pageList(queryDto);
    }

    @Override
    public PageInfoVo<AnnouncementVo> pageList(AnnouncementQueryDto queryDto) {
        int page = 1;
        int limit = 10;
        if (queryDto.getPage() != null) {
            page = queryDto.getPage();
        }
        if (queryDto.getLimit() != null) {
            limit = queryDto.getLimit();
        }
        PageHelper.startPage(page, limit, "create_time desc");
        Page<AnnouncementVo> pageList = (Page<AnnouncementVo>) announcementMapper.pageList(queryDto);
        PageInfoVo<AnnouncementVo> pageInfoVo = new PageInfoVo<>();
        pageInfoVo.setPages(pageList.getPages());
        pageInfoVo.setTotal(pageList.getTotal());
        pageInfoVo.setData(pageList.getResult());
        return pageInfoVo;
    }

    @Override
    public Integer update(AnnouncementUpdateDto updateDto) {
        Announcement announcement = new Announcement();
        BeanUtils.copyProperties(updateDto, announcement);
        announcement.setUpdateTime(new Date());
        return announcementMapper.updateByPrimaryKeySelective(announcement);
    }
}
