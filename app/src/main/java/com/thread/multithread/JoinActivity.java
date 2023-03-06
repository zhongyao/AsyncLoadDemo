package com.thread.multithread;

import android.app.Activity;

import android.os.Bundle;

import com.thread.asyncloaddemo.R;

public class JoinActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        doThreadTask();
    }

    private void doThreadTask() {
        //主线程X
        Thread threadX = new Thread(new Runnable() {
            public void run() {
                System.out.println("主线程X开始执行");
                //子线程A
                Thread threadA = new Thread(new ThreadA());
                //子线程B
                Thread threadB = new Thread(new ThreadB());
                threadA.start();
                threadB.start();
                try {
                    threadA.join();
                    threadB.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("主线程X执行结束");
            }
        });
        threadX.start();
    }


    public class ThreadA implements Runnable {
        public void run() {
            System.out.println("线程A开始执行");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("线程A异常了");
            }
            System.out.println("线程A执行结束");
        }
    }

    public class ThreadB implements Runnable {
        public void run() {
            System.out.println("线程B开始执行");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("线程B出异常了");
            }
            System.out.println("线程B执行结束");

        }
    }
}