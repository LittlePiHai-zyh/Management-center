package com.lsw.management.admin.mapper;

import com.lsw.management.admin.model.dto.announcement.AnnouncementQueryDto;
import com.lsw.management.admin.model.po.announcement.Announcement;
import com.lsw.management.admin.model.vo.announcement.AnnouncementVo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
* @author 17533
* @description 针对表【announcement】的数据库操作Mapper
* @createDate 2023-04-29 21:16:08
* @Entity com.lsw.management.admin.model.po.announcement.Announcement
*/
public interface AnnouncementMapper extends Mapper<Announcement> {

    List<AnnouncementVo> pageList(@Param("queryDto") AnnouncementQueryDto queryDto);

    AnnouncementVo oneAnnouncement();
}




