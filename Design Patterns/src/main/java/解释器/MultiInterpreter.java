package 解释器;

import javax.swing.*;


//乘法
public class MultiInterpreter implements Interpreter {
    private Interpreter firstExpression, secondExpression;

    public MultiInterpreter(Interpreter firstExpression, Interpreter secondExpression) {
        this.firstExpression = firstExpression;
        this.secondExpression = secondExpression;
    }
    public int interpret() {
        return this.firstExpression.interpret() * this.secondExpression.interpret();
    }
    @Override
    public String toString() {
        return "*";
    }
}
