package 抽象工厂;

public class XiaomiRouter implements IRouterProduct {
    public void start() {
        System.out.println("打开小米路由器");
    }

    public void shutdown() {
        System.out.println("关闭小米路由器");
    }

    public void openwife() {
        System.out.println("打开小米wifi");
    }

    public void sendSMS() {
        System.out.println("小米设置");
    }
}
