package edu.mecc.race2ged;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.widget.TabHost;

public class ReadySetGoActivity extends TabActivity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.readysetgo_layout);


        Resources res = getResources(); // Resource object to get Drawables
        TabHost tabHost = getTabHost();  // The activity TabHost
        TabHost.TabSpec spec;  // Resusable TabSpec for each tab
        Intent intent;  // Reusable Intent for each tab


        //Add Ready Tab and contents
        intent = new Intent().setClass(this, ReadyTab.class);
        spec = tabHost.newTabSpec("ready").setIndicator("", res.getDrawable(R.drawable.testing_ready)).setContent(intent);
        tabHost.addTab(spec);

        //Add Set Tab and contents
        intent = new Intent().setClass(this, SetTab.class);
        spec = tabHost.newTabSpec("set").setIndicator("", res.getDrawable(R.drawable.testing_set)).setContent(intent);
        tabHost.addTab(spec);

        //Add Go Tab and contents
        intent = new Intent().setClass(this, GoTab.class);
        spec = tabHost.newTabSpec("go").setIndicator("", res.getDrawable(R.drawable.testing_go)).setContent(intent);
        tabHost.addTab(spec);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.back_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.back:
                this.finish();
        }
        return true;
    }
}
