package com.chris.concurrency.aqs;

@SuppressWarnings("Duplicates")
public class LockDemo01 {

    private MyLock lock = new MyLock();

    private int m = 0;

    private int next() {
        try {
            lock.lock();
            return m++;
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        LockDemo01 lockDemo = new LockDemo01();
        Thread[] th = new Thread[20];
        for (int i = 0; i < 20; i++) {
            th[i] = new Thread(() -> {
                System.out.println(lockDemo.next());
            });
            th[i].start();
        }
    }
}
