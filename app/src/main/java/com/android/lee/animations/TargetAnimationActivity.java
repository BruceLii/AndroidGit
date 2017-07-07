package com.android.lee.animations;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.lee.R;

public class TargetAnimationActivity extends Activity implements View.OnClickListener {
    private TextView textView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target_animation);

        textView = (TextView) findViewById(R.id.textView);
        findViewById(R.id.button5).setOnClickListener(this);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button5:
                startAnimation(textView);
                break;
        }
    }

    private void startAnimation(final TextView textView) {

        final ValueAnimator animation = ValueAnimator.ofInt(0, 100);
        animation.setDuration(4000);
        animation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                Log.e("value change :", valueAnimator.getAnimatedValue() + "....");
                progressBar.setProgress((Integer) valueAnimator.getAnimatedValue());
                textView.setText(valueAnimator.getAnimatedValue() + "");

            }
        });

        animation.start();
    }
}
