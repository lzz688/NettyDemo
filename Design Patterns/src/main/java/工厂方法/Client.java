package 工厂方法;

public class Client {
    public static void main(String[] args) {
        Factory factory = new ConcreteFactory();
        Factory factory1 = new ConcreteFactory1();
        Factory factory2 = new ConcreteFactory2();
        System.out.println(factory.getClass());
        System.out.println(factory1.getClass());
        System.out.println(factory2.getClass());
        factory.dosomething();
    }
}
