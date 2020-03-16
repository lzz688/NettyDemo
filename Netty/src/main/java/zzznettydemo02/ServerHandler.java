package zzznettydemo02;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class ServerHandler extends SimpleChannelInboundHandler<String> {

    //使用hashmap管理--实现私聊
    public static Map<String,Channel> channels=new HashMap<>();

    //定义一个channel组
    //GlobalEventExecutor.INSTANCE是一个全局的事件执行器，是一个单利
    private static ChannelGroup channelGroup=new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    //链接建立，一但链接，第一个被执行
    //将当前的channel加入channelgroup
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        //将该客户加入聊天的信息推送给其他客户端
        //该方法会将channelgroup中所有的channel遍历，并发送消息
        //我们不需要自己遍历
        channelGroup.writeAndFlush("[客户端]" + channel.remoteAddress() + "在" + sdf + "加入聊天\n");
        channelGroup.add(channel);

        channels.put("id100",channel);
    }

    //断开连接触发，将xx离开的信息推送给当前在线客户
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("[客户端]" + channel.remoteAddress() + "在" + sdf.format(new java.util.Date()) + "离开了\n");
    }

    //表示channel处于活动状态
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress()+ "在" + sdf.format(new java.util.Date())  + "上线了");
    }

    //表示channel处于不活动状态了，xx离线了
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + "在" + sdf.format(new java.util.Date()) + "离线了");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {

        //获取到channel
        Channel channel = ctx.channel();
        //遍历channelgroup，根据不同的情况，回送不同的消息
        channelGroup.forEach(ch -> {
            if (channel != ch){//不是当前的channel，直接转发
                ch.writeAndFlush("[客户]" + channel.remoteAddress()+ "在" + sdf.format(new java.util.Date()) + "发送了消息" + msg + "\n");
            }else {
                ch.writeAndFlush("[自己]发送了消息" + msg + "\n");
            }
        });
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        //关闭通道
        ctx.close();
    }
}
