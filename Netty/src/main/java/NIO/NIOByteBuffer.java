package NIO;

import java.nio.Buffer;
import java.nio.ByteBuffer;

public class NIOByteBuffer {
    public static void main(String[] args) {
        ByteBuffer buffer=ByteBuffer.allocate(64);

        buffer.putInt(1);
        buffer.putLong(9);
        buffer.putChar('刘');
        buffer.putShort((short)4);

        buffer.flip();

        System.out.println();

        //顺序不可变动
        System.out.println(buffer.getInt());
        System.out.println(buffer.getLong());
        System.out.println(buffer.getChar());
        System.out.println(buffer.getShort());
    }
}
