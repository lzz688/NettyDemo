package 单例;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;


/*
* 该实现可以防止反射攻击。在其它实现中，通过 setAccessible() 方法可以将私有构造函数的访问级别设置为 public，
* 然后调用构造函数从而实例化对象，如果要防止这种攻击，
* 需要在构造函数中添加防止多次实例化的代码。
* 该实现是由 JVM 保证只会实例化一次，因此不会出现上述的反射攻击。
* */
//枚举
public enum Singleton5 {

    INSTANCE;

    private String objName;

    public String getObjName() {
        return objName;
    }

    public void setObjName(String objName) {
        this.objName = objName;
    }

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //单例测试
        Singleton5 firstSingleton = Singleton5.INSTANCE;
        firstSingleton.setObjName("firstname");
        System.out.println(firstSingleton.getObjName());
        Singleton5 secondSingleton = Singleton5.INSTANCE;
        secondSingleton.setObjName("secondname");
        System.out.println(firstSingleton.getObjName());
        System.out.println(secondSingleton.getObjName());

        Constructor<Singleton5> declaredConstructor =
                Singleton5.class.getDeclaredConstructor(String.class,int.class);

        declaredConstructor.setAccessible(true);

        Singleton5 instance = declaredConstructor.newInstance();

        System.out.println(instance);
    }
}
