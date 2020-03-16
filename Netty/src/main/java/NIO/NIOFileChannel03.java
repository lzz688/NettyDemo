package NIO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOFileChannel03 {
    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream=new FileInputStream("/home/lzz/Desktop/rust/1.txt");
        FileChannel fileChannel1=fileInputStream.getChannel();
        FileOutputStream fileOutputStream=new FileOutputStream("/home/lzz/Desktop/rust/2.txt");
        FileChannel fileChannel2=fileOutputStream.getChannel();
        ByteBuffer byteBuffer=ByteBuffer.allocate(512);

        while(true){
            //不复位，position和limit相等读出来read=0
            byteBuffer.clear();//清空buffer
            int read = fileChannel1.read(byteBuffer);
            System.out.println(read);
            if(read==-1){
                break;
            }
            byteBuffer.flip();
            fileChannel2.write(byteBuffer);

        }
        fileInputStream.close();
        fileOutputStream.close();
    }
}
