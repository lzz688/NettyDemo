package 代理.Cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.awt.*;
import java.lang.reflect.Method;

public class CglibProxy implements MethodInterceptor {
    private Enhancer enhancer = new Enhancer();

    public Object getProxy(Class clazz){
        //设置需要创建子类的类
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        //通过字节码技术动态创建子类实例
        return enhancer.create();
    }

    //实现MethodInterceptor接口方法
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        prepare();
        Object result = methodProxy.invokeSuper(o,objects);
        clean();
        return result;
    }
    private void prepare(){
        System.out.println("CGLIB 准备碗筷！");
    }

    private void clean(){
        System.out.println("CGLIB 清洗碗筷！");
    }
}
