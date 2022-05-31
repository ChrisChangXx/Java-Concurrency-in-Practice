package com.chris.concurrency;

public class IncreaseDemo {
    private static volatile int m = 0;

    private synchronized static void increase() {
        m++;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    increase();
                }
            }).start();
        }
        System.out.println(m);
    }
}
