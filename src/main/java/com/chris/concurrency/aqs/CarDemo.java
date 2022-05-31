package com.chris.concurrency.aqs;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * semaphore有限资源共享
 */
public class CarDemo {
    public static void main(String[] args) {
        //创建Semaphore
        Semaphore semaphore = new Semaphore(5);

        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep(2);
                    //请求许可
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "可以进停车场");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //使用资源
                try {
                    TimeUnit.SECONDS.sleep(new Random().nextInt(10));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //释放资源
                semaphore.release();
                System.out.println(Thread.currentThread().getName() + "离开停车场");
            });
            threads[i].start();
        }
    }
}
