package com.lsw.management.admin.controller;

import com.lsw.management.admin.model.dto.score.ScoreAddDto;
import com.lsw.management.admin.model.dto.score.ScoreQueryDto;
import com.lsw.management.admin.model.po.scores.ScoreVo;
import com.lsw.management.admin.service.ScoreService;
import com.lsw.management.common.http.response.ApiResponse;
import com.lsw.management.common.http.response.ResponseHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author: lsw
 * @desc
 * @date: 2023/5/4  11:56
 */
@Api(tags = "毕设打分模块")
@RestController
@RequestMapping("/scoreManage")
public class ScoreController {

    @Resource
    ScoreService scoreService;

    @ApiOperation(value = "打分", httpMethod = "POST")
    @PostMapping("/add")
    public ApiResponse<Integer> add(@RequestBody @Validated ScoreAddDto menu, HttpServletRequest request) {
        return ResponseHelper.success(scoreService.add(menu, request));
    }

    public ApiResponse<List<ScoreVo>> listAll(@RequestBody ScoreQueryDto queryDto){
        return ResponseHelper.success(scoreService.listAll(queryDto));
    }

}
