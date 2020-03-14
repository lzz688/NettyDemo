package 责任链;

public class ResponsibilityTest {

    public static void main(String[] args){
        leave request=leave.builder().leaveDays(20).name("lzz").build();

        DirectLeaderLeaveHandler directLeaderLeaveHandler = new DirectLeaderLeaveHandler("县令");
        DeptManagerLeaveHandler deptManagerLeaveHandler = new DeptManagerLeaveHandler("知府");
        GManagerLeaveHandler gManagerLeaveHandler = new GManagerLeaveHandler("府尹");

        directLeaderLeaveHandler.setNextHandler(deptManagerLeaveHandler);
        deptManagerLeaveHandler.setNextHandler(gManagerLeaveHandler);
        directLeaderLeaveHandler.handlerRequest(request);
    }
}
