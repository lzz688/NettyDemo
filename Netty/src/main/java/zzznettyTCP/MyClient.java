package zzznettyTCP;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;



public class MyClient {
    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup group = new NioEventLoopGroup();
        try{
            Bootstrap bootstrap=new Bootstrap();
            bootstrap.group(group)
                     .channel(NioSocketChannel.class)
                     .handler(new MyClientInitializer());

            ChannelFuture channelFuture = bootstrap.connect("localhost", 7005).sync();

            channelFuture.channel().closeFuture().sync();


        }finally {
            group.shutdownGracefully();
        }
    }
}
