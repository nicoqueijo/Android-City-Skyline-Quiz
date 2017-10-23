package com.nicoqueijo.cityskylinequiz.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.nicoqueijo.cityskylinequiz.R;

/**
 * This is activity serves as the splash screen the user sees for a couple of seconds upon
 * launching the app. After a couple seconds it proceeds to the MainMenuActivity.
 */
public class SplashScreen extends Activity {

    // time in milliseconds
    public static final long SPLASH_SCREEN_TIMEOUT = 2500;

    private ImageView mSplashScreenIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        mSplashScreenIcon = (ImageView) findViewById(R.id.splash_screen_icon);
        Animation animation = AnimationUtils.loadAnimation(getBaseContext(), R.anim.bounce);
        mSplashScreenIcon.startAnimation(animation);

        final Intent intent = new Intent(this, MainMenuActivity.class);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(intent);
                finish();
            }
        }, SPLASH_SCREEN_TIMEOUT);
    }
}
