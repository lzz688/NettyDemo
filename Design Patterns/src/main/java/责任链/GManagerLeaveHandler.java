package 责任链;

public class GManagerLeaveHandler extends AbstractleaveHandlder {
    public GManagerLeaveHandler(String name){
        this.handlerName = name;
    }

    @Override
    protected void handlerRequest(leave request) {
        if (request.getLeaveDays() > this.middle && request.getLeaveDays() <= this.max){
            System.out.println("总经理:" + handlerName + "已经处理，流程结束");
            return;
        }

        if (null != nextHandler){
            this.nextHandler.handlerRequest(request);
        }else {
            System.out.println("审批拒绝");
        }


    }
}
