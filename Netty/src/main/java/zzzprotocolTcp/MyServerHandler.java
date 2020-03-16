package zzzprotocolTcp;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.Charset;
import java.util.UUID;


public class MyServerHandler extends SimpleChannelInboundHandler<MessageProtocol> {

    private int count;
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageProtocol msg) throws Exception {

        //接收数据并处理
        int len = msg.getLen();
        byte[] content = msg.getContent();

        System.out.println("服务器收到的信息如下");
        System.out.println("长度=" + len);
        System.out.println("内容=" + new String(content,Charset.forName("utf-8")));

        System.out.println("服务器接收到消息包数量" + (++this.count));

        //回复消息
        String response= UUID.randomUUID().toString();
        int length = response.getBytes("utf-8").length;
        //构建一个协议包
        MessageProtocol messageProtocol= new MessageProtocol();
        messageProtocol.setLen(length);
        messageProtocol.setContent(response.getBytes("utf-8"));
        ctx.writeAndFlush(messageProtocol);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
