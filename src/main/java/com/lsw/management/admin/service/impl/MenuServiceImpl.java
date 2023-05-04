package com.lsw.management.admin.service.impl;

import com.lsw.management.admin.mapper.MenuMapper;
import com.lsw.management.admin.model.dto.menu.MenuAddDto;
import com.lsw.management.admin.model.dto.menu.MenuQueryDto;
import com.lsw.management.admin.model.po.menu.Menu;
import com.lsw.management.admin.model.po.user.UserAccount;
import com.lsw.management.admin.model.vo.PageInfoVo;
import com.lsw.management.admin.model.vo.menu.MenuVo;
import com.lsw.management.admin.service.MenuService;
import com.lsw.management.admin.service.UserAccountService;
import com.lsw.management.common.constants.ErrorCode;
import com.lsw.management.common.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @Author: lsw
 * @desc
 * @date: 2023/4/29  22:12
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Resource
    MenuMapper menuMapper;

    @Resource
    UserAccountService userAccountService;

    @Override
    public Integer add(MenuAddDto menu, HttpServletRequest request) {
        Menu addMenu = new Menu();
        BeanUtils.copyProperties(menu, addMenu);
        addMenu.setCreateTime(new Date());
        addMenu.setDeleted((byte)0);
        addMenu.setHidden(0);
        //查询用户
        UserAccount currentUser = userAccountService.getCurrentUser(request);
        addMenu.setCreateBy(currentUser.getUsername());
        return menuMapper.insert(addMenu);
    }

    @Override
    public Integer delete(String ids, HttpServletRequest request) {
        if (StringUtils.isBlank(ids)) {
            throw new BusinessException(ErrorCode.INVALID_PARAMS);
        }
        String[] idArray = ids.split(",");
        AtomicInteger atomicInteger = new AtomicInteger();
        Arrays.stream(idArray).map(Integer::parseInt).forEach(id -> {
            Menu menu = Menu.builder()
                    .menuId(id)
                    .deleted((byte) 1)
                    .updateTime(new Date())
                    .build();
            int i = menuMapper.updateByPrimaryKeySelective(menu);
            atomicInteger.getAndAdd(i);
        });
        return atomicInteger.get();
    }

    @Override
    public List<MenuVo> listAll(MenuQueryDto queryDto,String permissions) {
        Example example = new Example(Menu.class);
        example.createCriteria()
                .andEqualTo(Menu.HIDDEN, queryDto.getHidden())
                .andLike(Menu.NAME, queryDto.getName())
                .andEqualTo(Menu.PERMISSIONS, queryDto.getPermissions())
                .andEqualTo(Menu.PID, queryDto.getPid())
                .andLike(Menu.TITTLE,queryDto.getTitle());
        List<Menu> menus = menuMapper.selectByExample(example);
        if(StringUtils.isBlank(permissions)){
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        //权限校验过滤菜单
        String[] roles = permissions.split(",");
        List<Menu> permissionsMenu = menus.stream().filter(menu -> {
            String[] menuPermissions = menu.getPermissions().split(",");
            for (String role : menuPermissions) {
                if (Arrays.asList(roles).contains(role)) {
                    return true;
                }
            }
            return false;
        }).collect(Collectors.toList());
        return permissionsMenu.stream().map(MenuVo::new).collect(Collectors.toList());
    }

    @Override
    public PageInfoVo<MenuVo> pageList(MenuQueryDto queryDto) {
//        int page = 1;
//        int limit = 10;
//        if (queryDto.getPage() != null) {
//            page = queryDto.getPage();
//        }
//        if (queryDto.getLimit() != null) {
//            limit = queryDto.getLimit();
//        }
//        PageHelper.startPage(page, limit, "create_time asc");
//        Page<MenuVo> pageList = (Page<MenuVo>)menuMapper.pageList(queryDto);
        List<MenuVo> menuVos = menuMapper.pageList(queryDto);
        AtomicInteger atomicInteger = new AtomicInteger();
        menuVos.forEach(menu->{
            menu.setKey(atomicInteger.getAndIncrement());
        });
        PageInfoVo<MenuVo> pageInfoVo = new PageInfoVo<>();
        pageInfoVo.setData(menuVos);
        return pageInfoVo;
    }
}
