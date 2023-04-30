package com.lsw.management.admin.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author zhangyh
 * @Date 2023/4/23 8:41
 * @desc
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageInfoVo<T> {

    private Long total;
    private Integer pages;
    private List<T> data;
}