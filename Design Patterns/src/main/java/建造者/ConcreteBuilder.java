package 建造者;

//具体建造者
public class ConcreteBuilder extends Builder{
    private Product product;
    public ConcreteBuilder(){
        product = new Product();
    }

    void buildA() {
        product.setBuildA("地基");
        System.out.println("地基");
    }

    void buildB() {
        product.setBuildB("钢筋工程");
        System.out.println("钢筋工程");
    }

    void buildC() {
        product.setBuildC("铺电线");
        System.out.println("铺电线");
    }

    void buildD() {
        product.setBuildD("粉刷");
        System.out.println("粉刷");
    }

    Product getProduct() {
        return product;
    }
}
