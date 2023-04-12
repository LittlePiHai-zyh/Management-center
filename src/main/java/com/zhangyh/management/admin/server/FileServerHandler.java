//package com.zhangyh.management.admin.server;
//
//import io.netty.buffer.ByteBuf;
//import io.netty.buffer.Unpooled;
//import io.netty.channel.ChannelFutureListener;
//import io.netty.channel.ChannelHandlerContext;
//import io.netty.channel.SimpleChannelInboundHandler;
//import io.netty.handler.codec.http.*;
//import io.netty.util.CharsetUtil;
//import lombok.extern.slf4j.Slf4j;
//
///**
// * @author zhangyh
// * @Date 2023/4/12 10:49
// * @desc
// */
//@Slf4j
//class FileServerHandler extends SimpleChannelInboundHandler<HttpMessage> {
//
//    @Override
//    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        super.channelActive(ctx);
//
//    }
//
//    @Override
//    protected void channelRead0(ChannelHandlerContext ctx, HttpMessage msg) throws Exception {
//        // 读取Socket数据
//        // ...
//        log.info("收到前端的请求  {}",msg.protocolVersion());
//        // 回复信息给浏览器
//        ByteBuf byteBuf = Unpooled.copiedBuffer("hello 我是自定义的Http服务器, 我正在服务.....", CharsetUtil.UTF_8);
//        // 构造一个http响应体，即HttpResponse
//        DefaultFullHttpResponse defaultFullHttpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, byteBuf);
//        // 设置响应头信息
//        defaultFullHttpResponse.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain;charset=utf8");
//        defaultFullHttpResponse.headers().set(HttpHeaderNames.CONTENT_LENGTH, byteBuf.readableBytes());
//        // 将响应体写入到通道中
//        ctx.writeAndFlush(defaultFullHttpResponse).addListener(ChannelFutureListener.CLOSE);
//    }
//}
