package zzzDubboRPC.Provider;

import zzzDubboRPC.publicinterface.HelloService;

public class HelloServiceImpl implements HelloService {
    //有消费方调用该方法时就返回一个字符串

    private int count=0;//计算，但每次发消息时,都是直接new一个新的HelloServiceImpl，不会累计
                        //若想累计，则添加static关键字
    @Override
    public String hello(String mes) {
        System.out.println("收到了客户端消息=" + mes);
        //根据mes，返回不同的结果
        if (mes != null){
            return "你好客户端，我已经收到了你的消息 [" + mes + "] 第" + (++this.count) +"";
        }else{
            return "你好客户端，我没有收到你的消息";
        }

    }
}
