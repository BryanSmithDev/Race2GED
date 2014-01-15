package edu.mecc.race2ged;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.widget.ImageView;

public class SplashScreen extends Activity implements AnimationListener {

    ImageView logo;
    int phase = 0;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.splash_layout);

        logo = (ImageView) findViewById(R.id.logo);
        Animation fadeIn = new AlphaAnimation(0, 1);
        //fadeIn.setInterpolator(new DecelerateInterpolator()); //add this
        fadeIn.setDuration(250);

        Animation fadeOut = new AlphaAnimation(1, 0);
        //fadeOut.setInterpolator(new AccelerateInterpolator()); //and this
        fadeOut.setStartOffset(1500);
        fadeOut.setDuration(250);

        AnimationSet animation = new AnimationSet(false); //change to false
        animation.addAnimation(fadeIn);
        animation.addAnimation(fadeOut);

        animation.setAnimationListener(this);

        logo.startAnimation(animation);


    }

    public void onAnimationEnd(Animation animation) {

        if (phase <= 1) {
            logo.setImageDrawable(getApplication().getResources().getDrawable(R.drawable.adulted_logo_recreated_big_extra_text_effects));
            animation.reset();
            logo.startAnimation(animation);
            phase++;

        } else {
            logo.setAlpha(0);
            startActivity(new Intent(this, Race2GEDActivity.class));
            this.finish();
        }


    }

    public void onAnimationRepeat(Animation animation) {
        // TODO Auto-generated method stub

    }

    public void onAnimationStart(Animation animation) {
        //logo.setAlpha(0);

    }
}
