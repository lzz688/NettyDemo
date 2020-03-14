package 原型;

import java.util.Date;

public class BiliBili {

    public static void main(String[] args) throws CloneNotSupportedException {
        //原型对象
        Date date = new Date();
        Video v1 = new Video("xxxx",date);
        Video v2 = (Video) v1.clone();
        //浅克隆，克隆后的对象和原本的对象是共有一个属性
        System.out.println("v1 " + v1);
        System.out.println("v2 " + v2);

        System.out.println("========================");
        date.setTime(215135135);
        //深克隆，克隆后的对象拥有自己的属性
        System.out.println("v1 " + v1);
        System.out.println("v2 " + v2);
    }
}
