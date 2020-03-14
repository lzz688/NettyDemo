package 解释器;



//非终结表达式，对整数进行解释
public class NumberInterpreter implements Interpreter {
    private int number;

    public NumberInterpreter(int number){
        this.number=number;
    }

    public NumberInterpreter(String number){
        this.number = Integer.parseInt(number);
    }

    public int interpret() {
        return this.number;
    }
}
