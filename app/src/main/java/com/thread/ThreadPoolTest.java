package com.thread;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import android.util.Log;

/**
 * @author zhongyao
 * @date 2018/8/16
 *
 * 线程池的四种分类：
 * FixedThreadPool
 * CachedThreadPool
 * ScheduledThreadPool
 * SingleThreadExecutor
 *
 * 但一般建议使用ThreadPoolExecutor来根据需要灵活的开启线程池。
 *
 */

public class ThreadPoolTest {
    private static final String TAG = "yao";

    /**
     * CPU数
     */
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();

    /**
     * 核心线程数
     */
    private static final int CORE_POOL_SIZE = CPU_COUNT + 1;

    /**
     * 最大线程数
     */
    private static final int MAXIMUM_POOL_SIZE = CPU_COUNT * 2 + 1;

    /**
     * 非核心线程闲置时的超时时长，超过这个时长，非核心线程就会被回收
     */
    private static final int KEEP_ALIVE_TIME = 1;

    private static final ThreadFactory mThreadFactory = new ThreadFactory() {

        private final AtomicInteger mCount = new AtomicInteger();

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r + "AsyncTask#" + mCount.getAndIncrement());
        }
    };

    private static final BlockingDeque<Runnable> sPoolWorkQueue = new LinkedBlockingDeque<>(128);

    ThreadPoolExecutor THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE_TIME,
        TimeUnit.SECONDS, sPoolWorkQueue);

    Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            try {
                Log.d(TAG, "执行Start");
                Thread.sleep(2000);
                Log.d(TAG, "执行finish");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    /**
     * 线程池的分类可以分为如下四种(不过不建议直接使用如下这几种方式，建议使用ThreadPoolExecutor直接创建线程池)：
     * 1、FixedThreadPool：线程数量固定，且都是核心线程数，这些核心线程空闲并不会被回收。
     * 2、CachedThreadPool：没有核心线程数，非核心线程数可以任意大，线程池中的空闲线程超过60s就会被回收；比较适合执行大量的耗时较少的任务。
     * 3、ScheduledThreadPool：核心线程数量固定，非核心线程数量可以任意大，并且非核心线程数闲置时会被立刻回收；这类线程池主要用于执行定时任务或者有固定周期的重复任务。
     * 4、SingleThreadExecutor：线程池内容只有一个核心线程，确保所有的任务都在同一个线程池中按顺序执行。
     */
    private ExecutorService mFixedThreadPool = Executors.newFixedThreadPool(3);

    private ExecutorService mCachedThreadPool = Executors.newCachedThreadPool();

    private ScheduledExecutorService mScheduledThreadPool = Executors.newScheduledThreadPool(3);

    private ExecutorService mSingeThreadExecutor = Executors.newSingleThreadExecutor();

    public void doTask() {
        mFixedThreadPool.execute(mRunnable);

        mCachedThreadPool.execute(mRunnable);

        //延期2000ms执行（一次)
        mScheduledThreadPool.schedule(mRunnable, 2000, TimeUnit.MILLISECONDS);
        //延期10ms后开始（周期）执行，每两次执行间隔是1000ms
        mScheduledThreadPool.scheduleWithFixedDelay(mRunnable, 10, 1000, TimeUnit.MILLISECONDS);
        //延期10ms后开始（周期）执行，每次执行周期是2000ms
        mScheduledThreadPool.scheduleAtFixedRate(mRunnable, 10, 2000, TimeUnit.MILLISECONDS);

        mSingeThreadExecutor.execute(mRunnable);
    }

}
