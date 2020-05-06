package com.lei.knows;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class KnowsCommonApplicationTests {

    @Test
    void contextLoads() {


        B b = new B();

    }



}
class A{
    public A(){

        System.out.println("父构造器1");
    }
    public static  String print(String msg){
        System.out.println(msg);
        return msg;
    }

    static String name=print("父哈哈哈");
    static{
        System.out.println("父静态初始化块");
    }






    String info=print("父嘿嘿嘿1");
    {
        System.out.println("父普通初始化块");
    }
}class B extends A{

    public B(){

        System.out.println("构造器2");
    }
    public static  String out(String msg){
        System.out.println(msg);
        return msg;

    }
    {
        System.out.println("子类普通初始化块");
    }
    static{

        System.out.println("子类静态初始化块");
    }

    static String name=out("嘎嘎嘎");
    String info=out("嘿嘿嘿2");

}

