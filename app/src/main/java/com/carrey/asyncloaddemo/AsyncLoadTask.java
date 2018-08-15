package com.carrey.asyncloaddemo;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Pair;
import com.carrey.customview.customview.CustomView;

/**
 * @author hongri
 * execute 串行执行任务
 * executeOnExecutor 并行执行任务
 */
public class AsyncLoadTask extends AsyncTask<Integer, Void, Pair<Integer, Bitmap>> {

    private static final String TAG = "AsyncLoadTask";

    /**
     * 要刷新的view
     */
    private CustomView view;

    public AsyncLoadTask(CustomView view) {
        this.view = view;
    }

    /**
     * 在主线程中执行，一般做一些准备工作
     */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    /**
     * 在线程池中执行，此方法用于执行异步任务
     * @param params
     * @return
     */
    @Override
    protected Pair<Integer, Bitmap> doInBackground(Integer... params) {
        int position = params[0];
        String imageUrl = ImageHelper.getImageUrl(MainActivity.webServerStr, position);
        Bitmap bitmap = ImageHelper.loadBitmapFromNet(imageUrl);
        return new Pair<>(position, bitmap);
    }

    /**
     * 在主线程中执行，当publishProgress方法被调用时，该方法被调用。
     * @param values
     */
    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    /**
     * 在主线程中执行，在异步任务执行完之后，此方法会被调用。
     * @param result
     */
    @Override
    protected void onPostExecute(Pair<Integer, Bitmap> result) {
        if (result.first == view.position) {
            view.setImageBitmap(result.second);
        }
    }

    /**
     * 主线程中执行：
     * 当该方法被调用时，onPostExecute方法便不会被调用
     */
    @Override
    protected void onCancelled() {
        super.onCancelled();
    }
}
