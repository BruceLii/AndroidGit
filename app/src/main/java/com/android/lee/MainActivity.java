package com.android.lee;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Process;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.android.lee.animations.AnimListActivity;
import com.android.lee.service.IMService;

public class MainActivity extends Activity implements View.OnClickListener {
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.e("onServiceConnected", " ***");
            IMService.MyBinder myBinder = (IMService.MyBinder) iBinder;
            Toast.makeText(MainActivity.this, "服务端整数：" + myBinder.getService().getRandomNum() + "", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.e("onServiceDisconnected", " ***");
            Toast.makeText(MainActivity.this, "onServiceDisconnected", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        MobileAds.initialize(this, getString(R.string.ad_app_ID));
//
//        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);


        findViewById(R.id.button).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(this);
        findViewById(R.id.button4).setOnClickListener(this);


//        findViewById(R.id.button4).performClick();
        Log.e("pid", Process.myPid() + "");


        View inflater = LayoutInflater.from(this).inflate(R.layout.activity_main, null);


    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.button:
                Intent serviceIntent = new Intent(this, IMService.class);
                bindService(serviceIntent, connection, Context.BIND_AUTO_CREATE);
                break;

            case R.id.button2:
                unbindService(connection);
                break;

            case R.id.button4:
                Intent intent = new Intent(this, AnimListActivity.class);
                startActivity(intent);
                break;
            case R.id.button3:

                Toast.makeText(this, "process ID" + Process.myPid(), Toast.LENGTH_SHORT).show();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Process.killProcess(Process.myPid());
                    }
                }).start();

                break;

        }
    }
}
