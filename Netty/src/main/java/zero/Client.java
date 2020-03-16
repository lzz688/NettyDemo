package zero;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

public class Client {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel=SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost",7001));
        String filename ="BIO.zip";

        FileChannel fileChannel=new FileInputStream(filename).getChannel();

        long starttime=System.currentTimeMillis();

        //Linux下一个transferTO方法就可以完成传输
        //在windows下一次调用transferTO，只能发送8M ，需要分段传输
        //transferTo底层使用零拷贝
        long transferTo = fileChannel.transferTo(0, fileChannel.size(), socketChannel);

        System.out.println("发送的字节数"+transferTo+"耗时"+(System.currentTimeMillis()-starttime));

        fileChannel.close();
    }
}
