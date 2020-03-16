package NIOdemo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NIOClient {
    public static void main(String[] args) throws IOException {

        //得到一个网络通道
        SocketChannel socketChannel = SocketChannel.open();
        //设置非阻塞
        socketChannel.configureBlocking(false);
        //提供服务器
        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 6666);

        //链接服务器
        if(!socketChannel.connect(inetSocketAddress)){
            while (!socketChannel.finishConnect()){
                System.out.println("因为链接需要时间，客户端不会阻塞，可做其他的事");
            }
        }
        //如果连接成功就发数据
        String str="hello liuzhengzhou";
        //wrap根据字符串包裹一个字节数组进入buffer
        ByteBuffer buffer = ByteBuffer.wrap(str.getBytes());
        //发送，将buffer写入channel
        socketChannel.write(buffer);
        System.in.read();
    }
}
