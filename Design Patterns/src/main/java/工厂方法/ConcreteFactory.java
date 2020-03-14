package 工厂方法;

import 简单工厂.ConcreteProduct;
import 简单工厂.Product;

public class ConcreteFactory extends Factory{
    public Product factorymethod() {
        return new ConcreteProduct();
    }
}
