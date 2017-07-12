package com.android.lee.animations;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.lee.R;

public class TargetAnimationActivity extends Activity implements View.OnClickListener {
    private LinearLayout root;
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


        root = (LinearLayout) findViewById(R.id.root);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        alpha = (Button) findViewById(R.id.Alpha);
        transation = (Button) findViewById(R.id.transation);
        rotation = (Button) findViewById(R.id.rotation);
        scale = (Button) findViewById(R.id.scale);


        alpha.setOnClickListener(this);
        transation.setOnClickListener(this);
        rotation.setOnClickListener(this);
        scale.setOnClickListener(this);

        AnimationDrawable animation = (AnimationDrawable)root.getBackground();
        animation.start();

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
        OvershootInterpolator overshootInterpolator = new OvershootInterpolator();
        AccelerateInterpolator accelerateInterpolator = new AccelerateInterpolator();
        AnticipateInterpolator anticipateInterpolator = new AnticipateInterpolator();
        AnticipateOvershootInterpolator anticipateOvershootInterpolator = new AnticipateOvershootInterpolator();
        AccelerateDecelerateInterpolator accelerateDecelerateInterpolator = new AccelerateDecelerateInterpolator();
        CycleInterpolator cycleInterpolator = new CycleInterpolator(10);
        BounceInterpolator bounceInterpolator = new BounceInterpolator();
        DecelerateInterpolator decelerateInterpolator = new DecelerateInterpolator();


//        animation.setInterpolator(overshootInterpolator);
//        animation.setInterpolator(accelerateDecelerateInterpolator);
//        animation.setInterpolator(accelerateInterpolator);
//        animation.setInterpolator(anticipateInterpolator);
//        animation.setInterpolator(anticipateOvershootInterpolator);
//        animation.setInterpolator(bounceInterpolator);
//        animation.setInterpolator(cycleInterpolator);
        animation.setInterpolator(decelerateInterpolator);

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
