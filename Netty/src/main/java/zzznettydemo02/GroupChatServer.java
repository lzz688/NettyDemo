package zzznettydemo02;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class GroupChatServer {

    private int port;
    public GroupChatServer(int port){
        this.port=port;
    }

    //编写run方法处理客户端请求

    public void run() throws InterruptedException {

        //创建线程组
        NioEventLoopGroup bossgroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workergroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();

            serverBootstrap.group(bossgroup, workergroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {

                            ChannelPipeline pipeline = socketChannel.pipeline();
                            //向pipeline加入一个解码器
                            pipeline.addLast("decoder", new StringDecoder());
                            //向pipeline加入一个编码器
                            pipeline.addLast("encoder", new StringEncoder());
                            //加入自己的处理器handler
                            pipeline.addLast(new ServerHandler());
                        }
                    });
            System.out.println("netty服务器启动");
            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();

            //监听关闭事件
            channelFuture.channel().closeFuture().sync();
        }finally {
            bossgroup.shutdownGracefully();
            workergroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new GroupChatServer(7000).run();
    }
}
