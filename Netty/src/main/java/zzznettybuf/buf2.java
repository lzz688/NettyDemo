package zzznettybuf;

import com.sun.org.apache.xpath.internal.operations.String;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import static io.netty.util.CharsetUtil.UTF_8;
import static java.nio.charset.StandardCharsets.*;

public class buf2 {
    public static void main(String[] args) {


        ByteBuf byteBuf = Unpooled.copiedBuffer("hello,world", Charset.forName("UTF-8"));

        if (byteBuf.hasArray()){

            byte[] content = byteBuf.array();

            //将content转成字符串
           //System.out.println(new String(content, UTF_));
            System.out.println(byteBuf);

            System.out.println(byteBuf.arrayOffset());
            System.out.println(byteBuf.readerIndex());
            System.out.println(byteBuf.writerIndex());
            System.out.println(byteBuf.capacity());
        }
    }
}
