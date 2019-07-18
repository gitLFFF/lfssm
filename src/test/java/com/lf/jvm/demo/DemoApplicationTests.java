package com.lf.jvm.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Test
    public void contextLoads() {
        String s1="abc";
        String s2="abc";
        System.out.println(s1==s2);
      String ss=  new String("dfdf");
        System.out.println(ss);
        System.out.println("this is my git");
    }

}
