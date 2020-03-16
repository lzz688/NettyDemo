package zzzDubboRPC.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import javax.imageio.plugins.jpeg.JPEGImageReadParam;
import java.lang.reflect.Proxy;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class NettyClient {

    //创建线程池
    private static ExecutorService executor=
            Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    private static NettyClientHandler client;

    //创建代理对象
    public Object getBean(final Class<?> serviceClass,final String providerName){
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                new Class<?>[] {serviceClass},((proxy, method, args) -> {
                    //这部分代码，每调用一次hello,就会被执行一次
                    if (client == null){
                        initClient();
                    }
                    //设置发给服务器分段的参数
                    client.serPara(providerName + args[0]);//协议头 + hello()的参数
                    return executor.submit(client).get();
                }));
    }


    //初始化客户端
    private static void initClient() throws InterruptedException {
        client = new NettyClientHandler();
        NioEventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                 .channel(NioSocketChannel.class)
                 .option(ChannelOption.TCP_NODELAY,true)//无延时TCP
                 .handler(new ChannelInitializer<SocketChannel>() {
                     @Override
                     protected void initChannel(SocketChannel ch) throws Exception {

                         ChannelPipeline pipeline = ch.pipeline();
                         pipeline.addLast(new StringEncoder());
                         pipeline.addLast(new StringDecoder());
                         pipeline.addLast(client);
                     }
                 });
        try{
            bootstrap.connect("127.0.0.1",7000).sync();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
