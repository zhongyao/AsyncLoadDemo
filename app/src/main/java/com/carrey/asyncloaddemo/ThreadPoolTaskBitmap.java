package com.carrey.asyncloaddemo;

import android.graphics.Bitmap;
import android.os.Process;
import com.carrey.customview.customview.CustomView;

/**
 * 图片加载的任务单元
 *
 * @author hongri
 */
public class ThreadPoolTaskBitmap extends ThreadPoolTask {

    private CallBack callBack;

    private CustomView view;

    private int position;

    public ThreadPoolTaskBitmap(String url, CallBack callBack, int position, CustomView view) {
        super(url);
        this.callBack = callBack;
        this.position = position;
        this.view = view;
    }

    @Override
    public void run() {
        Process.setThreadPriority(Process.THREAD_PRIORITY_LOWEST);

        Bitmap bitmap = ImageHelper.loadBitmapFromNet(url);

        if (callBack != null) {
            callBack.onReady(url, bitmap, this.position, this.view);
        }
    }

    public interface CallBack {
        void onReady(String url, Bitmap bitmap, int position, CustomView view);
    }
}
