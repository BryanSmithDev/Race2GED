package edu.shenandoah.ged;

import android.app.Activity;
import android.os.Bundle;


public class ReadyTab extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

            setContentView(R.layout.readytab_layout);
            //TextView content = (TextView)findViewById(R.id.num5);
           // content.setText(String.format(getResources().getString(R.string.readytab_content_info), getResources().getString(R.string.tollFree)));
        }
}
