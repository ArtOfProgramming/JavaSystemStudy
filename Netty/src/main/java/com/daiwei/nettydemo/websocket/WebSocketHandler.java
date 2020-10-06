package com.daiwei.nettydemo.websocket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import java.time.LocalDateTime;

// TextWebSocketFrame类型，表示一个文本帧
public class WebSocketHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {

        System.out.println("服务器收到消息" + msg.text());

        // 回复消息
        ctx.channel().writeAndFlush(new TextWebSocketFrame("服务器时间" + LocalDateTime.now() + " " + msg.text()));

    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        // id 标识唯一的值，LongText是唯一的，shortText不是唯一的
        System.out.println("handlerAdded被调用了" + ctx.channel().id().asLongText());
        System.out.println("handlerAdded被调用了" + ctx.channel().id().asShortText());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        // id 标识唯一的值，LongText是唯一的，shortText不是唯一的
        System.out.println("handlerRemoved被调用了" + ctx.channel().id().asLongText());
        System.out.println("handlerRemoved被调用了" + ctx.channel().id().asShortText());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("异常发生了" + cause.getMessage());
        ctx.close(); // 关闭连接
    }
}
