package com.lsw.management.admin.controller;


import com.lsw.management.admin.annotation.AuthCheck;
import com.lsw.management.admin.annotation.Log;
import com.lsw.management.admin.annotation.PermissionEnum;
import com.lsw.management.admin.model.dto.user.PermissionVo;
import com.lsw.management.admin.model.dto.user.UserLoginDto;
import com.lsw.management.admin.model.dto.user.UserQueryDto;
import com.lsw.management.admin.model.dto.user.UserRegistryDto;
import com.lsw.management.admin.model.vo.PageInfoVo;
import com.lsw.management.admin.model.vo.VerifyImgResult;
import com.lsw.management.admin.model.vo.user.UserAccountVo;
import com.lsw.management.admin.model.vo.user.UserVo;
import com.lsw.management.admin.service.UserAccountService;
import com.lsw.management.admin.service.impl.ImgVerifyCodeServiceImpl;
import com.lsw.management.common.constants.ErrorCode;
import com.lsw.management.common.exception.BusinessException;
import com.lsw.management.common.http.response.ApiResponse;
import com.lsw.management.common.http.response.ResponseHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author zhangyh
 * @Date 2023/4/6 15:03
 * @desc
 */
@Api(tags = "用户账户模块")
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    UserAccountService userService;

    @Resource
    ImgVerifyCodeServiceImpl imgVerifyCodeService;


    @Log
    @ApiOperation(value = "用户信息分页查询", httpMethod = "POST")
    @PostMapping("/pageList")
    @AuthCheck(value = PermissionEnum.USER)
    public ApiResponse<PageInfoVo<UserVo>> pageList(@RequestBody UserQueryDto queryDto) {
        PageInfoVo<UserVo> userVoPageInfoVo = userService.selectInfoPageList(queryDto);
        return ResponseHelper.success(userVoPageInfoVo);
    }

    @Log
    @ApiOperation(value = "基础用户登录", httpMethod = "POST")
    @PostMapping(value = "/baseLogin")
    public ApiResponse<UserAccountVo> baseLogin(@Validated @RequestBody UserLoginDto user, HttpServletRequest request) {
        String verifyCode = user.getVerifyCode();
        String randomKey = user.getRandomKey();
//        imgVerifyCodeService.checkCaptcha(randomKey,verifyCode);
        UserAccountVo userAccountVo = userService.baseLogin(user, request);
        return ResponseHelper.success(userAccountVo);
    }

    @Log
    @ApiOperation(value = "获取验证码", httpMethod = "GET")
    @GetMapping("/imgVerifyCode")
    public ApiResponse<VerifyImgResult> getVerifyCode(@RequestParam(value = "length",required = false) Integer length) {
        if (length == null || length == 0) {
            length = 4;
        }
        return ResponseHelper.success(imgVerifyCodeService.generateVerifyCoe(length));
    }

    @Log
    @ApiOperation(value = "用户注册", httpMethod = "POST")
    @PostMapping("/userRegistry")
    public ApiResponse<Boolean> userRegistry(@RequestBody @Validated UserRegistryDto registryDto) {
        Integer flag = userService.userRegister(registryDto);
        return ResponseHelper.success(flag>0);
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

    @Log
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

    @ApiOperation(value = "获取权限", httpMethod = "GET")
    @GetMapping("/getPermissions")
    public ApiResponse<List<PermissionVo>> getPermissions(){
        return ResponseHelper.success(userService.getPermissions());
    }
}