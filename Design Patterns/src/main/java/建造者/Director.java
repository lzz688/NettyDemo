package 建造者;

//指挥者
public class Director {

    //指挥工人按顺序造房子
    public Product create(Builder builder) {
        builder.buildA();
        builder.buildB();
        builder.buildC();
        builder.buildD();


        return builder.getProduct();
    }
}
