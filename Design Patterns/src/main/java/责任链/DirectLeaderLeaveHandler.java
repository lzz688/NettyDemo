package 责任链;

public class DirectLeaderLeaveHandler extends AbstractleaveHandlder {

    public DirectLeaderLeaveHandler(String name){
        this.handlerName = name;
    }

    @Override
    protected void handlerRequest(leave Request) {
        if (Request.getLeaveDays() <= this.min){
            System.out.println("直接主管 " + handlerName + "已经处理，流程结束");
            return;
        }

        if(null != this.nextHandler){
            this.nextHandler.handlerRequest(Request);
        }else {
            System.out.println("审批拒绝");
        }
    }
}
