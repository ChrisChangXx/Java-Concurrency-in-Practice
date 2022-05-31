package com.chris.concurrency.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

public class Cas2 {
    private static AtomicInteger atomicInteger = new AtomicInteger(100);
    private static AtomicStampedReference atomicStampedReference = new AtomicStampedReference(100, 1);

    public static void main(String[] args) {
        new Thread(() -> System.out.println(Thread.currentThread().getName() + ":" + atomicInteger.compareAndSet(100, 110)), "thread1").start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ":" + atomicInteger.compareAndSet(110, 100));
        }, "thread2").start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ":" + atomicInteger.compareAndSet(100, 120));
        }, "thread3").start();

        System.out.println("================================");

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ":" + atomicStampedReference.compareAndSet(100, 110, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1));
            System.out.println(Thread.currentThread().getName() + ":" + atomicStampedReference.compareAndSet(110, 100, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1));
        }, "thead4").start();

        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ":" + atomicStampedReference.compareAndSet(100, 120, stamp, stamp + 1));
        }, "thead5").start();
    }
}
