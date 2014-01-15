package edu.mecc.race2ged;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class FreeResourcesActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.freeresources_layout);

        ListView list = (ListView) findViewById(R.id.listView1);

        String[] mTestArray = (String[]) getResources().getStringArray(R.array.freeresources_items);
        ArrayAdapter<String> aa = new ArrayAdapter<String>(getApplicationContext(),
                R.layout.listview_layouts, mTestArray);
        list.setAdapter(aa);
        list.invalidate();

        list.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                if (position == 0) {
                    accessLink("http://www.khanacademy.org/");
                }

                if (position == 1) {
                    accessLink("http://marshalladulteducation.org/student-lessons");
                }

                if (position == 2) {
                    accessLink("http://www.valrc.org/content/esol/esol_resources.html");
                }

                if (position == 3) {
                    accessLink("http://www.GEDMath.com/");
                }

                if (position == 4) {
                    accessLink("http://www.gedscience.com/");
                }

                if (position == 5) {
                    accessLink("http://www.gedreading.com/");
                }
                if (position == 6) {
                    accessLink("http://www.gedwriting.com/");
                }
                if (position == 7) {
                    accessLink("http://www.gedsocialstudies.com/");
                }
            }
        });
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
                FreeResourcesActivity.this.finish();
        }

        return true;
    }

    private void accessLink(String s) {
        final String linkUrl = s;

        final CustomDialog dialog = new CustomDialog(this);
        dialog.setTitle(R.string.do_you_want_to_continue);
        dialog.setMessage(R.string.external_website);
        dialog.setYesButtonAction(new View.OnClickListener() {
            public void onClick(View v) {
                Intent nIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(linkUrl));
                startActivity(nIntent);
                dialog.cancel();
            }
        });
        dialog.show();
    }
}
