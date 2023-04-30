package com.lsw.management.admin.service;

import com.lsw.management.admin.model.dto.log.LogQueryDto;
import com.lsw.management.admin.model.po.log.SysLog;
import com.lsw.management.admin.model.vo.PageInfoVo;
import com.lsw.management.admin.model.vo.log.LogVo;

/**
 * @author zhangyh
 * @Date 2023/4/7 16:43
 * @desc
 */
public interface LogService {


    PageInfoVo<LogVo> pageList(LogQueryDto queryDto);

    Integer saveLog(SysLog sysLog);
}
