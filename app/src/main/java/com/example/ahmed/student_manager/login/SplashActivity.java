package com.example.ahmed.student_manager.login;

import android.content.Intent;

import com.daimajia.androidanimations.library.Techniques;
import com.example.ahmed.student_manager.R;
import com.viksaa.sssplash.lib.activity.AwesomeSplash;
import com.viksaa.sssplash.lib.cnst.Flags;
import com.viksaa.sssplash.lib.model.ConfigSplash;


public class SplashActivity extends AwesomeSplash {

    @Override
    public void initSplash(ConfigSplash configSplash) {
            /* you don't have to override every property */

        //Customize Circular Reveal
        configSplash.setBackgroundColor(R.color.colorPrimary); //any color you want form colors.xml
        configSplash.setAnimCircularRevealDuration(2000); //int ms
        configSplash.setRevealFlagX(Flags.REVEAL_RIGHT);  //or Flags.REVEAL_LEFT
        configSplash.setRevealFlagY(Flags.REVEAL_BOTTOM); //or Flags.REVEAL_TOP

        //Choose LOGO OR PATH; if you don't provide String value for path it's logo by default

        //Customize Logo
        configSplash.setLogoSplash(R.drawable.logo); //or any other drawable
        configSplash.setAnimLogoSplashDuration(2000); //int ms
        configSplash.setAnimLogoSplashTechnique(Techniques.FlipInX);
        configSplash.setPathSplashFillColor(R.color.colorAccent);
        //choose one form Techniques (ref: https://github.com/daimajia/AndroidViewAnimations)




        //Customize Title
        configSplash.setTitleSplash("Student Registeration");
        configSplash.setTitleTextColor(R.color.color3);
        configSplash.setTitleTextSize(20f); //float value
        configSplash.setAnimTitleDuration(1500);
        configSplash.setAnimTitleTechnique(Techniques.FlipInX);
//        configSplash.setTitleFont("fonts/title.ttf"); //provide string to your font located in assets/fonts/

    }

    //--------------------this refer to the next screen after splash screen finished----------------------//
    @Override
    public void animationsFinished() {
        Intent i =new Intent(SplashActivity.this,MainActivity.class);
        startActivity(i);
        finish();
    }
}