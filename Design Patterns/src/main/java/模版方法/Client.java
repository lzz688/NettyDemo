package 模版方法;

public class Client {
    public static void main(String[] args) {
        CaffeineBeverage caffeineBeverage= new Coffee();
        caffeineBeverage.prepareRectpe();
        System.out.println("--------------");
        caffeineBeverage=new Tea();
        caffeineBeverage.prepareRectpe();
    }
}
