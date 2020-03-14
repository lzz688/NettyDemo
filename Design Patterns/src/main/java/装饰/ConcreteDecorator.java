package 装饰;

public class ConcreteDecorator extends Decorator {
    public ConcreteDecorator(Component component) {
        super(component);
    }

    // 定义自己的修饰逻辑
    private void decorateMethod(){
        // do somethind ...
    }

    // 重写父类的方法
    public void cost(){
        this.decorateMethod();
        super.cost();
    }
}
