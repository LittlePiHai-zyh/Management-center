package com.lsw.management.admin.model.dto.menu;

import com.lsw.management.admin.model.dto.PageRequest;
import lombok.Data;

/**
 * @Author: lsw
 * @desc
 * @date: 2023/4/30  8:23
 */
@Data
public class MenuQueryDto extends PageRequest {
    private Integer pid;

    private String title;

    private String name;

    private Integer hidden;

    private String permissions;
}
