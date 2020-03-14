package 享元;



//继承Flyweight超类或实现Flyweight接口，并为其内部状态增加存储空间。
public class ConcreteFlyweight extends Flyweight {
    ////接受外部状态
    protected ConcreteFlyweight(String extrinsic) {
        super(extrinsic);
    }

    //根据外部状态进行逻辑处理
    public void operate(int extrinsic) {
        System.out.println("具体Flyweight:" + extrinsic);
    }
}
