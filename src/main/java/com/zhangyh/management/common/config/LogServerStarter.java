package com.zhangyh.management.common.config;

import com.zhangyh.management.admin.server.NettyServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @author zhangyh
 * @Date 2023/4/12 8:37
 * @desc 容器刷新后执行
 */
@Slf4j
@Component
public class LogServerStarter implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
       log.info("正在启动Netty日志服务-------->>>>>>>>");
        NettyServer nettyServer = new NettyServer();
        nettyServer.startServer();
    }
}
