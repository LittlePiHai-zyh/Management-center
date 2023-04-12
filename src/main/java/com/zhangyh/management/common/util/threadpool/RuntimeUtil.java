package com.zhangyh.management.common.util.threadpool;

/**
 * @author zhangyh
 * @Date 2023/4/12 9:03
 * @desc
 */
public class RuntimeUtil {
    /**
     * 获取CPU的核心数
     *
     * @return cpu的核心数
     */
    public static int cpus() {
        return Runtime.getRuntime().availableProcessors();
    }
}
