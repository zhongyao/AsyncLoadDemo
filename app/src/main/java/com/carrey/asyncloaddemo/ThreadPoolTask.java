package com.carrey.asyncloaddemo;

/**
 * 任务单元
 *
 * @author hongri
 */
public abstract class ThreadPoolTask implements Runnable {

    protected String url;

    public ThreadPoolTask(String url) {
        this.url = url;
    }

    @Override
    public abstract void run();

    public String getURL() {
        return this.url;
    }
}
