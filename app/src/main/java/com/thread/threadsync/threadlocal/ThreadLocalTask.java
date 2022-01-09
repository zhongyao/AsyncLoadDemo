package com.thread.threadsync.threadlocal;

import android.util.Log;

import com.thread.threadsync.synchronize.Bank;

/**
 * 创建线程任务
 */
public class ThreadLocalTask implements Runnable {

    private static final String TAG = "TaskRunnable";
    private ThreadLocalBank bank;

    public ThreadLocalTask(ThreadLocalBank bank) {
        this.bank = bank;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            bank.save(100);
            Log.d(TAG, "账户余额是---" + bank.getAccount());
        }
    }
}
