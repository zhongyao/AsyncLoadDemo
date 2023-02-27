package com.thread.deadlock;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.thread.asyncloaddemo.R;

/**
 * 死锁通俗定义：
 * 死锁就是两个线程同时占用两个资源，但又在彼此等待对方释放锁
 *
 * 死锁的四个必要条件：
 * 1、互斥：共享资源 X 和 Y 只能被一个线程占用；
 * 2、占有且等待：线程 T1 已经取得共享资源 X，在等待共享资源 Y 的时候，不释放共享资源 X；
 * 3、不可抢占：其他线程不能强行抢占线程 T1 占有的资源；
 * 4、循环等待：线程 T1 等待线程 T2 占有的资源，线程 T2 等待线程 T1 占有的资源，就是循环等待。
 *
 * 如何预防死锁：
 * 因为要产生死锁，以上的4个条件必须同时满足，所以要防止死锁的话，只需要破坏其中一个条件即可。
 * 而互斥这个条件是没法破坏的，因为我们使用锁就是为了互斥，所以我们只能破坏剩下的3个条件：
 * 1、破坏占有且等待：理论上讲可以一次性申请所有资源。
 * 2、破坏不可抢占：核心就是主动释放占用的资源。
 * 3、破坏循环等待：指定加锁的先后顺序【可以按照资源序号大小指定】。
 */
public class DeadLockActivity extends Activity {

    private static final String TAG = "DeadLockActivity";
    public static final Object lock1 = new Object();
    public static final Object lock2 = new Object();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dead_lock);

        /**
         * 模拟产生死锁
         */
//        generateDeadLock();

        /**
         * 模拟解决死锁
         */
        resolveDeadLock();
    }

    private void generateDeadLock() {
        /**
         * 线程1拥有lock1之后试图获取lock2
         */
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    synchronized (lock1) {
                        Log.d(TAG, "线程1：" + Thread.currentThread().getName() + " 执行成功");
                        try {
                            Thread.sleep(1000);
                            synchronized (lock2) {
                                Log.d(TAG, Thread.currentThread().getName());
                                Thread.sleep(1000);
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        /**
         * 线程2拥有lock2之后试图获取lock1
         */
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    synchronized (lock2) {
                        Log.d(TAG, "线程2：" + Thread.currentThread().getName() + " 执行成功");
                        try {
                            Thread.sleep(1000);
                            synchronized (lock1) {
                                Log.d(TAG, Thread.currentThread().getName());
                                Thread.sleep(1000);
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        thread1.start();
        thread2.start();
    }


    /**
     * 只需要让thread1和thread2获取lock1和lock2的顺序相同即可解决上面的死锁问题
     */
    private void resolveDeadLock() {
        /**
         * 线程1拥有lock1之后试图获取lock2
         */
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    synchronized (lock1) {
                        Log.d(TAG, "线程1：" + Thread.currentThread().getName() + " 执行成功");
                        try {
                            Thread.sleep(1000);
                            synchronized (lock2) {
                                Log.d(TAG, Thread.currentThread().getName());
                                Thread.sleep(1000);
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        /**
         * 线程2拥有lock2之后试图获取lock1
         */
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    synchronized (lock1) {
                        Log.d(TAG, "线程2：" + Thread.currentThread().getName() + " 执行成功");
                        try {
                            Thread.sleep(1000);
                            synchronized (lock2) {
                                Log.d(TAG, Thread.currentThread().getName());
                                Thread.sleep(1000);
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}