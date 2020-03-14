package 状态;

public class SwimState implements RunState {
    public void run(Hero hero) {
        System.out.println("---------不能跑动-----------");
        try{
            Thread.sleep(2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        hero.setState(Hero.COMMON);
        System.out.println("------眩晕状态结束，变成正常状态--");
    }
}
