package edu.mecc.race2ged;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;

public class Race2GEDActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    private boolean firstRun;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);

        final Dialog customDialog = new Dialog(this);
        customDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        customDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


        ((Button) findViewById(R.id.gedButton)).setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Log.d("DEBUG", "GED button pressed");
                startActivity(new Intent(getApplicationContext(), TheGedActivity.class));
            }
        });

        ((Button) findViewById(R.id.mathButton)).setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Log.d("DEBUG", "Math button pressed");
                startActivity(new Intent(getApplicationContext(), MathActivity.class));
            }
        });

        ((Button) findViewById(R.id.scienceButton)).setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Log.d("DEBUG", "Science button pressed");
                startActivity(new Intent(getApplicationContext(), ScienceActivity.class));
            }
        });

        ((Button) findViewById(R.id.literacyButton)).setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Log.d("DEBUG", "Literacy button pressed");
                startActivity(new Intent(getApplicationContext(), ReadWriteActivity.class));
            }
        });

        ((Button) findViewById(R.id.socialScienceButton)).setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Log.d("DEBUG", "Social Science button pressed");
                startActivity(new Intent(getApplicationContext(), SocialStudiesActivity.class));
            }
        });

        
        firstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getBoolean("firstrun", true);
        if (firstRun) {

            // Save the state
            getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .edit()
                .putBoolean("firstrun", false)
                .commit();

            customDialog.setContentView(R.layout.outofdate_popup);
            ImageButton doneButton = (ImageButton) customDialog.findViewById(R.id.closeButton);
            doneButton.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    customDialog.dismiss();
                }
            });
            Button dirButton = (Button) customDialog.findViewById(R.id.dirButton);
            dirButton.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    customDialog.dismiss();
                }
            });
            customDialog.show();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.locations:
                startActivity(new Intent(this, LocationsActivity.class));
                break;
            case R.id.rdysetgo:
                startActivity(new Intent(this, ReadySetGoActivity.class));
                break;
            case R.id.freeresources:
                startActivity(new Intent(this, FreeResourcesActivity.class));
                break;
            case R.id.vidtest:
                startActivity(new Intent(this, VideoTestimonialActivity.class));
                break;
            case R.id.contact:
                startActivity(new Intent(this, ContactActivity.class));
                break;
            case R.id.dev_team:
                startActivity(new Intent(this, AboutDevActivity.class));
                break;
            case R.id.sources:
                startActivity(new Intent(this, SourcesActivity.class));
                break;
            case R.id.feedback:
                startActivity(new Intent(this, FeedBackActivity.class));
                break;
            case R.id.exit:
                Race2GEDActivity.this.finish();
                break;
        }

        return true;
    }
}