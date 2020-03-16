package NIO;

import java.nio.ByteBuffer;

public class ReadOnlyBuffer {
    public static void main(String[] args) {
        ByteBuffer byteBuffer=ByteBuffer.allocate(64);

        for (int i=0 ;i<64;i++){
            byteBuffer.put((byte) i);
        }

        byteBuffer.flip();
        //获取一个只读buffer
        ByteBuffer readOnlyBuffer = byteBuffer.asReadOnlyBuffer();
        System.out.println(readOnlyBuffer.getClass());

        while(readOnlyBuffer.hasRemaining()){
            System.out.println(readOnlyBuffer.get());
        }

    }
}
