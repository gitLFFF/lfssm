package com.lf.jvm.demo.desgin;

public class desgin01 {

    public static void main(String[] args) {
        A aa=new A();
        aa.yb(new C());
    }
}

interface B{
    void sys();
}
class A{

    void yb(B b){
     b.sys();
    }
}

class C implements B{
    @Override
    public void sys() {
        System.out.println("A通过接口B依赖C");
    }
}