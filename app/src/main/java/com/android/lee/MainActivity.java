package com.android.lee;

import android.app.Activity;
import android.os.Bundle;
import android.os.Process;
import android.util.Log;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.e("***** Process ID", Process.myPid() + "");

    }
}
