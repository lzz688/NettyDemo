package 工厂方法;

import 简单工厂.ConcreteProduct1;
import 简单工厂.Product;

public class ConcreteFactory1 extends Factory{
    public Product factorymethod() {
        return new ConcreteProduct1();
    }
}
