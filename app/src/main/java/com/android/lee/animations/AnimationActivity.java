package com.android.lee.animations;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListAdapter;

import com.android.lee.R;

public class AnimationActivity extends Activity {
    protected static final int ANIMATION_DURATION = 3500;

    protected ImageView viewfinder_laser,viewfinder_bg;
    protected TranslateAnimation laserDownAnimation;
    protected TranslateAnimation laserUpAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        viewfinder_laser = findViewById(R.id.viewfinder_laser);
        viewfinder_bg = findViewById(R.id.viewfinder_bg);





        viewfinder_bg.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                initAnimation(viewfinder_bg.getHeight());
                laserDownAnimation();
            }
        });
    }

    private void initAnimation(final int laserHeight) {
        laserDownAnimation = new TranslateAnimation(0, 0, 0, laserHeight);//init only once
        laserUpAnimation = new TranslateAnimation(0, 0, laserHeight, 0);

        //init laserdownanimation
        laserDownAnimation.setDuration(ANIMATION_DURATION);
        laserDownAnimation.setStartOffset(0);
        laserDownAnimation.setFillAfter(true);
        laserDownAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                laserUpAnimation();//向下扫描完成后启动向下扫描。
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        //init laserupAnimation
        laserUpAnimation.setDuration(ANIMATION_DURATION);
        laserUpAnimation.setStartOffset(0);
        laserUpAnimation.setFillAfter(true);
        laserUpAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                laserDownAnimation();//
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }

    /**
     * scanning down animation
     */
    void laserDownAnimation() {
        if (viewfinder_laser != null)
            viewfinder_laser.startAnimation(laserDownAnimation);
    }

    /**
     * scanning up animation
     */
    void laserUpAnimation() {
        if (viewfinder_laser != null)
            viewfinder_laser.startAnimation(laserUpAnimation);
    }

}
