package 代理.动态;

import 代理.动态.Subject;

public class RealSubject implements Subject {
    public void eat() {
        System.out.println("吃饭");
    }
}
