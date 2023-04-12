//package com.zhangyh.management.admin.server;
//
//import io.netty.channel.*;
//import io.netty.handler.codec.http.*;
//import io.netty.handler.stream.ChunkedFile;
//import lombok.extern.slf4j.Slf4j;
//
//import java.io.IOException;
//import java.io.RandomAccessFile;
//
//import static io.netty.handler.codec.http.HttpHeaderNames.*;
//
///**
// * @author zhangyh
// * @Date 2023/4/12 9:10
// * @desc
// */
//@Slf4j
//public class LocalLogServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {
//    private static final String LOG_FILE_PATH = "pub.key";
//
//    private static final int CHUNK_SIZE = 8192; // 块大小
//
//    @Override
//    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) {
//        try (RandomAccessFile file = new RandomAccessFile(LOG_FILE_PATH, "r");) {
//            long fileLength = file.length();
//            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
//            //添加响应头
//            response.headers().set(CONTENT_TYPE, "text/plain; charset=UTF-8");
//            response.headers().set(CACHE_CONTROL, "private, max-age=" + 60);
//            if (!HttpUtil.isKeepAlive(request)) {
//                response.headers().set(CONNECTION, HttpHeaderValues.CLOSE);
//            } else if (request.protocolVersion().equals(HttpVersion.HTTP_1_1)) {
//                response.headers().set(CONNECTION, HttpHeaderValues.KEEP_ALIVE);
//            }
//            //设置Content-Length
//            HttpUtil.setContentLength(response, fileLength);
//            // 写入消息头
//            ctx.write(response);
//            ChunkedFile chunkedFile = new ChunkedFile(file, CHUNK_SIZE);
//            log.info("chunkedFile length:{}", chunkedFile.length());
//            // 写入日志文件内容
////            ChannelFuture writeFuture = ctx.write(chunkedFile, ctx.newProgressivePromise());
//////                ChannelFuture writeFuture = ctx.writeAndFlush(new HttpChunkedInput(chunkedFile), ctx.newProgressivePromise());
////            writeFuture.addListener(new ChannelProgressiveFutureListener() {
////                @Override
////                public void operationProgressed(ChannelProgressiveFuture future, long progress, long total) {
////                    if (total < 0) { // total是未知的，因此显示发送的字节数
////                        log.error("{} Transfer progress: {}", future.channel(), progress);
////                    } else {
////                        log.error("{} Transfer progress: {}  / {}", future.channel(), progress, total);
////                    }
////                }
////
////                @Override
////                public void operationComplete(ChannelProgressiveFuture future) {
//////                        if (HttpUtil.isKeepAlive(request)) {
//////                            ctx.writeAndFlush(LastHttpContent.EMPTY_LAST_CONTENT).addListener(ChannelFutureListener.CLOSE);
//////                        }
////                    log.info("{}  Transfer complete.", future.channel());
////                }
////            });
//            ctx.writeAndFlush(new DefaultFileRegion(file.getChannel(),0,fileLength));
////            ctx.write(new ChunkedNioFile(file.getChannel()));
//
//            // 写入消息尾
//            ChannelFuture lastContentFuture = ctx.writeAndFlush(LastHttpContent.EMPTY_LAST_CONTENT);
//            if (!HttpUtil.isKeepAlive(request)) {
//                lastContentFuture.addListener(ChannelFutureListener.CLOSE);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//            ctx.writeAndFlush(new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.INTERNAL_SERVER_ERROR));
//        }
//    }
//
//
//    @Override
//    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
//        cause.printStackTrace();
//        ctx.close();
//    }
//}
