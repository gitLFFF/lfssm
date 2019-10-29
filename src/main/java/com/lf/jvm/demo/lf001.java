package com.lf.jvm.demo;

import java.util.ArrayList;
import java.util.Iterator;

public class lf001 {
    private String str;

    public void sys() {
        System.out.println(str = "ddffde");

    }

    public static void main(String[] args) {
        ArrayList<Object> a = new ArrayList<>();
        a.add("ddd");
        a.add("jack");
        Iterator<Object> iterator = a.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

    }

}
