package com.lsw.management.admin.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lsw.management.admin.mapper.TopicSelectionMapper;
import com.lsw.management.admin.model.dto.topic.selection.TopicSelectionAddDto;
import com.lsw.management.admin.model.dto.topic.selection.TopicSelectionQueryDto;
import com.lsw.management.admin.model.dto.topic.selection.TopicSelectionUpdateDto;
import com.lsw.management.admin.model.po.topic.selection.TopicSelection;
import com.lsw.management.admin.model.po.user.UserAccount;
import com.lsw.management.admin.model.vo.PageInfoVo;
import com.lsw.management.admin.model.vo.topic.selection.MajorVo;
import com.lsw.management.admin.model.vo.topic.selection.TopicSelectionVo;
import com.lsw.management.admin.service.TopicSelectionService;
import com.lsw.management.admin.service.UserAccountService;
import com.lsw.management.common.constants.ErrorCode;
import com.lsw.management.common.constants.MajorEnum;
import com.lsw.management.common.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: lsw
 * @desc
 * @date: 2023/4/29  22:13
 */
@Service
public class TopicSelectionServiceImpl implements TopicSelectionService {

    @Resource
    TopicSelectionMapper topicSelectionMapper;

    @Resource
    UserAccountService userAccountService;

    @Override
    public Integer add(TopicSelectionAddDto addDto, HttpServletRequest request) {
        TopicSelection topicSelection = new TopicSelection();
        BeanUtils.copyProperties(addDto,topicSelection);
        topicSelection.setCreateTime(new Date());
        topicSelection.setDeleted((byte)0);
        UserAccount currentUser = userAccountService.getCurrentUser(request);
        topicSelection.setTeacherId(currentUser.getId());
       return topicSelectionMapper.insert(topicSelection);
    }

    @Override
    public Integer delete(String ids) {
        if(StringUtils.isBlank(ids)){
            throw new BusinessException(ErrorCode.INVALID_PARAMS);
        }
        String[] idArr = ids.split(",");
        AtomicInteger atomicInteger = new AtomicInteger();
        Arrays.stream(idArr).map(Integer::parseInt).forEach(id->{
            TopicSelection topicSelection = TopicSelection.builder()
                    .id(id)
                    .deleted((byte) 1)
                    .updateTime(new Date())
                    .build();
            int i = topicSelectionMapper.updateByPrimaryKeySelective(topicSelection);
            atomicInteger.getAndAdd(i);
        });
        return atomicInteger.get();
    }

    @Override
    public Integer update(TopicSelectionUpdateDto updateDto) {
        TopicSelection topicSelection = new TopicSelection();
        BeanUtils.copyProperties(updateDto,topicSelection);
        topicSelection.setUpdateTime(new Date());
       return topicSelectionMapper.updateByPrimaryKeySelective(topicSelection);
    }

    @Override
    public List<TopicSelectionVo> listAll(TopicSelectionQueryDto queryDto) {
       return topicSelectionMapper.pageList(queryDto);
    }

    @Override
    public PageInfoVo<TopicSelectionVo> pageList(TopicSelectionQueryDto queryDto) {
        int page = 1;
        int limit = 10;
        if (queryDto.getPage() != null) {
            page = queryDto.getPage();
        }
        if (queryDto.getLimit() != null) {
            limit = queryDto.getLimit();
        }
        PageHelper.startPage(page, limit, "create_time desc");
        Page<TopicSelectionVo> pageList = (Page<TopicSelectionVo>) topicSelectionMapper.pageList(queryDto);
        PageInfoVo<TopicSelectionVo> pageInfoVo = new PageInfoVo<>();
        pageInfoVo.setPages(pageList.getPages());
        pageInfoVo.setTotal(pageList.getTotal());
        pageInfoVo.setData(pageList.getResult());
        return pageInfoVo;
    }

    @Override
    public List<MajorVo> getMajor() {
        List<MajorVo> res=new ArrayList<>();
        for (MajorEnum value : MajorEnum.values()) {
            MajorVo majorVo = new MajorVo();
            majorVo.setName(value.getName());
            majorVo.setCode(value.getCode());
            res.add(majorVo);
        }
        return res;
    }
}