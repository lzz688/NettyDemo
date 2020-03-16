package zzznettybuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class buf1 {
    public static void main(String[] args) {

        /*
        * 创建一个Bytebuf
        * 1.创建对象，包含一个数组arr，是一个byte【10】
        * 2.netty的buffer中不需要filp反转
        * 底层维护了readindex和writeindex
        * 通过readindex，writeindex和capacity，将buffer分成三个区域
        * 0-readindex 已经读取的区域
        * readindex-writeindex 可读的区域
        * writeindex-capacity可写的区域
        * */

        ByteBuf byteBuf= Unpooled.buffer(10);

        for (int i=0;i<10;i++){
            byteBuf.writeByte(i);
        }

        System.out.println(byteBuf.capacity());

        for (int i=0;i<byteBuf.capacity();i++){
            System.out.println(byteBuf.readByte());
        }
        System.out.println("执行完毕");
    }
}
