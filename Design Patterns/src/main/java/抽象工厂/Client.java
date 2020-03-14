package 抽象工厂;

public class Client {
    public static void main(String[] args) {
        System.out.println("=======xiaomi========");
        IProductFactory xiaomiFactory = new XiaomiFactory();
        IphoneProduct iphoneProduct = xiaomiFactory.iphoneProduct();
        iphoneProduct.callup();
        iphoneProduct.sendSMS();

        System.out.println("=======huawei========");
        IProductFactory huaweiFactory = new HuaweiFactory();
        IphoneProduct iphoneProduct1 = huaweiFactory.iphoneProduct();
        iphoneProduct1.callup();
        iphoneProduct1.sendSMS();
    }
}
