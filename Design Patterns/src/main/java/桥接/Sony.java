package 桥接;

public class Sony extends TV {
    public void on() {
        System.out.println("Sony.on");
    }

    public void off() {
        System.out.println("Sony.off");
    }

    public void tuneChannel() {
        System.out.println("Sony.tuneChannel");
    }
}
