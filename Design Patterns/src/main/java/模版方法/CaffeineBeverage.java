package 模版方法;

public abstract class CaffeineBeverage {
    final void prepareRectpe(){
        boilWater();
        brew();
        pourInCup();
        addCondiments();
    }
    abstract void brew();

    abstract void addCondiments();

    void boilWater() {
        System.out.println("boilWater");
    }

    void pourInCup() {
        System.out.println("pourInCup");
    }
}

