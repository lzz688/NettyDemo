package 解释器;


//加法
public class AddInterpreter implements Interpreter {

    private Interpreter firstExpression , secondExpression;

    public AddInterpreter(Interpreter firstExpression,Interpreter secondExpression){
        this.firstExpression=firstExpression;
        this.secondExpression=secondExpression;
    }

    public int interpret(){
        return this.firstExpression.interpret() + this.secondExpression.interpret();
    }

    @Override
    public String toString() {
        return "+";
    }
}
