package com.payment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class splashscreen extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
//        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) CoordinatorLayout coordinatorLayout = findViewById(R.id.mainLayout3);
//        AnimationDrawable animationDrawable = (AnimationDrawable) coordinatorLayout.getBackground();
//        animationDrawable.setEnterFadeDuration(2500);
//        animationDrawable.setExitFadeDuration(5000);
//        animationDrawable.start();

        ImageView splashImageView = findViewById(R.id.SplashScreenImage);

        // Load the animation from the XML resource file
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.slide_share);

        // Set an animation listener to start the main activity when the animation ends
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                // Animation starts
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // Start the main activity or another activity once the animation finishes.
                // For example, you can use an Intent to launch your main activity.
                Intent intent = new Intent(splashscreen.this, MainActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // Animation repeats (if set)
            }
        });

        // Start the animation on the ImageView
        splashImageView.startAnimation(animation);
    }
}



