package NIO;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

//让文件在堆外内存进行修改,同步到文件用NIO完成
public class MappedByteBuffer {
    public static void main(String[] args) throws IOException {
        RandomAccessFile randomAccessFile=new RandomAccessFile("/home/lzz/Desktop/rust/1.txt","rw");

        FileChannel channel = randomAccessFile.getChannel();

        /*
        * 参数
        * 1.FileChannel.MapMode.READ_WRITE 使用的是读写模式
        * 2. 0 可以直接修改的起始位置
        * 3. 5 映射到内存的大小，即将文件的多少个字节映射到内存
        *      但可修改的范围也就是0-5,最多修改5个字节
        * */
        java.nio.MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_WRITE, 0, 5);
        map.put(0,(byte) 'S');
        map.put(3,(byte) 'H');

        randomAccessFile.close();
    }
}
