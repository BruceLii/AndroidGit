package com.android.lee.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.util.Random;

public class IMService extends Service {

    private MyBinder myBinder = new MyBinder();

    public IMService() {
    }

    @Override
    public void onCreate() {
        Log.e("IMService ", "IMServiceonCreate");
        System.out.println("IMServiceonCreate");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("IMService ", "onStartCommand");
        System.out.println("onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {

        Log.e("IMService ", "onDestroy");
        System.out.println("onDestroy");
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        System.out.println("onBind.....");

        return myBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
    }

    /**
     * 服务共用方法，给外界返回整数值
     *
     * @return
     */
    public int getRandomNum() {
        return Math.abs(new Random().nextInt(10));
    }

    public class MyBinder extends Binder {

        public IMService getService() {
            return IMService.this;
        }

    }
}
