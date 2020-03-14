package 工厂方法;

import 简单工厂.ConcreteProduct2;
import 简单工厂.Product;

public class ConcreteFactory2 extends Factory{
    public Product factorymethod() {
        return new ConcreteProduct2();
    }
}
