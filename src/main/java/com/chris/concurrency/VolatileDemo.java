package com.chris.concurrency;

import java.util.concurrent.TimeUnit;

public class VolatileDemo {
    final static int MAX = 5;
    static volatile int INIT_VALUE = 0;

    public static void main(String[] args) {
        new Thread(() -> {
            int local_value = INIT_VALUE;
            while (local_value < MAX) {
                if (local_value != INIT_VALUE) {
                    System.out.println("Read:" + INIT_VALUE);
                    local_value = INIT_VALUE;
                }
            }
//            while (INIT_VALUE < MAX) {
//                if (INIT_VALUE < MAX) {
//                    System.out.println("Read:" + INIT_VALUE);
//                }
//            }
        }, "Reader").start();

        new Thread(() -> {
            int local_value = INIT_VALUE;
            while (local_value < MAX) {
                System.out.println("Update:" + (++local_value));
                INIT_VALUE = local_value;
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
//            while (INIT_VALUE < MAX) {
//                System.out.println("Update:" + (++INIT_VALUE));
//                try {
//                    TimeUnit.SECONDS.sleep(2);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
        }, "Updater").start();
    }
}
