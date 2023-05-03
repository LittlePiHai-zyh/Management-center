package com.lsw.management.admin.model.vo.announcement;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lsw.management.admin.model.po.announcement.Announcement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import static com.lsw.management.common.constants.GlobalConstants.TIME_PATTERN;

/**
 * @Author: lsw
 * @desc
 * @date: 2023/4/30  14:55
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AnnouncementVo {

    private Integer id;

    private String title;

    private String content;

    private String username;

    @JsonFormat(pattern =TIME_PATTERN, timezone = "GMT+8")
    @DateTimeFormat(pattern = TIME_PATTERN)
    private Date createTime;

    @JsonIgnore
    transient final BeanCopier beanCopier = BeanCopier.create(Announcement.class, AnnouncementVo.class, false);

    public AnnouncementVo(Announcement announcement) {
        beanCopier.copy(announcement, this, null);
    }
}
