package NIOLiaoTian;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

public class GroupServer {

    private Selector selector;
    private ServerSocketChannel listen;
    private static final int PORT=6667;

    public GroupServer(){
        try {
            selector=Selector.open();
            listen=ServerSocketChannel.open();
            listen.socket().bind(new InetSocketAddress(PORT));
            listen.configureBlocking(false);
            listen.register(selector, SelectionKey.OP_ACCEPT);

        }catch (IOException e){
            e.printStackTrace();
        }
    }//监听
    public void listen(){
        try {
            //循环处理
            while(true){
                int count=selector.select(2000);
                if (count > 0){//有事件处理
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while(iterator.hasNext()){
                        SelectionKey key=iterator.next();
                        if (key.isAcceptable()){
                            SocketChannel accept = listen.accept();
                            accept.configureBlocking(false);
                            accept.register(selector,SelectionKey.OP_READ);
                            System.out.println(accept.getLocalAddress()+"上线");
                        }
                        if (key.isReadable()){
                            //处理读
                            readData(key);
                        }
                        iterator.remove();
                    }
                }else{
                    System.out.println("等待");
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    //读取客户端消息
    private void readData(SelectionKey key) throws IOException {
        SocketChannel socketChannel=null;
        try {
         socketChannel=(SocketChannel) key.channel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int count=socketChannel.read(buffer);
            if (count > 0){
                String msg = new String(buffer.array());
                System.out.println("客户端消息"+msg);
                //向其他的客户端转发消息
                sendInfo(msg,socketChannel);
            }
        } catch (IOException e) {
            System.out.println(socketChannel.getRemoteAddress()+"离线了");
            //取消注册
            key.cancel();
            //关闭通道
            socketChannel.close();
        }
    }

    //转发消息给其他客户
    private void sendInfo(String msg,SocketChannel self) throws IOException {
        System.out.println("服务器转发消息中");
        for (SelectionKey key:selector.keys()){
            //通过key取出对应的channel
            Channel targetchannel = key.channel();
            //排除自己
            if (targetchannel instanceof SocketChannel && targetchannel != self){
                //转型
                SocketChannel dest = (SocketChannel) targetchannel;
                //将msg存储到buffer
                ByteBuffer byteBuffer=ByteBuffer.wrap(msg.getBytes());
                //将buffer数据写入通道
                dest.write(byteBuffer);
            }
        }
    }
    public static void main(String[] args) {
        //创建服务器对象
        GroupServer groupServer = new GroupServer();
        groupServer.listen();
    }
}
