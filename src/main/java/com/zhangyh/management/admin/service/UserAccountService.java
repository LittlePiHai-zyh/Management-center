package com.zhangyh.management.admin.service;

import com.zhangyh.management.admin.model.dto.UserLoginDto;
import com.zhangyh.management.admin.model.dto.UserQueryDto;
import com.zhangyh.management.admin.model.dto.UserRegistryDto;
import com.zhangyh.management.admin.model.po.UserAccount;
import com.zhangyh.management.admin.model.vo.PageInfoVo;
import com.zhangyh.management.admin.model.vo.UserAccountVo;
import com.zhangyh.management.admin.model.vo.UserVo;

import javax.servlet.http.HttpServletRequest;

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
   UserAccountVo baseLogin(UserLoginDto user,HttpServletRequest request);

   /**
    * 注册
    * @param registryDto
    */
   void userRegister(UserRegistryDto registryDto);

   /**
    * 分页查询
    * @param queryDto
    * @return
    */
   PageInfoVo<UserVo> selectInfoPageList(UserQueryDto queryDto);

   Integer removeById(String  ids);

   UserVo getById(Integer id);
}
