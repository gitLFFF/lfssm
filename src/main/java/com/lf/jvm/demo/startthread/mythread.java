package com.lf.jvm.demo.startthread;

public class mythread extends Thread {
    private int a = 10;

    @Override
  synchronized   public void run() {
       a= a-1;
        System.out.println(currentThread().getName() + "==" + a);
    }
}

class test {
    public static void main(String[] args) {
        mythread mythread = new mythread();
        mythread mythread1 = new mythread();
        mythread mythread2 = new mythread();
        mythread mythread3 = new mythread();
        mythread mythread4 = new mythread();
        mythread mythread5 = new mythread();
        mythread.start();
        mythread1.start();
        mythread2.start();
        mythread3.start();
        mythread4.start();
        mythread5.start();


    }
}