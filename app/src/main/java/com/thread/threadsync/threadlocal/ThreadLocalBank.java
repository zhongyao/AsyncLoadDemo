package com.thread.threadsync.threadlocal;
/**
 * 银行存款实例
 *
 * 1、相同：ThreadLocal与同步机制都是为了解决多线程中相同变量的访问冲突问题。
 * 2、差异：前者采用"空间换时间"的方式，或者采用"时间换空间"的方式。
 */
public class ThreadLocalBank {

    //使用ThreadLocal类管理共享变量account
    private static ThreadLocal<Integer> account = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return 100;
        }
    };

    public int getAccount() {
        return account.get();
    }

    public void save(int money) {
        account.set(account.get() + money);
    }

}
