package com.thread.threadsync;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.thread.asyncloaddemo.R;
import com.thread.threadsync.synchronize.Bank;
import com.thread.threadsync.synchronize.TaskRunnable;

/**
 * 线程同步的几种方式
 * 参考: https://www.cnblogs.com/jtlgb/p/11864607.html
 */
public class ThreadSyncActivity extends Activity implements View.OnClickListener {

    private final String TAG = "ThreadSyncActivity";
    private Button btnSync1, btnSync2, btnSync3, btnSync4, btnSync5, btnSync6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_sync);

        btnSync1 = (Button) findViewById(R.id.btnSync1);

        btnSync1.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btnSync1:

                Bank bank = new Bank();
                TaskRunnable task = new TaskRunnable(bank);

                Thread thread1 = new Thread(task);
                thread1.start();
                Log.d(TAG, "线程1");

                Thread thread2 = new Thread(task);
                thread2.start();
                Log.d(TAG, "线程1");

                break;
            default:
                break;
        }
    }
}