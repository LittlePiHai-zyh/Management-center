package com.lsw.management.admin.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lsw.management.admin.mapper.LogMapper;
import com.lsw.management.admin.model.dto.log.LogQueryDto;
import com.lsw.management.admin.model.po.log.SysLog;
import com.lsw.management.admin.model.vo.PageInfoVo;
import com.lsw.management.admin.model.vo.log.LogVo;
import com.lsw.management.admin.service.LogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author zhangyh
 * @Date 2023/4/7 16:44
 * @desc
 */
@Service
public class LogServiceImpl implements LogService {

    @Resource
    LogMapper logMapper;

    @Override
    public PageInfoVo<LogVo> pageList(LogQueryDto queryDto) {
        int page = 1;
        int limit = 10;
        if (queryDto.getPage() != null) {
            page = queryDto.getPage();
        }
        if (queryDto.getLimit() != null) {
            limit = queryDto.getLimit();
        }
        PageHelper.startPage(page, limit, "create_time desc");
        Page<LogVo> pageList = (Page<LogVo>) logMapper.pageList(queryDto);
        PageInfoVo<LogVo> pageInfoVo = new PageInfoVo<>();
        pageInfoVo.setPages(pageList.getPages());
        pageInfoVo.setTotal(pageList.getTotal());
        pageInfoVo.setData(pageList.getResult());
        return pageInfoVo;
    }

    @Override
    public Integer saveLog(SysLog sysLog) {
        return logMapper.insert(sysLog);
    }
}
