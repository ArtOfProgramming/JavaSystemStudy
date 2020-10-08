/*
 * Copyright 2012 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package com.daiwei.source.echo2;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.EventLoopGroup;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import io.netty.util.concurrent.EventExecutorGroup;
import java.util.concurrent.Callable;

/**
 * Handler implementation for the echo server.
 */
@Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    // group 就是充当业务线程池，可以将任务提交到该线程池 耗时任务提交到group 不会阻塞netty io
    static final EventExecutorGroup group = new DefaultEventExecutorGroup(16);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        System.out.println("EchoServerHandler channelRead 的线程是:" + Thread.currentThread().getName());
        ctx.write(msg);
        // 提交耗时任务到taskQueue
//        ctx.channel().eventLoop().execute(() -> {
//            try {
//                Thread.sleep(5 * 1000);
//                System.out.println("EchoServerHandler execute 的线程是:" + Thread.currentThread().getName());
//                ctx.writeAndFlush(Unpooled.copiedBuffer("daiwei nihao", CharsetUtil.UTF_8));
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
        // 将任务提交到group
        group.submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                try {
                    Thread.sleep(5 * 1000);
                    System.out.println("EchoServerHandler execute 的线程是:" + Thread.currentThread().getName());
                    ctx.writeAndFlush(Unpooled.copiedBuffer("daiwei nihao", CharsetUtil.UTF_8));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }
        });

        System.out.println("go on");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }
}
