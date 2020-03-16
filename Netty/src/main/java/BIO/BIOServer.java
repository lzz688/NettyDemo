package BIO;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//telnet 127.0.0.1 6666
public class BIOServer {
    public static void main(String[] args) {
        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
        //创建
        ServerSocket serverSocket;

        {
            try {
                serverSocket = new ServerSocket(6666);
                System.out.println("服务器启动了");

                final Socket socket=serverSocket.accept();
                System.out.println("链接到了一个客户端");
                newCachedThreadPool.execute(new Runnable() {
                    public void run() {
                        handler(socket);
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    public static void handler(Socket socket){

        try {
            System.out.println("线程信息 id="+ Thread.currentThread().getId() +"名字="+Thread.currentThread().getName());
            byte[] bytes=new byte[1024];
            InputStream inputStream=socket.getInputStream();
            while(true){
                int read = inputStream.read(bytes);
                if (read != -1){
                    System.out.println(new String(bytes,0,read));
                }else {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
