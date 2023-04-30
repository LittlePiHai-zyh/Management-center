package com.lsw.management.common.util.excel.annotion;

import java.lang.annotation.*;

/**
 * @author zhangyh
 * @Date 2023/4/21 11:27
 * @desc
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Inherited
public @interface ExcelSelect {

    /**
     * 级联父id序号
     */
    int pid() default -1;

    /**
     * 设置下拉框的起始行，默认为第二行
     */
    int firstRow() default 1;

    /**
     * 设置下拉框的结束行，默认为最后一行
     */
    int lastRow() default 0x10000;
}
