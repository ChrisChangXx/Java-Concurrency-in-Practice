package com.chris.concurrency.singleton;

/*
饿汉式
 */
public class HungrySingleton {
    //线程安全的，加载的时候就产生实例对象，ClassLoader
    private static HungrySingleton instance = new HungrySingleton();

    private HungrySingleton() {

    }

    private static HungrySingleton getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                System.out.println(HungrySingleton.getInstance());
            }).start();
        }
    }
}
