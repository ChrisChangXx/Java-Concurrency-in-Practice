package com.chris.concurrency.cas;

import java.util.concurrent.atomic.AtomicInteger;

public class Cas1 {
    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    public static void increase(){
        atomicInteger.getAndIncrement();
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[20];
        for (int i = 0; i < 20; i++) {
            threads[i]=new Thread(Cas1::increase);
            threads[i].start();
            threads[i].join();//join方法加入线程组 使线程具有交互性
        }
        System.out.println(atomicInteger);
    }
}
