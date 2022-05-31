package com.chris.concurrency.aqs;

import java.util.concurrent.locks.ReentrantLock;

@SuppressWarnings("Duplicates")
public class LockDemo02 {

    private MyLock lock = new MyLock();
    //可重入锁
    private ReentrantLock reentrantLock = new ReentrantLock();

    private int m = 0;

    public void a() {
        lock.lock();
        System.out.println("a");
        b();
        lock.unlock();
    }

    public void b() {
        lock.lock();
        System.out.println("b");
        lock.unlock();
    }

    public static void main(String[] args) {
        LockDemo02 lockDemo = new LockDemo02();
        new Thread(()->{
            lockDemo.a();
        }).start();
    }
}
