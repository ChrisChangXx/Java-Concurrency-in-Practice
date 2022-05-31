package com.chris.concurrency;

import java.util.concurrent.TimeUnit;

public class SynchronizedDemo {

    //1.同步非静态
    public synchronized void access1() {
        try {
            TimeUnit.MINUTES.sleep(2);
            System.out.println(Thread.currentThread().getName() + " is running");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //2.同步静态
    public synchronized static void access2() {
        try {
            TimeUnit.SECONDS.sleep(2);
            System.out.println(Thread.currentThread().getName() + " is running");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //3.同步代码块（对象）
    public void access3() {
        synchronized (this) {
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName() + " is running");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //4.同步代码块（类）
    public void access4() {
        synchronized (SynchronizedDemo.class) {
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName() + " is running");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        /*for (int i = 0; i < 5; i++) {
            new Thread(SynchronizedDemo::access2).start();
        }*/

        final SynchronizedDemo synchronizedDemo = new SynchronizedDemo();
        for (int i = 0; i < 5; i++) {
            new Thread(synchronizedDemo::access1).start();
        }
    }
}
