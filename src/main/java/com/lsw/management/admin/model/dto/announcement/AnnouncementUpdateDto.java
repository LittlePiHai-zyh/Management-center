package com.lsw.management.admin.model.dto.announcement;

import lombok.Data;

import java.util.Date;

/**
 * @Author: lsw
 * @desc
 * @date: 2023/4/30  14:55
 */
@Data
public class AnnouncementUpdateDto {

    private Integer id;

    private String title;

    private String content;

    private Integer accountId;

    private Date updateTime;

}
