package 代理.静态;

public class ProxySubject implements Subject{
    RealSubject realSubject = null;

    public void eat() {
        if (realSubject == null){
            realSubject = new RealSubject();
        }
        prepare();
        realSubject.eat();
        clean();
    }

    private void prepare(){
        System.out.println("准备碗筷");
    }
    private void clean(){
        System.out.println("清洗碗筷");
    }
}
