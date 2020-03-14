package 状态;

public class SpeedUpState implements RunState {
    public void run(Hero hero) {
        System.out.println("------------加速跑动------------");
        try{
            Thread.sleep(2000);//加速持续4秒
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        hero.setState(Hero.COMMON);
        System.out.println("-----加速状态结束，变成正常状态-------");
    }
}
