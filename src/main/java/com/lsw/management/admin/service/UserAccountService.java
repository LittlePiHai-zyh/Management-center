package com.lsw.management.admin.service;

import com.lsw.management.admin.model.dto.user.PermissionVo;
import com.lsw.management.admin.model.dto.user.UserLoginDto;
import com.lsw.management.admin.model.dto.user.UserQueryDto;
import com.lsw.management.admin.model.dto.user.UserRegistryDto;
import com.lsw.management.admin.model.po.user.UserAccount;
import com.lsw.management.admin.model.vo.user.UserAccountVo;
import com.lsw.management.admin.model.vo.user.UserVo;
import com.lsw.management.admin.model.vo.PageInfoVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author zhangyh
 * @Date 2023/4/6 15:25
 * @desc
 */
public interface UserAccountService {

   /**
    * 获取登录的用户
    * @param request
    * @return
    */
   UserAccount getCurrentUser(HttpServletRequest request);

   /**
    * 登录
    * @param user
    * @param request
    * @return
    */
   UserAccountVo baseLogin(UserLoginDto user, HttpServletRequest request);

   /**
    * 注册
    * @param registryDto
    */
   Integer userRegister(UserRegistryDto registryDto);

   /**
    * 分页查询
    * @param queryDto
    * @return
    */
   PageInfoVo<UserVo> selectInfoPageList(UserQueryDto queryDto);

   Integer removeById(String  ids);

   UserVo getById(Integer id);

    List<PermissionVo> getPermissions();
}
