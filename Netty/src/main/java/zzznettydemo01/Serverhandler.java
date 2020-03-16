package zzznettydemo01;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

//自定义一个handler，需要继承netty规定好的某个HandlerAdapter
public class Serverhandler extends ChannelInboundHandlerAdapter {

    /*
   * 1.ChannelHandlerContext  上下文对象，含有管道pipeline ， 通道channel ，地址
   * 2.Object msg 就是客户端发送的数据，默认Object
   * */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        //1.用户自定义非常耗时长的任务，->异步执行->提交该channel到对应的NIOEventLoop的
        //taskqueue中
        ctx.channel().eventLoop().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    ctx.writeAndFlush(Unpooled.copiedBuffer("hello,我睡醒了1",CharsetUtil.UTF_8));
                }catch (Exception e){
                    System.out.println("发生了异常" + e.getMessage());
                }
            }
        });

        //2用户自定义定时任务 提交到Scheduletaskqueue队列中
        ctx.channel().eventLoop().schedule(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    ctx.writeAndFlush(Unpooled.copiedBuffer("hello,我睡醒了2",CharsetUtil.UTF_8));
                }catch (Exception e){
                    System.out.println("发生了异常" + e.getMessage());
                }
            }
        } ,5, TimeUnit.SECONDS);


//        System.out.println("server ctx = " + ctx);
//        //将msg转成bytebuffer
//        //ByteBuf是netty提供的bytebuffer
//        ByteBuf buf=(ByteBuf)msg;
//        System.out.println("客户端发送的消息：" + buf.toString(CharsetUtil.UTF_8));
//        System.out.println("客户端地址：" + ctx.channel().remoteAddress());
    }

    //数据读取完毕
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //写入并刷新
        //一般来讲，对发送的数据进行编码
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello,客户端",CharsetUtil.UTF_8));
    }

    //处理异常，一般需要关闭通道
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
