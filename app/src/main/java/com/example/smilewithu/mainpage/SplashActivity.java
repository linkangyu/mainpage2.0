package com.example.smilewithu.mainpage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

public class SplashActivity extends AppCompatActivity {

    private AlphaAnimation start_anima;
    View view;
    @Override protected void onCreate(Bundle savedInstanceState)
    {
         super.onCreate(savedInstanceState);
         view = View.inflate(this, R.layout.activity_splash, null);
         setContentView(view);
         initView();
         initData(); }
         private void initData()
         { start_anima = new AlphaAnimation(0.0f, 1.0f);
         start_anima.setDuration(2000);
         view.startAnimation(start_anima);
         start_anima.setAnimationListener(new AnimationListener()
         { @Override
         public void onAnimationStart(Animation animation) { }
         @Override

         public void onAnimationRepeat(Animation animation) { }
         @Override public void onAnimationEnd(Animation animation) { redirectTo(); } }); }
         private void initView() {

         }
         private void redirectTo()
         { startActivity(new Intent(getApplicationContext(), MainActivity.class)); finish();}

}
