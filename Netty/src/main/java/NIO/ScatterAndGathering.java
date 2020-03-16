package NIO;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/*
* Scatter:将数据写入到buffer中，可以采用buffer数组，依次写入
* Gather:将数据读出到buffer，可以采用buffer数组，依次读取
* */
public class ScatterAndGathering {
    public static void main(String[] args) throws IOException {
        //使用ServerSocketChanne和SocketChannel
        ServerSocketChannel serverSocketChannel=ServerSocketChannel.open();

        InetSocketAddress inetSocketAddress = new InetSocketAddress(7000);

        //绑定端口,并启动
        serverSocketChannel.socket().bind(inetSocketAddress);

        ByteBuffer[] byteBuffer=new ByteBuffer[2];
        byteBuffer[0]=ByteBuffer.allocate(5);
        byteBuffer[1]=ByteBuffer.allocate(3);

        SocketChannel socketChannel=serverSocketChannel.accept();

        int messageLength=8;//假定从客户端接受8个字节
        while(true){

            int byteread=0;

            while (byteread < messageLength){
                long read = socketChannel.read(byteBuffer);
                byteread+=read;//累计读取的字节数
                System.out.println(byteread);
                //使用流打印
                Arrays.asList(byteBuffer).stream().map(buffer -> "position" + buffer.position() + "limit" + buffer.limit()).forEach(System.out::println);
            }
            //将所有的buffer进行filp
            Arrays.asList(byteBuffer).forEach(buffer -> buffer.flip());

            //显示读出的数据
            long byteWrite = 0;
            while(byteWrite < messageLength){
                long write = socketChannel.write(byteBuffer);
                byteWrite += write;

            }
            //将所有的buffer进行clear
            Arrays.asList(byteBuffer).forEach(buffer -> buffer.clear());

            System.out.println("byteread" + byteread);
            System.out.println("byteWrite" + byteWrite);

        }
    }
}
