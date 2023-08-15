package com.cxy.mall.search.thread;

import java.util.concurrent.*;

public class ThreadTest {
    public static ExecutorService executorService = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("main start.....");
//        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
//            System.out.println("当前线程---" + Thread.currentThread().getId());
//            int i = 10 / 2;
//            System.out.println("运行结果----" + i);
//        }, executorService);

        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("当前线程---" + Thread.currentThread().getId());
            int i = 10 / 2;
            System.out.println("运行结果----" + i);
            return i;
        }, executorService);
        Integer integer = future.get();
        System.out.println(integer);
        System.out.println("main end....");
    }

    public static void thread(String[] args) throws ExecutionException, InterruptedException {
//        System.out.println("main start.....");
//        Thread01 thread01 = new Thread01();
//        thread01.start();
//        System.out.println("main end....");

//        System.out.println("main start.....");
//        Runable01 runable01 = new Runable01();
//        new Thread(runable01).start();
//        System.out.println("main end....");

//        System.out.println("main start.....");
//        FutureTask<Integer> futureTask = new FutureTask<>(new Callable01());
//        new Thread(futureTask).start();
//        Integer integer = futureTask.get();
//        System.out.println("main end...." + integer);

        System.out.println("main start.....");
//        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor();


        System.out.println("main end....");
    }

    public static class Thread01 extends Thread {
        @Override
        public void run() {
            System.out.println("当前线程---" + Thread.currentThread().getId());
            int i = 10 / 2;
            System.out.println("运行结果----" + i);
        }
    }

    public static class Runable01 implements Runnable {
        @Override
        public void run() {
            System.out.println("当前线程---" + Thread.currentThread().getId());
            int i = 10 / 2;
            System.out.println("运行结果----" + i);
        }
    }

    public static class Callable01 implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            System.out.println("当前线程---" + Thread.currentThread().getId());
            int i = 10 / 2;
            System.out.println("运行结果----" + i);
            return i;
        }
    }
}
