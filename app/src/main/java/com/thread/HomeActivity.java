package com.thread;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.thread.asyncloaddemo.R;
import com.thread.asyncloaddemo.ThreadPoolActivity;
import com.thread.deadlock.DeadLockActivity;
import com.thread.multithread.FutureActivity;
import com.thread.multithread.JoinActivity;
import com.thread.threadsync.ThreadSyncActivity;

/**
 * 入口Activity
 */
public class HomeActivity extends Activity {

    private Button btnThreadPool, btnSync, deadLock, btnJoin, btnCountDownLatch, btnFutureTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnThreadPool = (Button) findViewById(R.id.btnThreadPool);
        btnSync = (Button) findViewById(R.id.btnSync);
        deadLock = (Button) findViewById(R.id.deadLock);
        btnJoin = (Button) findViewById(R.id.btnJoin);
        btnCountDownLatch = (Button) findViewById(R.id.btnCountDownLatch);
        btnFutureTask = (Button) findViewById(R.id.btnFutureTask);

        btnThreadPool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ThreadPoolActivity.class);
                startActivity(intent);
            }
        });

        btnSync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ThreadSyncActivity.class);
                startActivity(intent);
            }
        });

        deadLock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, DeadLockActivity.class);
                startActivity(intent);
            }
        });

        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, JoinActivity.class);
                startActivity(intent);
            }
        });

        btnCountDownLatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //参考RecyclerViewDemo
            }
        });

        btnFutureTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, FutureActivity.class);
                startActivity(intent);
            }
        });
    }
}