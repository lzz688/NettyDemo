package zzzwebSocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;
import zzznettyheartbeat.heartServer;

import java.util.concurrent.TimeUnit;

public class MyServer {
    public static void main(String[] args) throws Exception{
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

                            //因为基于http，使用http编码解码器
                            pipeline.addLast(new HttpServerCodec());
                            //是以块方式写，添加ChunkWriter处理器
                            pipeline.addLast(new ChunkedWriteHandler());
                            /*
                            * http传输数据时是分段的，HttpObjectAggregator是将多个段整合
                            * 这就是为什么，当浏览器发送大量数据时，就会发送多个请求
                            * */
                            pipeline.addLast(new HttpObjectAggregator(8192));
                            /*
                            * 对于websocket，他的数据是以帧传递的
                            * websocketframe下面有六个子类
                            * 浏览器请求时 ws://localhost:7000/xxx 表示请求的uri
                            * WebSocketServerProtocolHandler核心功能将http升级为ws协议，保持长连接
                            * */
                            pipeline.addLast(new WebSocketServerProtocolHandler("/hello"));
                            //自定义handler
                            pipeline.addLast(new WebSockethandler());
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

