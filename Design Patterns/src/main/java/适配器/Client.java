package 适配器;


/*
* 背景：买了一个进口笔记本电脑
  冲突：笔记本电脑需要的三项电源，和只提供的二项电源冲突
  解决方案：设置一个适配器二项充电口转化为三项充电口
* */
public class Client {

    private ThreePower threePower;
    public Client(ThreePower threePower){
        this.threePower=threePower;
    }
    public static void main(String[] args) {
        System.out.println("-----------类适配器---------");
        ThreePower threePower1=new TwoToThreeAdapter2();
        Client client1 = new Client(threePower1);
        client1.recharge();
        client1.work();

        System.out.println("-----------对象适配器-----------");
        TwoPower twoPower = new TwoPower();
        ThreePower threePower = new TwoToThreeAdapter(twoPower);
        Client client = new Client(threePower);
        client.recharge();
        client.work();
    }
    public void work() {
        System.out.println("笔记本电脑开始工作!");
    }

    public void recharge() {
        // 使用三项充电
        threePower.powerByThree();
    }
}
