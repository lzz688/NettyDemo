package zzznettydemo01;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class Server {
    public static void main(String[] args) throws Exception {
        //创建Bossgroup和workergroup
        //1.创建两个线程组Bossgroup和workergroup
        //2.Bossgroup只处理连接请求，业务处理workergroup
        //3.两个都是无限循环
        //4.Bossgroup和workergroup含有的子线程的EventLoopGroup
        //  默认是cpu核数 * 2
        EventLoopGroup bossgroup= new NioEventLoopGroup();
        EventLoopGroup workergroup= new NioEventLoopGroup();

        try {
            //创建服务器端启动对象，配置参数
            ServerBootstrap bootstrap = new ServerBootstrap();

            bootstrap.group(bossgroup, workergroup)//设置两个线程组
                    .channel(NioServerSocketChannel.class)//使用NioServerSocketChannel作为服务器通道的实现
                    .option(ChannelOption.SO_BACKLOG, 128)//设置线程队列得到的链接个数
                    .childOption(ChannelOption.SO_KEEPALIVE, true)//设置保持活动的连接状态
                    .childHandler(new ChannelInitializer<SocketChannel>() {//创建一个通道的测试对象
                        //给pipeline设置处理器
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new Serverhandler());//加入自己的处理器
                        }
                    });
            System.out.println("服务器准备好了");
            //绑定一个端口并且同步，生成一个ChannelFuture对象
            //启动服务器
            ChannelFuture ch = bootstrap.bind(6668).sync();
            //对关闭通道进行监听
            ch.channel().closeFuture().sync();//sync相当于把异步变成同步，等待另一个线程中的操作返回结果
        }finally {
            bossgroup.shutdownGracefully();
            workergroup.shutdownGracefully();
        }
    }
}
