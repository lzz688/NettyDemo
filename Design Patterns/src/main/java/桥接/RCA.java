package 桥接;

public class RCA extends TV {
    public void on() {
        System.out.println("RCA.on");
    }

    public void off() {
        System.out.println("RCA.off");
    }

    public void tuneChannel() {
        System.out.println("RCA.tuneChannel");
    }
}
