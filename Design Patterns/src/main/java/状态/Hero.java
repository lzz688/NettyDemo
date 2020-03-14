package 状态;

public class Hero {
    public static final RunState COMMON = new CommonState();//正常状态

    public static final RunState SPEED_UP = new SpeedUpState();//加速状态

    public static final RunState SPEED_DOWN = new SpeedDownState();//减速状态

    public static final RunState SWIM = new SwimState();//眩晕状态

    private RunState state = COMMON;//默认是正常状态

    private Thread runThread;//跑动线程

    //设置状态
    public void setState(RunState state) {
        this.state = state;
    }
    //停止跑动
    public void stopRun() {
        if (isRunning()) runThread.interrupt();
        System.out.println("--------------停止跑动---------------");
    }
    //开始跑动
    public void startRun() {
        if (isRunning()) {
            return;
        }
        final Hero hero = this;
        runThread = new Thread(new Runnable() {
            public void run() {
                while (!runThread.isInterrupted()) {
                    state.run(hero);
                }
            }
        });
        System.out.println("--------------开始跑动---------------");
        runThread.start();
    }

    private boolean isRunning(){
        return runThread != null && !runThread.isInterrupted();
    }
}
