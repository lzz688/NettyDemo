package zzzDubboRPC.netty;


import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import zzzDubboRPC.Provider.HelloServiceImpl;

public class NettyServerhandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        //获取客户端发送的消息，并调用相应服务
        System.out.println("msg=" + msg);
        //客户端在调用服务器的服务时，需要定义一个协议
        //比如---要求每次发消息时，必须以某个字符串开头"HelloService#hello#xxx"
        if (msg.toString().startsWith("HelloService#hello#")){

            String result = new HelloServiceImpl().hello(msg.toString().substring(msg.toString().lastIndexOf("#") + 1));
            ctx.writeAndFlush(result);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
