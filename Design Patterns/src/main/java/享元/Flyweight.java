package 享元;

public abstract class Flyweight {

    //内部状态,享元对象共享内部状态
    public String intrinsic;
    //外部状态,每个享元对象的外部状态不同
    protected final String extrinsic;

    //要求享元角色必须接受的外部状态
    protected Flyweight(String extrinsic) {
        this.extrinsic = extrinsic;
    }
    //定义业务操作
    public abstract void operate(int extrinsic);

    public String getIntrinsic(){
        return intrinsic;
    }

    public void setIntrinsic(String intrinsic){
        this.intrinsic=intrinsic;
    }

}
