package zzzProtobuf;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.util.concurrent.TimeUnit;

//自定义一个handler，需要继承netty规定好的某个HandlerAdapter
public class Serverhandler extends ChannelInboundHandlerAdapter {

    /*
   * 1.ChannelHandlerContext  上下文对象，含有管道pipeline ， 通道channel ，地址
   * 2.Object msg 就是客户端发送的数据，默认Object
   * */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        //读取从客户端发哦少年宫的StudentPOJO.Student
        StudentPOJO.Student student= (StudentPOJO.Student) msg;
        System.out.println("客户端发送的数据" + student.getId() + "名字=" + student.getName());
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
