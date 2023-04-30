package com.lsw.management.admin.service;

import com.lsw.management.admin.model.dto.menu.MenuAddDto;
import com.lsw.management.admin.model.dto.menu.MenuQueryDto;
import com.lsw.management.admin.model.vo.PageInfoVo;
import com.lsw.management.admin.model.vo.menu.MenuVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author: lsw
 * @desc
 * @date: 2023/4/29  22:12
 */
public interface MenuService {
    Integer add(MenuAddDto menu, HttpServletRequest request);

    Integer delete(String ids, HttpServletRequest request);

    List<MenuVo> listAll(MenuQueryDto queryDto,String permissions);

    PageInfoVo<MenuVo> pageList(MenuQueryDto queryDto);
}