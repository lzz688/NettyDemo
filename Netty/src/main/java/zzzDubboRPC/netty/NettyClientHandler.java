package zzzDubboRPC.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.Callable;

public class NettyClientHandler extends ChannelInboundHandlerAdapter implements Callable {

    private ChannelHandlerContext context;
    private String result;//返回的结果
    private String para;//客户端调用时传进来的参数

    //与服务器创建成功时就被调用
    // (1)
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelActive 方法被调用");
        context=ctx;//在其他方法会使用到ctx
    }


    //收到服务器数据后就被调用
    //和call方法存在同步关系
    //(4)
    @Override
    public synchronized void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("channelRead 方法被调用");
        result=msg.toString();
        notify();//唤醒等待的线程
        /*
        * 因为客户端调用call方法时，客户端会发送消息给服务器，但因为call方法是用反射调用，消息没有马上回来时
        * 需要使用wait等待，服务器回送数据到channelRead方法，接收到回数据后唤醒call方法继续执行，再得到结果
        * 返回给proxy代理对象
        * */

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }

    //被代理对象调用，真正发送数据给服务器-->wait-->等待被唤醒(channelRead)-->返回结果
    //(3)-->(5)
    @Override
    public synchronized Object call() throws Exception {
        System.out.println("call 方法被调用");
        context.writeAndFlush(para);//相当于Server得到的msg
        wait();//等到channelRead方法获取到结果后唤醒
        return result;//服务方返回的结果
    }

    //(2)
    void serPara(String para){
        System.out.println("serPara 方法被调用");
        this.para=para;
    }
}
