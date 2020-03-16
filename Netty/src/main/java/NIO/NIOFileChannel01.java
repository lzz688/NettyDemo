package NIO;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOFileChannel01 {
    public static void main(String[] args) throws IOException {
        String str="hello xxxx";
        //创建输出流
        FileOutputStream fileOutputStream = new FileOutputStream("/home/lzz/Desktop/rust/file.txt");

        //通过fileOutputStream获取对应的FileChannel
        //fileChannel真是类型是fileChannelImpl
        FileChannel fileChannel = fileOutputStream.getChannel();

        //创建一个缓冲区
        ByteBuffer byteBuffer=ByteBuffer.allocate(1024);

        byteBuffer.put(str.getBytes());

        //对byteBuffer进行翻转
        byteBuffer.flip();

        //将bytebuffer写入filechannel
        fileChannel.write(byteBuffer);
        fileOutputStream.close();
    }
}
