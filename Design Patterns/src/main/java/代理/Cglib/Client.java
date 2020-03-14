package 代理.Cglib;

public class Client {
    public static void main(String[] args) {
        CglibProxy cglibProxy = new CglibProxy();
        RealSubject proxy = (RealSubject) cglibProxy.getProxy(RealSubject.class);
        proxy.eat();
    }
}
