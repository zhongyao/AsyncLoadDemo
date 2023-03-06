package com.thread.multithread;


import android.app.Activity;
import android.os.Bundle;

import com.thread.asyncloaddemo.R;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_future);

        try {
            doThreadTask();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static class FutureTaskThread implements Callable {
        private String threadName;

        public FutureTaskThread(String threadName) {
            this.threadName = threadName;
        }

        public Object call() throws Exception {
            Thread.sleep(3000);
            return "线程" + threadName + "执行结束了";
        }
    }

    public void doThreadTask() throws ExecutionException, InterruptedException {
        System.out.println("主线程开始等待子线程执行完成");
        FutureTask taskA = new FutureTask(new FutureTaskThread("A"));
        FutureTask taskB = new FutureTask(new FutureTaskThread("B"));
        new Thread(taskA).start();
        new Thread(taskB).start();
        //判断子线程A是否执行结束
        if (!taskA.isDone()) {
            System.out.println("线程A未执行完，主线程继续等待");
        }
        //判断子线程B是否执行结束
        if (!taskB.isDone()) {
            System.out.println("线程B未执行完，主线程继续等待");
        }
        //打印一下线程A的返回值
        //【调用 get() 方法获取计算结果 , 如果计算没有完成 , 该方法会阻塞 ,
        // 直到计算完成之后 , 阻塞才会解除 , 同时返回执行结果】
        System.out.println(taskA.get());
        //打印一下线程B的返回值
        System.out.println(taskB.get());
        System.out.println("子线程执行完成了，主线程开始执行了");
    }
}