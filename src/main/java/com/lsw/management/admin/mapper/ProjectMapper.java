package com.lsw.management.admin.mapper;

import com.lsw.management.admin.model.dto.project.ProjectQueryDto;
import com.lsw.management.admin.model.po.project.Project;
import com.lsw.management.admin.model.vo.project.ProjectVo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

/**
* @author 17533
* @description 针对表【project】的数据库操作Mapper
* @createDate 2023-04-29 21:18:48
* @Entity com.lsw.management.admin.model.po.project.Project
*/
public interface ProjectMapper extends BaseMapper<Project> {

    List<ProjectVo> listAll(@Param("queryDto") ProjectQueryDto queryDto);
}




