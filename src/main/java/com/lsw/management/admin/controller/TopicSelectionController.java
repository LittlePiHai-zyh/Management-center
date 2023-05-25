package com.lsw.management.admin.controller;

import com.lsw.management.admin.model.dto.topic.selection.TopicSelectionAddDto;
import com.lsw.management.admin.model.dto.topic.selection.TopicSelectionQueryDto;
import com.lsw.management.admin.model.dto.topic.selection.TopicSelectionUpdateDto;
import com.lsw.management.admin.model.vo.PageInfoVo;
import com.lsw.management.admin.model.vo.topic.selection.MajorVo;
import com.lsw.management.admin.model.vo.topic.selection.StudentTypeVo;
import com.lsw.management.admin.model.vo.topic.selection.TopicSelectionVo;
import com.lsw.management.admin.service.TopicSelectionService;
import com.lsw.management.common.http.response.ApiResponse;
import com.lsw.management.common.http.response.ResponseHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author: lsw
 * @desc  毕设出题管理模块
 * @date: 2023/4/29  22:08
 */
@Api(tags = "毕设出题管理模块")
@RestController
@RequestMapping("/topicSelectionManage")
public class TopicSelectionController {

    @Resource
    TopicSelectionService topicSelectionService;

    @ApiOperation(value = "毕设题目新增", httpMethod = "POST")
    @PostMapping("/add")
    public ApiResponse<Integer> add(@RequestBody @Validated TopicSelectionAddDto addDto, HttpServletRequest request) {
        return ResponseHelper.success(topicSelectionService.add(addDto, request));
    }

    @ApiOperation(value = "毕设题目删除", httpMethod = "GET")
    @GetMapping("/delete")
    public ApiResponse<Integer> delete(@RequestParam String ids) {
        return ResponseHelper.success(topicSelectionService.delete(ids));
    }

    @ApiOperation(value = "获取专业", httpMethod = "GET")
    @GetMapping("/getMajor")
    public ApiResponse<List<MajorVo>> getMajor() {
        return ResponseHelper.success(topicSelectionService.getMajor());
    }

    @ApiOperation(value = "获取学生类型", httpMethod = "GET")
    @GetMapping("/getStudentType")
    public ApiResponse<List<StudentTypeVo>> getStudentType() {
        return ResponseHelper.success(topicSelectionService.getStudentType());
    }

    @ApiOperation(value = "毕设题目更新", httpMethod = "POST")
    @PostMapping("/update")
    public ApiResponse<Integer> update(@RequestBody TopicSelectionUpdateDto updateDto) {
        return ResponseHelper.success(topicSelectionService.update(updateDto));
    }

    @ApiOperation(value = "查询全部毕设题目", httpMethod = "POST")
    @PostMapping("/listAll")
    public ApiResponse<List<TopicSelectionVo>> listAll(@RequestBody(required = false) TopicSelectionQueryDto queryDto) {
        return ResponseHelper.success(topicSelectionService.listAll(queryDto));
    }

    @ApiOperation(value = "可选毕设题目查询", httpMethod = "POST")
    @PostMapping("/beApproved")
    public ApiResponse<List<TopicSelectionVo>> beApproved(@RequestBody(required = false) TopicSelectionQueryDto queryDto) {
        return ResponseHelper.success(topicSelectionService.beApproved(queryDto));
    }

    @ApiOperation(value = "审核通过的毕设题目", httpMethod = "POST")
    @PostMapping("/pageList")
    public ApiResponse<PageInfoVo<TopicSelectionVo>> pageList(@RequestBody(required = false) TopicSelectionQueryDto queryDto) {
        return ResponseHelper.success(topicSelectionService.pageList(queryDto));
    }

}
