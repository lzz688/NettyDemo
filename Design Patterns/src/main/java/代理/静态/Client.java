package 代理.静态;

public class Client {
    public static void main(String[] args) {
        ProxySubject proxySubject = new ProxySubject();
        proxySubject.eat();
    }
}
