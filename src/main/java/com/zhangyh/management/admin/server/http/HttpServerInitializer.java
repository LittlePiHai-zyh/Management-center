package com.zhangyh.management.admin.server.http;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @author zhangyh
 * @Date 2023/4/13 14:18
 * @desc
 */
public class HttpServerInitializer extends ChannelInitializer<SocketChannel> {

    private final String rootDir;

    public HttpServerInitializer(String rootDir) {
        this.rootDir = rootDir;
    }

    @Override
    public void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline p = ch.pipeline();

        p.addLast(new HttpServerCodec());
        p.addLast(new HttpServerHandler(rootDir));
    }

}
