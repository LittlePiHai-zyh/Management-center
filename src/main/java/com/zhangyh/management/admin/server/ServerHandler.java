//package com.zhangyh.management.admin.server;
//
//import io.netty.buffer.Unpooled;
//import io.netty.channel.*;
//import io.netty.handler.codec.http.*;
//import io.netty.handler.stream.ChunkedFile;
//
//import java.io.File;
//import java.io.IOException;
//import java.io.RandomAccessFile;
//
///**
// * @author zhangyh
// * @Date 2023/4/12 10:00
// * @desc
// */
//public class ServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {
//    private static final int READ_FILE_LENGTH = 1024;
//
//    @Override
//    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {
//        if (request.decoderResult().isSuccess()) {
//            String uri = request.uri();
//            // 检查uri是否为获取本地日志文件的请求
//            if (uri.startsWith("/logs")) {
//                String filePath = uri.substring(uri.indexOf("/logs") + 6);
//                File file = new File(filePath);
//                if (file.exists() && file.isFile()) {
//                    RandomAccessFile raf = new RandomAccessFile(file, "r");
//                    DefaultHttpResponse response = new DefaultHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
//                    response.headers().set(HttpHeaders.Names.CONTENT_TYPE, "text/plain; charset=UTF-8");
//                    boolean keepAlive = HttpHeaders.isKeepAlive(request);
//                    if (keepAlive) {
//                        response.headers().set(HttpHeaders.Names.CONTENT_LENGTH, raf.length());
//                        response.headers().set(HttpHeaders.Names.CONNECTION, HttpHeaders.Values.KEEP_ALIVE);
//                    }
//                    ctx.write(response);
//                    ChannelFuture sendFileFuture = ctx.writeAndFlush(new HttpChunkedInput(new ChunkedFile(raf, 0, raf.length(), 8192)),
//                            ctx.newProgressivePromise());
//                    sendFileFuture.addListener(new ChannelProgressiveFutureListener() {
//                        @Override
//                        public void operationComplete(ChannelProgressiveFuture future) {
//                            //完成传输文件以后清空资源
//                            if (raf != null) {
//                                try {
//                                    raf.close();
//                                } catch (IOException e) {
//                                    e.printStackTrace();
//                                }
//                            }
//                            if (keepAlive) {
//                                ctx.writeAndFlush(LastHttpContent.EMPTY_LAST_CONTENT).addListener(ChannelFutureListener.CLOSE);
//                            }
//                        }
//
//                        @Override
//                        public void operationProgressed(ChannelProgressiveFuture future, long progress, long total) {
//                            if (total < 0) { // total unknown
//                                System.err.println(future.channel() + " Transfer progress: " + progress);
//                            } else {
//                                System.err.println(future.channel() + " Transfer progress: " + progress + " / " + total);
//                            }
//                        }
//                    });
//                    return;
//                }
//            }
//            //其他请求默认相应404
//            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.NOT_FOUND,
//                    Unpooled.wrappedBuffer("404 Not Found".getBytes("UTF-8")));
//            response.headers().set(HttpHeaders.Names.CONTENT_TYPE, "text/plain; charset=UTF-8");
//            ctx.write(response).addListener(ChannelFutureListener.CLOSE);
//        }
//    }
//
//    @Override
//    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//        cause.printStackTrace();
//        ctx.close();
//    }
//}
