package 状态;

public class SpeedDownState implements RunState {
    public void run(Hero hero) {
        System.out.println("-------减速跑动----------");
        try{
            Thread.sleep(2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        hero.setState(Hero.COMMON);
        System.out.println("------减速状态结束，变成正常状态------");
    }
}
