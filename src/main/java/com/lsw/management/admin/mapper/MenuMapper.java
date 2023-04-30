package com.lsw.management.admin.mapper;

import com.lsw.management.admin.model.dto.menu.MenuQueryDto;
import com.lsw.management.admin.model.po.menu.Menu;
import com.lsw.management.admin.model.vo.menu.MenuVo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


/**
* @author 17533
* @description 针对表【menu】的数据库操作Mapper
* @createDate 2023-04-29 21:11:38
* @Entity com.lsw.management.admin.model.po.menu.Menu
*/
public interface MenuMapper extends Mapper<Menu> {

    List<MenuVo> pageList(@Param("queryDto") MenuQueryDto queryDto);
}
