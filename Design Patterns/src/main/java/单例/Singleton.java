package 单例;


//懒汉式--线程不安全
public class Singleton {

    private static Singleton uniqueInstance;

    private Singleton(){}

    public static Singleton getUniqueInstance(){
        if (uniqueInstance == null){
            uniqueInstance = new Singleton();
        }
        return uniqueInstance;
    }
}
