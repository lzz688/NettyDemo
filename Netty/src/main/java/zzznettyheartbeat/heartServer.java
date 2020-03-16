package zzznettyheartbeat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

public class heartServer extends ChannelInboundHandlerAdapter {

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent){

            //将evt向下转型
            IdleStateEvent event=(IdleStateEvent) evt;
            String eventType=null;
            switch(event.state()){
                case READER_IDLE:
                    eventType="现在发生了读空闲";
                    break;
                case WRITER_IDLE:
                    eventType="现在发生了写空闲";
                    break;
                case ALL_IDLE:
                    eventType="现在发生了读写空闲";
                    break;
            }
            System.out.println(ctx.channel().remoteAddress() + "--超时时间--" + eventType);
            System.out.println("服务器作相应处理");

            //如果发生空闲，关闭通道
            ctx.close();
        }
    }
}
