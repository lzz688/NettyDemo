package 模版方法;

public class Tea extends CaffeineBeverage{
    void brew() {
        System.out.println("Tea.brew");
    }

    void addCondiments() {
        System.out.println("Tea.addCondiments");
    }
}
