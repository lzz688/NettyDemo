package zzzProtobuf2;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import zzzProtobuf.StudentPOJO;

import java.util.Date;
import java.util.Random;

public class Clienthandler extends ChannelInboundHandlerAdapter {

    //通道就绪就会触发该方法
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //随机发送一个student/worker对象到服务器

        int random = new Random().nextInt(3);
        StudnetInfo.Mymessage mymessage = null;

        if (random == 0){
            mymessage=StudnetInfo.Mymessage.newBuilder().setDataType(StudnetInfo.Mymessage.DataType.StudentType)
                       .setStudent(StudnetInfo.Student.newBuilder().setId(5).setName("lalalalalalalala").build()).build();
        }else{
            mymessage=StudnetInfo.Mymessage.newBuilder().setDataType(StudnetInfo.Mymessage.DataType.WorkerType)
                    .setWorker(StudnetInfo.Worker.newBuilder().setAge(30).setName("laowang").build()).build();
        }
        ctx.writeAndFlush(mymessage);
    }

    //当通道有读取事件时，会触发
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf=(ByteBuf) msg;
        System.out.println("服务器返回的信息 " + buf.toString(CharsetUtil.UTF_8));
        System.out.println("服务器的地址");
    }

    //发生异常
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
