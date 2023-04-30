package com.lsw.management.admin.model.dto.announcement;

import com.lsw.management.admin.model.dto.PageRequest;
import lombok.Data;

/**
 * @Author: lsw
 * @desc
 * @date: 2023/4/30  14:55
 */
@Data
public class AnnouncementQueryDto extends PageRequest {
    private String title;

    private String content;
}
