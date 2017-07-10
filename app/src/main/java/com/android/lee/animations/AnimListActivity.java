package com.android.lee.animations;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.lee.R;

public class AnimListActivity extends Activity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim_list);

        findViewById(R.id.button6).setOnClickListener(this);
        findViewById(R.id.button7).setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        Intent intent = new Intent(AnimListActivity.this, AnimationActivity.class);
        switch (view.getId()) {
            case R.id.button6:
                //TODO implement
                AnimListActivity.this.startActivity(intent);
                break;
            case R.id.button7:
                //TODO implement

                intent = new Intent(this, TargetAnimationActivity.class);
                this.startActivity(intent);
                break;
        }
    }
}
