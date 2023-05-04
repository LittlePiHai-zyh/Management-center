package com.lsw.management.admin.mapper;

import com.lsw.management.admin.model.dto.project.ProjectNoAnswerQueryDto;
import com.lsw.management.admin.model.dto.project.ProjectQueryDto;
import com.lsw.management.admin.model.po.project.Project;
import com.lsw.management.admin.model.po.project.ProjectInfo;
import com.lsw.management.admin.model.vo.project.ProjectNoAnswerVo;
import com.lsw.management.admin.model.vo.project.ProjectStatusVo;
import com.lsw.management.admin.model.vo.project.ProjectVo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
* @author 17533
* @description 针对表【project】的数据库操作Mapper
* @createDate 2023-04-29 21:18:48
* @Entity com.lsw.management.admin.model.po.project.Project
*/
public interface ProjectMapper extends Mapper<Project> {

    List<ProjectVo> listAll(@Param("queryDto") ProjectQueryDto queryDto);

    List<ProjectNoAnswerVo> noAnswerListAll(@Param("queryDto") ProjectNoAnswerQueryDto queryDto);

    List<ProjectStatusVo> projectState();

    ProjectInfo getCurrentUserProject(@Param("id") Integer id);
}




