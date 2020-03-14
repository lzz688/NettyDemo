package 抽象工厂;

public class HuaweiRouter implements IRouterProduct {
    public void start() {
        System.out.println("打开华为路由器");
    }

    public void shutdown() {
        System.out.println("关闭华为路由器");
    }

    public void openwife() {
        System.out.println("打开华为wifi");
    }

    public void sendSMS() {
        System.out.println("华为设置");
    }
}
