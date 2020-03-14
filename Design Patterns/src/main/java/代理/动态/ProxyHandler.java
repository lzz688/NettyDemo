package 代理.动态;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyHandler implements InvocationHandler {

    private Subject subject=null;

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Object getproxy(){
        return Proxy.newProxyInstance(this.getClass().getClassLoader(),
                subject.getClass().getInterfaces(),this);
    }
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        prepare();
        Object result = method.invoke(subject,args);
        clean();
        return result;
    }
    private void prepare(){
        System.out.println("准备碗筷");
    }
    private void clean(){
        System.out.println("清洗碗筷");
    }
}
