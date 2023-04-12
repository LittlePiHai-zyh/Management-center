package com.zhangyh.management.admin.service.impl;

import com.zhangyh.management.admin.server.NettyConfig;
import com.zhangyh.management.admin.service.PushMsgService;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author zhangyh
 * @Date 2023/4/12 16:36
 * @desc
 */
@Service
public class PushMsgServiceImpl implements PushMsgService {
    @Override
    public void pushMsgToOne(String userId, String msg) {
        Channel channel = NettyConfig.getChannel(userId);
        if (Objects.isNull(channel)) {
            throw new RuntimeException("未连接socket服务器");
        }

        channel.writeAndFlush(new TextWebSocketFrame(msg));
    }

    @Override
    public void pushMsgToAll(String msg) {
        NettyConfig.getChannelGroup().writeAndFlush(new TextWebSocketFrame(msg));
    }
}
