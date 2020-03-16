package zzzProtobuf2;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;


//自定义一个handler，需要继承netty规定好的某个HandlerAdapter
public class Serverhandler extends SimpleChannelInboundHandler<StudnetInfo.Mymessage> {

    //数据读取完毕
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //写入并刷新
        //一般来讲，对发送的数据进行编码
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello,客户端",CharsetUtil.UTF_8));
    }

    //处理异常，一般需要关闭通道
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, StudnetInfo.Mymessage msg) throws Exception {
        //根据DataType来显示不同的信息
        StudnetInfo.Mymessage.DataType dataType = msg.getDataType();
        if ( dataType == StudnetInfo.Mymessage.DataType.StudentType){
            StudnetInfo.Student student = msg.getStudent();
            System.out.println("学生=" + student.getId() + "学生名字" + student.getName());
        }else if(dataType == StudnetInfo.Mymessage.DataType.WorkerType){
            StudnetInfo.Worker worker = msg.getWorker();
            System.out.println("工人=" + worker.getAge() + "工人名字" + worker.getName());
        }else{
            System.out.println("传输的类型不正确");
        }
    }
}
