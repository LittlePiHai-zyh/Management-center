package com.lsw.management.admin.controller;

import com.lsw.management.admin.model.dto.menu.MenuAddDto;
import com.lsw.management.admin.model.dto.menu.MenuQueryDto;
import com.lsw.management.admin.model.dto.menu.MenuUpdateDto;
import com.lsw.management.admin.model.vo.PageInfoVo;
import com.lsw.management.admin.model.vo.menu.MenuVo;
import com.lsw.management.admin.service.MenuService;
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
 * @desc 系统菜单
 * @date: 2023/4/29  22:07
 */
@Api(tags = "系统菜单模块")
@RestController
@RequestMapping("/menuManage")
public class MenuController {

    @Resource
    MenuService menuService;

    @ApiOperation(value = "菜单添加", httpMethod = "POST")
    @PostMapping("/add")
    public ApiResponse<Integer> add(@RequestBody @Validated MenuAddDto menu, HttpServletRequest request) {
        return ResponseHelper.success(menuService.add(menu, request));
    }

    @ApiOperation(value = "菜单删除", httpMethod = "GET")
    @GetMapping("/delete")
    public ApiResponse<Integer> delete(@RequestParam String ids, HttpServletRequest request) {
        return ResponseHelper.success(menuService.delete(ids, request));
    }

    @ApiOperation(value = "根据权限查菜单", httpMethod = "POST")
    @PostMapping("/listAll")
    public ApiResponse<List<MenuVo>> listAll(@RequestBody MenuQueryDto queryDto,HttpServletRequest request) {
        return ResponseHelper.success(menuService.listAll(queryDto,request));
    }

    @ApiOperation(value = "菜单分页查询", httpMethod = "POST")
    @PostMapping("/pageList")
    public ApiResponse<PageInfoVo<MenuVo>> pageList(@RequestBody MenuQueryDto queryDto) {
        return ResponseHelper.success(menuService.pageList(queryDto));
    }

    @ApiOperation(value = "菜单更新", httpMethod = "POST")
    @PostMapping("/update")
    public ApiResponse<Integer> update(@RequestBody MenuUpdateDto updateDto) {
        return ResponseHelper.success(menuService.update(updateDto));
    }

}
