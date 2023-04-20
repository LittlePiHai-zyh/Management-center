package com.zhangyh.management.admin.server.http;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.handler.stream.ChunkedFile;

import java.io.File;

/**
 * @author zhangyh
 * @Date 2023/4/13 14:18
 * @desc
 */
public class HttpServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    private final String rootDir;

    public HttpServerHandler(String rootDir) {
        this.rootDir = rootDir;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request)
            throws Exception {

        HttpResponseStatus status = null;
        Object message = null;
        message = new ChunkedFile(new File("pub.key"));
        status = HttpResponseStatus.OK;
        HttpResponse response = new DefaultHttpResponse(HttpVersion.HTTP_1_1, status);
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain; charset=UTF-8");
        response.headers().set(HttpHeaderNames.TRANSFER_ENCODING, HttpHeaderValues.CHUNKED);
        ctx.write(response);
        ctx.write(message);
      ctx.writeAndFlush(LastHttpContent.EMPTY_LAST_CONTENT);
    }
}
