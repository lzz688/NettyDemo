package 策略;

//Context上下文角色，也叫Context封装角色，起承上启下的作用，
// 屏蔽高层模块对策略、算法的直接访问，封装可能存在的变化。
public class Context {

    Strategy strategy;

    public Context(Strategy strategy){
        this.strategy=strategy;
    }

    //上下文接口
    public void contextInterface(){
        strategy.algorithminterface();
    }
}
