package zzznettyheartbeat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

public class heartbeat {

    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup bossgroup= new NioEventLoopGroup();
        EventLoopGroup workergroup= new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap=new ServerBootstrap()
                    .group(bossgroup,workergroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))//在boss增加一个日志处理器
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            //得到piprline
                            ChannelPipeline pipeline = ch.pipeline();
                            //加入netty提供的IdleStateHandler
                            /*
                            * IdleStateHandler是netty提供的处理空闲状态的处理器
                            * readerIdleTimeSeconds 表示多长时间没有读了,就会发送一个心跳检测包检测是否是连接状态
                            * writerIdleTimeSeconds 表示多长时间没有写了,就会发送一个心跳检测包检测是否是连接状态
                            * allIdleTimeSeconds 表示多长时间没有读写了,就会发送一个心跳检测包检测是否是连接状态
                            * allIdleTimeSeconds触发后，就会传递给管道的下一个handler去处理
                            * 通过调用(触发) 下一个handler 的 userEventTiggered，在该方法中处理
                            * */
                            pipeline.addLast(new IdleStateHandler(3,5,7, TimeUnit.SECONDS));
                            //加入一个对空闲检测进一步的handler
                            pipeline.addLast(new heartServer());

                        }
                    });
            ChannelFuture sync = serverBootstrap.bind(7000).sync();
            sync.channel().closeFuture().sync();

        }finally {
            bossgroup.shutdownGracefully();
            workergroup.shutdownGracefully();
        }
    }
}
