package 责任链;

public class DeptManagerLeaveHandler extends AbstractleaveHandlder {

    public DeptManagerLeaveHandler(String name){
        this.handlerName = name;
    }

    @Override
    protected void handlerRequest(leave request) {
        if (request.getLeaveDays() > this.min && request.getLeaveDays() <= this.middle){
            System.out.println("部门经理" + handlerName + "已经处理，流程结束");
            return;
        }
        if (null != this.nextHandler){
            this.nextHandler.handlerRequest(request);
        }else {
            System.out.println("审批拒绝");
        }
    }
}
