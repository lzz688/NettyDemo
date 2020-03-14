package 观察者;


/*
*天气数据布告板会在天气信息发生改变时更新其内容，布告板有多个，
* 并且在将来会继续增加
* */
public interface Subject {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObserver();
}
