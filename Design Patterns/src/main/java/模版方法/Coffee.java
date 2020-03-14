package 模版方法;

public class Coffee extends CaffeineBeverage {
    void brew() {
        System.out.println("Coffee.brew");
    }

    void addCondiments() {
        System.out.println("Coffee.addCondiments");
    }
}
