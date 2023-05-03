package com.lsw.management.admin.controller;

import com.lsw.management.admin.model.dto.log.LogQueryDto;
import com.lsw.management.admin.model.vo.log.LogVo;
import com.lsw.management.admin.service.LogService;
import com.lsw.management.common.http.response.ApiResponse;
import com.lsw.management.common.http.response.ResponseHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: lsw
 * @desc  系统日志
 * @date: 2023/4/29  22:07
 */
@Api(tags = "系统日志模块")
@RestController
@RequestMapping("/logManage")
public class LogController {

    @Resource
    LogService logService;

    @ApiOperation(value = "系统日志分页查询", httpMethod = "POST")
    @PostMapping("/pageList")
    public ApiResponse<List<LogVo>> pageList(@RequestBody LogQueryDto queryDto){
        return ResponseHelper.success(logService.pageList(queryDto));
    }
}
