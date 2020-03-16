package NIOLiaoTian;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;

public class GroupClient {

    private final String Host="127.0.0.1";
    private final int PORT=6667;
    private Selector selector;
    private SocketChannel socketChannel;
    private String username;

    public GroupClient() throws IOException {
        selector=Selector.open();
        socketChannel=socketChannel.open(new InetSocketAddress(Host,PORT));
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);
        username=socketChannel.getLocalAddress().toString().substring(1);
        System.out.println(username + "is ok....");
    }

    //向服务器发送消息
    public void sendInfo(String str){
        str=username + "说:" +str;
        try{
            socketChannel.write(ByteBuffer.wrap(str.getBytes()));
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    //读取从服务器端恢复的消息
    public void readInfo(){
        try {
            int readChannel=selector.select();
            if(readChannel > 0){
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while(iterator.hasNext()){
                    SelectionKey key = iterator.next();
                    if (key.isReadable()){
                        //得到相关的通道
                        SocketChannel sc= (SocketChannel) key.channel();
                        //得到一个Buffer
                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                        //读取
                        sc.read(byteBuffer);
                        String msg=new String(byteBuffer.array());
                        System.out.println(msg.trim());
                    }
                }
                iterator.remove();
            }else{
                //System.out.println("没有可以用的通道");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws IOException {
        GroupClient groupClient = new GroupClient();

        //启动一个线程，读取从服务器端发送的数据
        new Thread(){
            public void run(){
                while(true){
                    groupClient.readInfo();
                    try {
                        Thread.currentThread().sleep(3000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        }.start();
        //发送数据给服务器端
        Scanner scanner=new Scanner(System.in);
        while(scanner.hasNextLine()){
            String s=scanner.nextLine();
            groupClient.sendInfo(s);
        }
    }
}
