package com.chris.concurrency;

/*
volatile应用场景：1.状态标志（开关模式）
 */
public class ShutdownDemo extends Thread {

    private volatile boolean flag = false;

    @Override
    public void run() {
        while (flag) {
            System.out.println("working");
        }
    }

    public void shutdown() {
        flag = false;
    }

    public void starts() {
        flag = true;
    }
}
