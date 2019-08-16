package com.hc.admin;

import sun.awt.windows.ThemeReader;

import java.util.concurrent.*;

/**
 * @author ：xzyuan
 * @date ：Created in 2019/7/18 17:54
 * @description：
 * @version:
 */
public class demo {
    public static void main(String[] args) {
        //第一种方式
        ExecutorService executor = Executors.newCachedThreadPool();
        Task1 task1=new Task1();
        Task3 task3=new Task3();
        Task2 task2=new Task2();
        FutureTask<Integer> futureTask1 = new FutureTask<Integer>(task1);
        FutureTask<Integer> futureTask3=new FutureTask<Integer>(task3);
        FutureTask<Integer> futureTask2=new FutureTask<Integer>(task2);
        executor.submit(futureTask1);
        executor.submit(futureTask3);
        executor.submit(futureTask2);
        executor.shutdown();

        //第二种方式，注意这种方式和第一种方式效果是类似的，只不过一个使用的是ExecutorService，一个使用的是Thread
        /*Task task = new Task();
        FutureTask<Integer> futureTask = new FutureTask<Integer>(task);
        Thread thread = new Thread(futureTask);
        thread.start();*/

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

        System.out.println("主线程在执行任务");
        System.out.println(Thread.currentThread().getId());

        try {
            System.out.println("task1运行结果"+futureTask1.get());
            System.out.println("task2运行结果"+futureTask2.get());
            System.out.println("task3运行结果"+futureTask3.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("所有任务执行完毕");
    }

}
class Task1 implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("子线程1在进行计算");
        System.out.println(Thread.currentThread().getId());
        Thread.sleep(3000);
        int sum = 0;
        for(int i=0;i<100;i++) {
            sum += i;
        }
        return sum;
    }
}


class Task2 implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("子线程2在进行计算");
        System.out.println(Thread.currentThread().getId());
        Thread.sleep(3000);
        int sum = 0;
        for(int i=0;i<200;i++) {
            sum += i;
        }
        return sum;
    }
}
class Task3 implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("子线程3在进行计算");
        System.out.println(Thread.currentThread().getId());
        Thread.sleep(3000);
        int sum = 0;
        for(int i=0;i<300;i++) {
            sum += i;
        }
        return sum;
    }
}