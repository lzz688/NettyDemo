package zzznettyTCP;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;


public class MyServer {
    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup bossgroup = new NioEventLoopGroup(1);
        EventLoopGroup workergroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossgroup,workergroup)
                           .channel(NioServerSocketChannel.class)
                           .childHandler(new MyServerInitializer());

            ChannelFuture channelFuture = serverBootstrap.bind(7005).sync();
            channelFuture.channel().closeFuture().sync();

        }finally {
            bossgroup.shutdownGracefully();
            workergroup.shutdownGracefully();
        }
    }
}
