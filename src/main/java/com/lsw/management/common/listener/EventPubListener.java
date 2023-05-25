package com.lsw.management.common.listener;

import com.lsw.management.admin.model.po.log.SysLog;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author lsw
 * @Date 2023/4/7 16:41
 * @desc
 */
@Component
public class EventPubListener {
    @Resource
    private ApplicationContext applicationContext;

    // 事件发布方法
    public void pushListener(SysLog sysLogEvent) {
        applicationContext.publishEvent(sysLogEvent);
    }
}
