package com.chris.concurrency;

/*
volatile应用场景2：DCL（double-checked-locking）
 */
public class SingletonDemo {
    private volatile static SingletonDemo singletonDemo;

    public static SingletonDemo getInstance() {
        if (singletonDemo == null) {
            synchronized (SingletonDemo.class) {
                singletonDemo = new SingletonDemo();
            }
        }
        return singletonDemo;
    }
}
