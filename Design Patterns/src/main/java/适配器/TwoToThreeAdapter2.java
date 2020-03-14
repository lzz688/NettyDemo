package 适配器;

public class TwoToThreeAdapter2 extends TwoPower implements ThreePower {
    public void powerByThree() {
        System.out.println("借助继承适配器转化二项电");
        this.powerByTwo();
    }
}
