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
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.google.firebase.analytics.FirebaseAnalytics;

public class MainActivity extends Activity implements View.OnClickListener {
    AdView mAdView;
    private FirebaseAnalytics mFirebaseAnalytics;

    private InterstitialAd mInterstitialAd;
    private RewardedVideoAd mAd;
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
        MobileAds.initialize(this, getString(R.string.ad_app_ID));

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);


        findViewById(R.id.button).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(this);
        findViewById(R.id.button4).setOnClickListener(this);


//        findViewById(R.id.button4).performClick();
        Log.e("pid", Process.myPid() + "");


        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
//                .addTestDevice("E254B74D5782E54433C7E8C193063B42")
                .build();

        mAdView.loadAd(adRequest);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_ad));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        mAd = MobileAds.getRewardedVideoAdInstance(this);
        mAd.setRewardedVideoAdListener(new RewardedVideoAdListener() {
            @Override
            public void onRewardedVideoAdLoaded() {

            }

            @Override
            public void onRewardedVideoAdOpened() {

            }

            @Override
            public void onRewardedVideoStarted() {

            }

            @Override
            public void onRewardedVideoAdClosed() {

            }

            @Override
            public void onRewarded(RewardItem rewardItem) {

            }

            @Override
            public void onRewardedVideoAdLeftApplication() {

            }

            @Override
            public void onRewardedVideoAdFailedToLoad(int i) {

            }
        });
        mAd.loadAd(getString(R.string.install_add), new AdRequest.Builder().build());


        View inflater = LayoutInflater.from(this).inflate(R.layout.activity_main, null);


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
            case R.id.root:
                if (mAd.isLoaded()) {
                    mAd.show();
                }


                //add two
                AdRequest adRequest = new AdRequest.Builder().build();
                mInterstitialAd.loadAd(adRequest);
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    Log.d("TAG", "The interstitial wasn't loaded yet.");
                }
                break;
            case R.id.adView:
                Bundle bundle = new Bundle();
                bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "adView");
                bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "banner");
                bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "chlick");

                mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

                break;
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
