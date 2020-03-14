package 抽象工厂;

public class XiaomiFactory implements IProductFactory {
    public IphoneProduct iphoneProduct() {
        return new Xiaomiphone();
    }

    public IRouterProduct iRouterProduct() {
        return new XiaomiRouter();
    }
}
