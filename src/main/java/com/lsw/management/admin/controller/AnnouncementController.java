package com.lsw.management.admin.controller;

import com.lsw.management.admin.model.dto.announcement.AnnouncementAddDto;
import com.lsw.management.admin.model.dto.announcement.AnnouncementQueryDto;
import com.lsw.management.admin.model.dto.announcement.AnnouncementUpdateDto;
import com.lsw.management.admin.model.vo.PageInfoVo;
import com.lsw.management.admin.model.vo.announcement.AnnouncementVo;
import com.lsw.management.admin.service.AnnouncementService;
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
 * @desc 系统公告
 * @date: 2023/4/29  22:06
 */
@Api(tags = "系统公告")
@RestController
@RequestMapping("/announcementManage")
public class AnnouncementController {

    @Resource
    AnnouncementService announcementService;

    @ApiOperation(value = "公告新增", httpMethod = "POST")
    @PostMapping("/add")
    public ApiResponse<Integer> add(@RequestBody @Validated AnnouncementAddDto addDto, HttpServletRequest request) {
        return ResponseHelper.success(announcementService.add(addDto, request));
    }

    @ApiOperation(value = "公告删除", httpMethod = "GET")
    @GetMapping("/delete")
    public ApiResponse<Integer> delete(@RequestParam String ids) {
        return ResponseHelper.success(announcementService.delete(ids));
    }

    @ApiOperation(value = "公告更新", httpMethod = "POST")
    @PostMapping("/update")
    public ApiResponse<Integer> update(@RequestParam AnnouncementUpdateDto updateDto) {
        return ResponseHelper.success(announcementService.update(updateDto));
    }

    @ApiOperation(value = "查询全部公告", httpMethod = "POST")
    @PostMapping("/listAll")
    public ApiResponse<List<AnnouncementVo>> listAll(@RequestBody(required = false) AnnouncementQueryDto queryDto, String permissions) {
        return ResponseHelper.success(announcementService.listAll(queryDto, permissions));
    }

    @ApiOperation(value = "公告分页查询", httpMethod = "POST")
    @PostMapping("/pageList")
    public ApiResponse<PageInfoVo<AnnouncementVo>> pageList(@RequestBody(required = false) AnnouncementQueryDto queryDto) {
        return ResponseHelper.success(announcementService.pageList(queryDto));
    }

}
