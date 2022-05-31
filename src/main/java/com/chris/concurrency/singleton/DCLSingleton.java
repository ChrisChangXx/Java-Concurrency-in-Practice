package com.chris.concurrency.singleton;

/*
double-check-locking
 */
public class DCLSingleton {
    private volatile static DCLSingleton instance = null;

    private DCLSingleton() {

    }

    public static DCLSingleton getInstance() {
        if (null == instance) {
            synchronized (DCLSingleton.class) {
                if (null == instance) {
                    instance = new DCLSingleton();
                }
            }
        }
        return instance;
    }
}
