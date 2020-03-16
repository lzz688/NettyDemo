package zzzhandler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class MyServerInitializer  extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {

        ChannelPipeline pipeline = ch.pipeline();

        //入站的handler进行解码 MyByteTo
        pipeline.addLast(new MyByteToLongDecoder());

        //出站编码
        pipeline.addLast(new MyLongToByteEncoder());

        //自定义的handler
        pipeline.addLast(new MyServerhandler());
    }
}
