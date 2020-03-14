package 工厂方法;

import 简单工厂.Product;

public abstract class Factory {
    abstract public Product factorymethod();
    public void dosomething(){
        Product product = factorymethod();
        System.out.println("创建成功");
    }
}
