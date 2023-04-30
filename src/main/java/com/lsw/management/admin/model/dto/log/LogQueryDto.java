package com.lsw.management.admin.model.dto.log;

import com.lsw.management.admin.model.dto.PageRequest;
import lombok.Data;

/**
 * @Author: lsw
 * @desc
 * @date: 2023/4/29  22:21
 */
@Data
public class LogQueryDto extends PageRequest {

    private Integer level;

    private Integer businessType;

    private String operName;

    private String requestMethod;

    private String operUrl;

    private String operIp;

    private Long operTime;

}
