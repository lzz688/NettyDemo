package zzzDubboRPC.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class NettyServer {

    //根据不同的地址和端口号调用不同的服务
    public static void startServer(String hostname,int port){
        startServer0( hostname, port);
    }
    //编写一个方法，完成对nettyserver的初始化和启动
    private static void startServer0(String hostname,int port){
        NioEventLoopGroup bossgroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workergroup = new NioEventLoopGroup();

        try{
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossgroup,workergroup)
                           .channel(NioServerSocketChannel.class)
                           .childHandler(new ChannelInitializer<SocketChannel>() {
                               @Override
                               protected void initChannel(SocketChannel ch) throws Exception {
                                   ChannelPipeline pipeline = ch.pipeline();
                                   pipeline.addLast(new StringDecoder());
                                   pipeline.addLast(new StringEncoder());
                                   pipeline.addLast(new NettyServerhandler());//自己的业务处理器

                               }
                           });
            System.out.println("服务提供方开始提供服务");
            ChannelFuture channelFuture = serverBootstrap.bind(hostname, port).sync();
            channelFuture.channel().closeFuture().sync();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            bossgroup.shutdownGracefully();
            workergroup.shutdownGracefully();
        }
    }
}
