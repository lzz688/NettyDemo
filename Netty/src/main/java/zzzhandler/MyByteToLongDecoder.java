package zzzhandler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class MyByteToLongDecoder extends ByteToMessageDecoder {

    //ctx :上下文集合
    //in : 入站的Bytebuf
    //out : list集合 ，将解码后的数据传给下一个handler

    /*
    * decode会根据接受的数据，被调用多次，直到确定没有新的元素被添加到list
    * 或者Bytebuf没有更多的可读字节为止
    * 如果list out不为空，就会将list的内容传递给下一个
    * channellinboundhandler处理，该处理器的方法也会被调用多次
    * */
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

        System.out.println("MyByteToLongDecoder 被调用");
        //需要判断有8个字节才能读取一个long
        if (in.readableBytes() >= 8){
            out.add(in.readLong());
        }
    }
}
