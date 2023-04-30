package com.lsw.management.admin.mapper;

import com.lsw.management.admin.model.dto.log.LogQueryDto;
import com.lsw.management.admin.model.po.log.SysLog;
import com.lsw.management.admin.model.vo.log.LogVo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author zhangyh
 * @Date 2023/4/7 16:45
 * @desc
 */
public interface LogMapper extends Mapper<SysLog> {
    List<LogVo> pageList(@Param("queryDto") LogQueryDto queryDto);
}
