package com.lsw.management.admin.controller;

import com.lsw.management.admin.model.dto.project.*;
import com.lsw.management.admin.model.vo.project.ProjectInfoVo;
import com.lsw.management.admin.model.vo.project.ProjectNoAnswerVo;
import com.lsw.management.admin.model.vo.project.ProjectStatusVo;
import com.lsw.management.admin.model.vo.project.ProjectVo;
import com.lsw.management.admin.service.ProjectService;
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
 * @desc 毕设进度跟踪与学生选题模块
 * @date: 2023/4/29  22:07
 */
@Api(tags = "学生选题与毕设进度跟踪模块")
@RestController
@RequestMapping("/projectManage")
public class ProjectController {

    @Resource
    ProjectService projectService;

    @ApiOperation(value = "学生选题记录添加", httpMethod = "POST")
    @PostMapping("/studentTopicSelection")
    private ApiResponse<Integer> studentTopicSelection(@RequestBody ProjectAddDto addDto, HttpServletRequest request){
        return ResponseHelper.success(projectService.studentTopicSelection(addDto,request));
    }

    @ApiOperation(value = "学生选题取消", httpMethod = "POST")
    @PostMapping("/studentTopicSelectionCancel")
    private ApiResponse<Integer> studentTopicSelectionCancel(@RequestBody ProjectUpdateDto updateDto){
        return ResponseHelper.success(projectService.studentTopicSelectionCancel(updateDto));
    }

    @ApiOperation(value = "学生选题进度", httpMethod = "POST")
    @PostMapping("/studentTopicSelectionProcess")
    private ApiResponse<List<ProjectVo>> studentTopicSelectionProcess(@RequestBody ProjectQueryDto addDto){
        return ResponseHelper.success(projectService.studentTopicSelectionProcess(addDto));
    }

    @ApiOperation(value = "学生选题进度更新", httpMethod = "POST")
    @PostMapping("/studentTopicSelectionUpdate")
    private ApiResponse<Integer> studentTopicSelectionUpdate(@RequestBody ProjectUpdateDto updateDto){
        return ResponseHelper.success(projectService.studentTopicSelectionUpdate(updateDto));
    }

    @ApiOperation(value = "学生免答申请", httpMethod = "POST")
    @PostMapping("/noAnswer")
    private ApiResponse<Integer> noAnswer(@RequestParam String id){
        return ResponseHelper.success(projectService.noAnswer(id));
    }

    @ApiOperation(value = "申请免答的学生", httpMethod = "POST")
    @PostMapping("/noAnswerListAll")
    private ApiResponse<List<ProjectNoAnswerVo>> noAnswerListAll(@RequestBody ProjectNoAnswerQueryDto queryDto){
        return ResponseHelper.success(projectService.noAnswerListAll(queryDto));
    }

    @ApiOperation(value = "学生免答申请审核", httpMethod = "POST")
    @PostMapping("/noAnswerAdd")
    private ApiResponse<Integer> noAnswerAdd(@RequestBody ProjectNoAnswerAddDto addDto){
        return ResponseHelper.success(projectService.noAnswerAdd(addDto));
    }

    @ApiOperation(value = "学生完成状态", httpMethod = "GET")
    @GetMapping("/projectState")
    private ApiResponse<List<ProjectStatusVo>> projectState(){
        return ResponseHelper.success(projectService.projectState());
    }

    @ApiOperation(value = "获取当前登录用户的毕设", httpMethod = "GET")
    @GetMapping("/getCurrentUserProject")
    private ApiResponse<ProjectInfoVo> getCurrentUserProject(HttpServletRequest request){
        return  ResponseHelper.success(projectService.getCurrentUserProject(request));
    }
}
