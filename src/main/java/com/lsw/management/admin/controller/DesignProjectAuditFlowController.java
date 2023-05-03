package com.lsw.management.admin.controller;

import com.lsw.management.admin.model.dto.designProjectAuditFlow.DesignProjectAuditFlowQueryDto;
import com.lsw.management.admin.model.dto.designProjectAuditFlow.DesignProjectAuditFlowsDepartmentAuditAddDto;
import com.lsw.management.admin.model.dto.designProjectAuditFlow.DesignProjectAuditFlowsSchoolAuditAddDto;
import com.lsw.management.admin.model.vo.designProjectAuditFlow.DesignProjectAuditFlowPercent;
import com.lsw.management.admin.model.vo.designProjectAuditFlow.DesignProjectAuditFlowVo;
import com.lsw.management.admin.service.DesignProjectAuditFlowService;
import com.lsw.management.common.http.response.ApiResponse;
import com.lsw.management.common.http.response.ResponseHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author: lsw
 * @desc  毕设出题审计模块
 * @date: 2023/4/29  22:06
 */
@Api(tags = "毕设审计模块")
@RestController
@RequestMapping("/designManage")
public class DesignProjectAuditFlowController {

    @Resource
    DesignProjectAuditFlowService designProjectAuditFlowService;

    @ApiOperation(value = "系负责人审核记录添加", httpMethod = "POST")
    @PostMapping("/departmentAuditAdd")
    public ApiResponse<Integer> departmentAuditAdd(@RequestBody DesignProjectAuditFlowsDepartmentAuditAddDto addDto, HttpServletRequest request){
        return ResponseHelper.success(designProjectAuditFlowService.departmentAuditAdd(addDto,request));
    }

    @ApiOperation(value = "院负责人审核记录添加", httpMethod = "POST")
    @PostMapping("/schoolAuditAdd")
    public ApiResponse<Integer> schoolAuditAdd(@RequestBody DesignProjectAuditFlowsSchoolAuditAddDto addDto,HttpServletRequest request){
        return ResponseHelper.success(designProjectAuditFlowService.schoolAuditAdd(addDto,request));
    }

    @ApiOperation(value = "系负责人需要审核的题目", httpMethod = "POST")
    @PostMapping("/departmentAudit")
    public ApiResponse<List<DesignProjectAuditFlowVo>> departmentAuditQuery(@RequestBody DesignProjectAuditFlowQueryDto queryDto){
        return ResponseHelper.success(designProjectAuditFlowService.departmentAuditQuery(queryDto));
    }

    @ApiOperation(value = "院负责人需要审核的题目", httpMethod = "POST")
    @PostMapping("/schoolAudit")
    public ApiResponse<List<DesignProjectAuditFlowVo>> schoolAuditQuery(@RequestBody DesignProjectAuditFlowQueryDto queryDto){
        return ResponseHelper.success(designProjectAuditFlowService.schoolAuditQuery(queryDto));
    }

    @ApiOperation(value = "审核通过的题目", httpMethod = "POST")
    @PostMapping("/listAll")
    private ApiResponse<List<DesignProjectAuditFlowVo>> listAll(@RequestBody DesignProjectAuditFlowQueryDto queryDto){
        return ResponseHelper.success(designProjectAuditFlowService.listAll(queryDto));
    }

    @ApiOperation(value = "获取审核占比", httpMethod = "GET")
    @GetMapping("/percent")
    private ApiResponse<List<DesignProjectAuditFlowPercent>> designProjectAuditFlowPercent(){
        return ResponseHelper.success(designProjectAuditFlowService.designProjectAuditFlowPercent());
    }

}
