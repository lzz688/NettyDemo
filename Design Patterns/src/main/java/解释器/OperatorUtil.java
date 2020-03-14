package 解释器;

public class OperatorUtil {
    public static boolean isOperator(String symbol) {
        return (symbol.equals("+") || symbol.equals("*"));
    }

    public static Interpreter getExpressionObject(Interpreter firstExpression, Interpreter secondExpression, String symbol) {
        if ("+".equals(symbol)) {  // 加法
            return new AddInterpreter(firstExpression, secondExpression);
        } else if ("*".equals(symbol)) {    // 乘法
            return new MultiInterpreter(firstExpression, secondExpression);
        } else {
            throw new RuntimeException("不支持的操作符：" + symbol);
        }
    }
}