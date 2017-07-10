package com.android.lee.animations;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.lee.R;

public class TargetAnimationActivity extends Activity implements View.OnClickListener {
    private TextView textView;
    private ProgressBar progressBar;

    private Button transation;
    private Button scale;
    private Button rotation;
    private Button alpha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target_animation);

        textView = (TextView) findViewById(R.id.textView);
        findViewById(R.id.button5).setOnClickListener(this);


        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        alpha = (Button) findViewById(R.id.Alpha);
        transation = (Button) findViewById(R.id.transation);
        rotation = (Button) findViewById(R.id.rotation);
        scale = (Button) findViewById(R.id.scale);


        alpha.setOnClickListener(this);
        transation.setOnClickListener(this);
        rotation.setOnClickListener(this);
        scale.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button5:
                startAnimation(textView);
                break;
            case R.id.Alpha:
                Animation animation = AnimationUtils.loadAnimation(this, R.anim.alpha);
                textView.startAnimation(animation);
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        Toast.makeText(TargetAnimationActivity.this, "end", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                break;
            case R.id.transation:
                Animation transAnim = AnimationUtils.loadAnimation(this, R.anim.transation);
                textView.startAnimation(transAnim);

                break;
            case R.id.rotation:
                Animation rotation = AnimationUtils.loadAnimation(this, R.anim.rotation);
                textView.startAnimation(rotation);
                break;
            case R.id.scale:
                Animation scale = AnimationUtils.loadAnimation(this, R.anim.scale);
                textView.startAnimation(scale);
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
