package com.lsw.management.admin.mapper;

import com.lsw.management.admin.model.dto.topic.selection.TopicSelectionQueryDto;
import com.lsw.management.admin.model.po.topic.selection.TopicSelection;
import com.lsw.management.admin.model.vo.topic.selection.TopicSelectionVo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
* @author 17533
* @description 针对表【topic_selection】的数据库操作Mapper
* @createDate 2023-04-29 21:19:47
* @Entity com.lsw.management.admin.model.po.topic.selection.TopicSelection
*/
public interface TopicSelectionMapper extends Mapper<TopicSelection> {

    List<TopicSelectionVo> pageList(@Param("queryDto") TopicSelectionQueryDto queryDto);

    List<TopicSelectionVo> beApproved(@Param("queryDto") TopicSelectionQueryDto queryDto);
}




