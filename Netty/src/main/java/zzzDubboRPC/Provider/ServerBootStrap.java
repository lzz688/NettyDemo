package zzzDubboRPC.Provider;


import zzzDubboRPC.netty.NettyServer;

//ServerBootStrap 会启动一个服务的提供者，相当于nettyServer
public class ServerBootStrap {
    public static void main(String[] args) {

        NettyServer.startServer("127.0.0.1",7000);
    }
}
