package NIOdemo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class NIOServer {
    public static void main(String[] args) throws IOException {
        //创建ServerSocketChannel
        ServerSocketChannel serverSocketChannel=ServerSocketChannel.open();
        //得到一个Selector对象
        Selector selector=Selector.open();
        //绑定一个端口
        serverSocketChannel.socket().bind(new InetSocketAddress(6666));
        //设置为非阻塞
        serverSocketChannel.configureBlocking(false);

        //把serverSocketChannel注册到selector
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        //循环等待客户端链接
        while(true){
            if (selector.select(1000)==0){
                System.out.println("服务器等待了1秒，无连接");
                continue;
            }

            //如果返回的>0,获取到相关的selectionKeys集合
            //selector.selectedKeys()返回关注事件的集合
            //通过selectionKeys反向获取通道
            Set<SelectionKey> selectionKeys=selector.selectedKeys();

            //遍历Set<SelectionKey>
            Iterator<SelectionKey> keyIterator=selectionKeys.iterator();

            while(keyIterator.hasNext()){
                //获取到的SelectionKey
                SelectionKey key=keyIterator.next();
                //根据key，对应通道发生的事件做处理
                if(key.isAcceptable()){
                    //该客户端生成一个SocketChannel
                    SocketChannel socketChannel=serverSocketChannel.accept();

                    //将socketchannel设置为非阻塞

                    System.out.println("客户端连接成功，生成一个socketchannel"+socketChannel.hashCode());
                    socketChannel.configureBlocking(false);
                    //将socketChannel注册到Selector
                    //关联一个buffer
                    socketChannel.register(selector,SelectionKey.OP_READ, ByteBuffer.allocate(1024));

                }
                if (key.isAcceptable()){//发生read
                    //通过key，反向获取到对应的channel
                    SocketChannel channel = (SocketChannel) key.channel();
                    //获取到该channel关联的buffer
                    ByteBuffer buffer =(ByteBuffer) key.attachment();
                    channel.read(buffer);
                    System.out.println("客户端发送的信息"+new String(buffer.array()));
                }
                //手动从集合中移动当前的SelectionKey，防止重复操作
                keyIterator.remove();
            }

        }
    }

}
