package com.thread.threadsync.synchronize;

/**
 * 银行存款实例
 */
public class Bank {

    private int account = 100;

    public int getAccount() {
        return account;
    }

    /**
     * 同步方法
     * @param money
     */
    public synchronized void save(int money) {
        this.account += money;
    }

    public void save2(int money) {
        /**
         * 同步代码块
         */
        synchronized (this) {
            this.account += money;
        }
    }
}
