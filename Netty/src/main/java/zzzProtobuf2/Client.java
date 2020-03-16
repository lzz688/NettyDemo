package zzzProtobuf2;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufEncoder;

public class Client {
    public static void main(String[] args) throws InterruptedException {

        //客户端需要一个时间循环组
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();

        try {
            //创建客户端启动对象
            //使用Bootstrap
            Bootstrap bootstrap = new Bootstrap();

            //设置相关参数
            bootstrap.group(eventLoopGroup)//设置线程组
                    .channel(NioSocketChannel.class)//设置客户端通道的类
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pipeline=socketChannel.pipeline();
                            pipeline.addLast("encoder",new ProtobufEncoder());
                            pipeline.addLast(new Clienthandler());//加入自己的处理器
                        }
                    });
            System.out.println("客户端ok");

            //启动客户端去连接服务器端
            //关于ChannelFuture分析，涉及到netty的异步模型
            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 6668).sync();
            channelFuture.channel().closeFuture().sync();
        }finally {
            eventLoopGroup.shutdownGracefully();
        }
    }
}
