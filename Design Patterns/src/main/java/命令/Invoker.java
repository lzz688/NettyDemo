package 命令;


//遥控器
public class Invoker {
    private Command[] onCommand;
    private Command[] offCommand;
    private final int slotNum = 7;

    public Invoker(){
        this.onCommand = new Command[slotNum];
        this.offCommand = new Command[slotNum];
    }

    public void setOnCommand(Command command,int slot){
        onCommand[slot] = command;
    }

    public void setOffCommand(Command command,int slot){
        offCommand[slot] = command;
    }

    public void onButtonWasPushed(int slot){
        onCommand[slot].execute();
    }

    public void offButtonWasPushed(int slot){
        offCommand[slot].execute();
    }
}
