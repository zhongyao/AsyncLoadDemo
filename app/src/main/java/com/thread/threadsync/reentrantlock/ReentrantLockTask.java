package com.thread.threadsync.reentrantlock;

import android.util.Log;

public class ReentrantLockTask implements Runnable {
    private static final String TAG = "ReentrantLockTask";
    private ReentrantLockBank bank;

    public ReentrantLockTask(ReentrantLockBank bank) {
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
