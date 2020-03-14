package 责任链;

public class AbstractleaveHandlder {

    protected int min=1;//直接主管审批请假天数
    protected int middle=3;//部门经理处理的请假天数
    protected int max=30;//总经理处理的请假天数

    protected String handlerName;//领导名称

    protected AbstractleaveHandlder nextHandler;//下一个处理领导，更高级别

    //设置下一节点
    protected void setNextHandler(AbstractleaveHandlder handler){
        this.nextHandler=handler;
    }

    //处理请假的请求，子类实现
    protected void handlerRequest(leave request){

    }
}
