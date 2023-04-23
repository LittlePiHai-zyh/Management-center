package com.zhangyh.management.common.util.excel;

import java.util.function.Function;

/**
 * @author zhangyh
 * @Date 2023/4/21 11:28
 * @desc
 */
public interface ColumnDynamicSelectDataHandler<T, R> {

    Function<T, R> source();
}
