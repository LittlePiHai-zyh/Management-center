package com.zhangyh.management.admin.controller;


import com.zhangyh.management.admin.annotation.AuthCheck;
import com.zhangyh.management.admin.annotation.Log;
import com.zhangyh.management.admin.annotation.PermissionEnum;
import com.zhangyh.management.admin.model.dto.UserLoginDto;
import com.zhangyh.management.admin.model.dto.UserQueryDto;
import com.zhangyh.management.admin.model.dto.UserRegistryDto;
import com.zhangyh.management.admin.model.vo.PageInfoVo;
import com.zhangyh.management.admin.model.vo.UserAccountVo;
import com.zhangyh.management.admin.model.vo.UserVo;
import com.zhangyh.management.admin.model.vo.VerifyImgResult;
import com.zhangyh.management.admin.service.UserAccountService;
import com.zhangyh.management.admin.service.impl.ImgVerifyCodeServiceImpl;
import com.zhangyh.management.common.constants.ErrorCode;
import com.zhangyh.management.common.exception.BusinessException;
import com.zhangyh.management.common.http.response.ApiResponse;
import com.zhangyh.management.common.http.response.ResponseHelper;
import com.zhangyh.management.common.util.I18nUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author zhangyh
 * @Date 2023/4/6 15:03
 * @desc
 */
@Api("用户账户模块")
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    UserAccountService userService;

    @Resource
    ImgVerifyCodeServiceImpl imgVerifyCodeService;


    @GetMapping("/hello")
    public String hello() {
        return I18nUtils.getMessage("password");
    }

    @Log
    @ApiOperation(value = "用户信息分页查询", httpMethod = "POST")
    @PostMapping("/pageList")
    @AuthCheck(value = PermissionEnum.USER)
    public ApiResponse<PageInfoVo<UserVo>> pageList(@RequestBody UserQueryDto queryDto) {

        PageInfoVo<UserVo> userVoPageInfoVo = userService.selectInfoPageList(queryDto);

        return ResponseHelper.success(userVoPageInfoVo);
    }

    @ApiOperation(value = "基础用户登录", httpMethod = "POST")
    @PostMapping(value = "/baseLogin")
    public ApiResponse<UserAccountVo> baseLogin(@Validated @RequestBody UserLoginDto user, HttpServletRequest request) {
        String verifyCode = user.getVerifyCode();
        String randomKey = user.getRandomKey();
//        imgVerifyCodeService.checkCaptcha(randomKey,verifyCode);
        UserAccountVo userAccountVo = userService.baseLogin(user, request);
        return ResponseHelper.success(userAccountVo);
    }

    @ApiOperation(value = "获取验证码", httpMethod = "GET")
    @GetMapping("/imgVerifyCode")
    public ApiResponse<VerifyImgResult> getVerifyCode(@RequestParam("length") Integer length) {
        if (length == null || length == 0) {
            length = 4;
        }
        return ResponseHelper.success(imgVerifyCodeService.generateVerifyCoe(length));
    }

    @ApiOperation(value = "用户注册", httpMethod = "POST")
    @PostMapping("/userRegistry")
    public ApiResponse<UserAccountVo> userRegistry(@RequestBody @Validated UserRegistryDto registryDto) {
        userService.userRegister(registryDto);
        return ResponseHelper.success();
    }

    @Log
    @AuthCheck(value = PermissionEnum.ADMIN)
    @ApiOperation(value = "账号删除", httpMethod = "DELETE")
    @DeleteMapping("/delete")
    public ApiResponse<Integer> deleteUser(@RequestParam String ids) {
        if (StringUtils.isBlank(ids)) {
            throw new BusinessException(ErrorCode.MISSING_PARAMS);
        }
        Integer userAccountDeleteFlag = userService.removeById(ids);
        return ResponseHelper.success(userAccountDeleteFlag);
    }

    @AuthCheck(value = PermissionEnum.USER)
    @ApiOperation(value = "查询用户账号", httpMethod = "GET")
    @GetMapping("/getUserAccount")
    public ApiResponse<UserVo> getUser(@RequestParam Integer id) {
        if (id == null) {
            throw new BusinessException(ErrorCode.MISSING_PARAMS);
        }
        UserVo userAccountVo = userService.getById(id);
        return ResponseHelper.success(userAccountVo);
    }
}
