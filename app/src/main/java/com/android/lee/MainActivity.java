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
import android.view.View;
import android.widget.Toast;

import com.android.lee.animations.AnimListActivity;
import com.android.lee.service.IMService;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends Activity implements View.OnClickListener {
    AdView mAdView;

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

        findViewById(R.id.button).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(this);
        findViewById(R.id.button4).setOnClickListener(this);

//        findViewById(R.id.button4).performClick();
        Log.e("pid", Process.myPid() + "");

        MobileAds.initialize(this, getString(R.string.ad_app_ID));

        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
//                .addTestDevice("E254B74D5782E54433C7E8C193063B42")
                .build();

        mAdView.loadAd(adRequest);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mAdView != null) {
            mAdView.pause();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
//            case R.id.root:
//                Toast.makeText(this, "rootclick", Toast.LENGTH_SHORT).show();
//                break;
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
