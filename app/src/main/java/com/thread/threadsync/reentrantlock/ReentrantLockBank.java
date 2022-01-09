package com.thread.threadsync.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 银行存款实例
 */
public class ReentrantLockBank {

    private int account = 100;
    private ReentrantLock lock = new ReentrantLock();

    public int getAccount() {
        return account;
    }

    /**
     * 同步方法
     *
     * @param money
     */
    public void save(int money) {
        //获得锁
        lock.lock();
        try {
            this.account += money;
        } finally {
            //释放锁
            lock.unlock();
        }
    }

}
