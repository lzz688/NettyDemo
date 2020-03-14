package 单例;



//饿汉式--线程安全
public class Singleton1 {

    private static Singleton1 uniqueInstance = new Singleton1();

    private Singleton1(){

    }

    public static Singleton1 getSingleton1(){
        return uniqueInstance;
    }
}
