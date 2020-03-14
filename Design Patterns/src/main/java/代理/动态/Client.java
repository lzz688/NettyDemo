package 代理.动态;


public class Client {
    public static void main(String[] args) {
        RealSubject realSubject = new RealSubject();
        ProxyHandler proxyHandler = new ProxyHandler();
        proxyHandler.setSubject(realSubject);
        Subject subject = (Subject)proxyHandler.getproxy();
        subject.eat();
    }
}
