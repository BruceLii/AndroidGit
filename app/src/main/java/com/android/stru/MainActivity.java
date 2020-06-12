package com.android.stru;

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
import android.widget.ImageView;
import android.widget.Toast;

import com.android.stru.animations.AnimListActivity;
import com.android.stru.demos.fragmentrelated.FragmentsCoumicationActivity;
import com.android.stru.service.IMService;
import com.iglide.Glide;

public class MainActivity extends Activity implements View.OnClickListener {
    private ImageView imageView1, imageView2, imageView3;

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

        imageView1 = findViewById(R.id.image1);
        imageView2 = findViewById(R.id.image2);
        imageView3 = findViewById(R.id.image3);


        findViewById(R.id.button).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(this);
        findViewById(R.id.button4).setOnClickListener(this);
        findViewById(R.id.button5).setOnClickListener(this);

        findViewById(R.id.image1).setOnClickListener(this);
        findViewById(R.id.image2).setOnClickListener(this);
        findViewById(R.id.image3).setOnClickListener(this);


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

            case R.id.button5:
                Intent intent1 = new Intent(this, FragmentsCoumicationActivity.class);
                startActivity(intent1);
                break;

            case R.id.image1:
            case R.id.image2:
            case R.id.image3:
                t1(view);
                break;
        }
    }


    public void t1(View view) {

        Glide.with(this)
                .load("https://cn.bing.com/sa/simg/hpb/LaDigue_EN-CA1115245085_1920x1080.jpg")
                .into((ImageView) view); // inio 不能在异步线程运行
    }

}
