package 简单工厂;


public class SimpleFactory {

    public Product createProduct(int type){
        if (type == 1 ){
            return new ConcreteProduct1();
        }else if (type == 2){
            return new ConcreteProduct2();
        }
        return new ConcreteProduct();
    }
}

class Client{
    public static void main(String[] args) {
        SimpleFactory simpleFactory = new SimpleFactory();
        Product product = simpleFactory.createProduct(0);
        Product product1 = simpleFactory.createProduct(1);
        Product product2 = simpleFactory.createProduct(2);

    }
}