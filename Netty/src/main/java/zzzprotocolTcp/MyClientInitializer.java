package zzzprotocolTcp;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

import javax.imageio.plugins.jpeg.JPEGImageReadParam;

public class MyClientInitializer extends ChannelInitializer<SocketChannel> {


    @Override
    protected void initChannel(SocketChannel ch) throws Exception {

        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new MyMessageEncoder());//编码器
        pipeline.addLast(new MyMessageDecoder());
        pipeline.addLast(new MyClientHandler());

    }
}
