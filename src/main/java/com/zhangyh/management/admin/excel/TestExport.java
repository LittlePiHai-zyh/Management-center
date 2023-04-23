package com.zhangyh.management.admin.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author zhangyh
 * @Date 2023/4/21 11:14
 * @desc
 */
@Data
public class TestExport {
    @ExcelProperty("key")
    private String key;
    @ExcelProperty("value")
    private String value;
}

