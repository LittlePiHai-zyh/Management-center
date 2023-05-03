package com.lsw.management.admin.service.impl;

import com.lsw.management.admin.mapper.LogMapper;
import com.lsw.management.admin.model.dto.log.LogQueryDto;
import com.lsw.management.admin.model.po.log.SysLog;
import com.lsw.management.admin.model.vo.log.LogVo;
import com.lsw.management.admin.service.LogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
    public List<LogVo> pageList(LogQueryDto queryDto) {
        return logMapper.pageList(queryDto);
    }

    @Override
    public Integer saveLog(SysLog sysLog) {
        return logMapper.insert(sysLog);
    }
}
