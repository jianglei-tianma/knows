package com.tianma.java;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;

@SpringBootTest
class JavaApplicationTests {

    @Test
    void contextLoads() {
    }


    @Test
    void testTryCatch() {


        //1个字节=8位
        try {
            System.out.println("a(UTF-8)    ：" + "1bnc".getBytes("UTF-8").length);
            System.out.println("a(UTF-8)    ：" + "a".getBytes("UTF-8").length);
            System.out.println("aa(UTF-8)   ：" + "aa".getBytes("UTF-8").length);
            System.out.println("啊(UTF-8)   ：" + "啊".getBytes("UTF-8").length);
            System.out.println("啊啊(UTF-8) ：" + "啊啊".getBytes("UTF-8").length);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        String str = "ABC";
        String tem = "";
        char[] c = str.toCharArray();
        for (int i = 0; i < c.length; i++) {
            tem = tem + Integer.toBinaryString(c[i]) + " ";
        }
        System.out.println("二进制===>>" + tem);
        for (String s : tem.split(" ")) {
            BigInteger big = new BigInteger(s, 2);
            int x = big.intValue();
            System.out.println("二进制===>>" + s + ", valueOf()==>>" + String.valueOf(((char) x)));


        }
    }


}
