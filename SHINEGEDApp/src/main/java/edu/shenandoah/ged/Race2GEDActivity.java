package edu.shenandoah.ged;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Race2GEDActivity extends Activity {
    /** Called when the activity is first created. */
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
        
        
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
        
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.main_menu, menu);

    	return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
    	switch(item.getItemId())
    	{
    	case R.id.locations:
    		startActivity(new Intent(this, LocationsActivity.class));
    		break;
    	case R.id.rdysetgo:
    		startActivity(new Intent(this, ReadySetGoActivity.class));
    		break;
    	case R.id.freeresources:
    		startActivity(new Intent(this, FreeResourcesActivity.class));
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