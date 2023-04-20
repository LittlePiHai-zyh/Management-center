package com.zhangyh.management.admin.server.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author zhangyh
 * @Date 2023/4/13 14:18
 * @desc
 */
public class HttpServer {
    private static final int PORT = 8080;
    private static final String ROOT_DIR = "path/to/your/log/directory";

    public static void main(String[] args) throws Exception {
        // 创建一个EventLoopGroup用于线程管理
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new HttpServerInitializer(ROOT_DIR));

            Channel ch = b.bind(PORT).sync().channel();
            System.out.println("Server started at http://localhost:" + PORT);
            ch.closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
