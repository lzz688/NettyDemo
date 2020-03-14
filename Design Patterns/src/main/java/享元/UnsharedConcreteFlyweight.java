package 享元;


//指那些不需要共享的Flyweight子类。
public class UnsharedConcreteFlyweight extends Flyweight {

    protected UnsharedConcreteFlyweight(String extrinsic) {
        super(extrinsic);
    }

    public void operate(int extrinsic) {
        System.out.println("不共享的具体Flyweight" + extrinsic);
    }
}
