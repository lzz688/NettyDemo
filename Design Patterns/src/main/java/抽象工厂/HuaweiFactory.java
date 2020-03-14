package 抽象工厂;

public class HuaweiFactory implements IProductFactory {
    public IphoneProduct iphoneProduct() {
        return new Huaweiphone();
    }

    public IRouterProduct iRouterProduct() {
        return new HuaweiRouter();
    }
}
