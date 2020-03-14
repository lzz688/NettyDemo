package 单例;



/*
* 使用双重校验锁，也就是需要使用两个 if 语句：
* 第一个 if 语句用来避免 uniqueInstance 已经被实例化之后的加锁操作，
* 而第二个 if 语句进行了加锁，所以只能有一个线程进入，
* 就不会出现 uniqueInstance == null 时两个线程同时进行实例化操作。
* */
//双重校验锁
public class Singleton3 {

    private volatile static Singleton3 uniqueInstance;

    private static boolean flag = false;

    //防止反射攻击
    private Singleton3(){
        synchronized (Singleton3.class){
            if (flag == false){
                flag = true;
            }else{
                throw new RuntimeException("不要试图用反射破坏异常");
            }
        }
    }

    public static Singleton3 getUniqueInstance(){
        if (uniqueInstance == null){
            synchronized (Singleton3.class){
                if (uniqueInstance == null){
                    uniqueInstance = new Singleton3();
                }
            }
        }
        return uniqueInstance;
    }
}
