package com.lsw.management.admin.model.vo.log;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Author: lsw
 * @desc
 * @date: 2023/4/29  22:18
 */
@Data
public class LogVo {
    private Integer id;

    private Integer level;

    private Integer businessType;

    private String requestMethod;

    private String operName;

    private String operUrl;

    private String operIp;

    private Long operTime;

    private String exceptionDetail;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}
