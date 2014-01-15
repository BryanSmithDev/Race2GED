package edu.shenandoah.ged;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;


public class SetTab extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

            setContentView(R.layout.settab_layout);
            TextView content = (TextView)findViewById(R.id.num6);
            content.setText(String.format(getResources().getString(R.string.settab_content_info), getResources().getString(R.string.tollFree)));
        }
}
