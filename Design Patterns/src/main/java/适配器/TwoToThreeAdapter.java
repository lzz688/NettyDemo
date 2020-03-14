package 适配器;

public class TwoToThreeAdapter implements ThreePower{

    private TwoPower twoPower;

    public TwoToThreeAdapter(TwoPower twoPower){
        this.twoPower=twoPower;
    }

    public void powerByThree() {
        System.out.println("借助组合适配器转化二项电");
        twoPower.powerByTwo();
    }
}
