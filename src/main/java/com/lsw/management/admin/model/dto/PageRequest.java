package com.lsw.management.admin.model.dto;

import lombok.Data;

/**
 * @author zhangyh
 * @Date 2023/4/7 13:58
 * @desc 分页请求
 */
@Data
public class PageRequest {
    /**
     * 当前页号
     */
    private final Integer page = 1;

    /**
     * 页面大小
     */
    private final Integer limit = 10;
}
