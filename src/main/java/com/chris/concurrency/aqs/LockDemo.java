package com.chris.concurrency.aqs;

import java.util.concurrent.TimeUnit;

public class LockDemo {

    private int m = 0;

    public int next() {
        try {
            TimeUnit.SECONDS.sleep(2);
            return m++;
        } catch (InterruptedException e) {
            throw new RuntimeException("ERROR");
        }
    }

    public static void main(String[] args) {
        LockDemo lockDemo = new LockDemo();
        Thread[] th = new Thread[20];
        for (int i = 0; i < 20; i++) {
            th[i] = new Thread(() -> {
                System.out.println(lockDemo.next());
            });
            th[i].start();
        }
    }
}
