package zzznettyhttp;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class Server {
    public static void main(String[] args) throws Exception{

        EventLoopGroup bossgroup= new NioEventLoopGroup();
        EventLoopGroup workergroup= new NioEventLoopGroup();

        try {
            ServerBootstrap bootstrap= new ServerBootstrap();
            bootstrap.group(bossgroup,workergroup)
                     .channel(NioServerSocketChannel.class)
                     .childHandler(new ServerInit());

            ChannelFuture channelFuture=bootstrap.bind("localhost",9999).sync();

            channelFuture.channel().closeFuture().sync();
        }finally {
            bossgroup.shutdownGracefully();
            workergroup.shutdownGracefully();
        }
    }
}
