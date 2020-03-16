package zzznettyhttp;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;


//SimpleChannelInboundHandler
//HttpObject 表示客户端和服务器端相互通讯的数据被封装成HttpObject
public class HttpHandler extends SimpleChannelInboundHandler<HttpObject> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {

        //判断msg是不是httpRequest请求
        if (msg instanceof HttpRequest){

            System.out.println("msg 类型=" + msg.getClass());
            System.out.println("客户端地址" + ctx.channel().remoteAddress());

            //对特定资源进行过滤
            HttpRequest httpRequest= (HttpRequest) msg;
            URI uri=new URI(httpRequest.uri());
            if ("/xxxxxx.jpg".equals(uri.getPath())){
                System.out.println("请求xxxxxx.jpg，不做响应");
                return;
            }
            //回复信息给浏览器
            ByteBuf content = Unpooled.copiedBuffer("hello,我是服务器",CharsetUtil.UTF_8);

            //构造一个http相应的httpResponse
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,HttpResponseStatus.OK,content);

            response.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain;charset=utf-8");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH,content.readableBytes());

            ctx.writeAndFlush(response);
        }
    }
}
