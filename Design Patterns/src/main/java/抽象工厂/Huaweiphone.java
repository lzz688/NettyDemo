package 抽象工厂;

public class Huaweiphone implements IphoneProduct {
    public void start() {
        System.out.println("开启华为手机");
    }

    public void shutdown() {
        System.out.println("关闭华为手机");
    }

    public void callup() {
        System.out.println("华为打电话");
    }

    public void sendSMS() {
        System.out.println("华为发短信");
    }
}
