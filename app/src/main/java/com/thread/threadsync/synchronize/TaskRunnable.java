package com.thread.threadsync.synchronize;

import android.util.Log;

/**
 * 创建线程任务
 */
public class TaskRunnable implements Runnable {

    private static final String TAG = "TaskRunnable";
    private Bank bank;

    public TaskRunnable(Bank bank) {
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
