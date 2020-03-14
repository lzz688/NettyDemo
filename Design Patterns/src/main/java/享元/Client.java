package 享元;

public class Client {
    public static void main(String[] args) {
        int extrinsic = 12;
        Flyweight flyweightX = FlyweightFactory.getFlyweight("X");
        flyweightX.operate(++ extrinsic);
        Flyweight flyweightY = FlyweightFactory.getFlyweight("Y");
        flyweightX.operate(++ extrinsic);
        Flyweight flyweightZ = FlyweightFactory.getFlyweight("Z");
        flyweightX.operate(++ extrinsic);
        Flyweight flyweightReX = FlyweightFactory.getFlyweight("x");
        flyweightX.operate(++ extrinsic);

        Flyweight unsharedFlyweight = new UnsharedConcreteFlyweight("X");
        unsharedFlyweight.operate(++ extrinsic);
    }
}
