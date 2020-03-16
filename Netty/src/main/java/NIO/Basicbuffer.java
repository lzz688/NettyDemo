package NIO;

import java.nio.IntBuffer;

//Buffer的使用
public class Basicbuffer {
    public static void main(String[] args) {
        //创建一个buffer，可存放5个int
        IntBuffer intBuffer=IntBuffer.allocate(5);
        intBuffer.put(10);
        intBuffer.put(11);
        intBuffer.put(12);
        intBuffer.put(13);
        intBuffer.put(14);

        //从buffer读数据，将buffer转换，读写切换
        intBuffer.flip();

        while(intBuffer.hasRemaining()){
            System.out.println(intBuffer.get());
        }
    }
}
