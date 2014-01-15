package edu.mecc.race2ged;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;


public class ContactActivity extends Activity {

    String url1 = "http://www.youtube.com/regionaladulted";
    String url2 = "http://www.facebook.com/pages/Regional-Adult-Education/140057909349342";
    String url3 = "http://twitter.com/#!/race2gednow";
    String url4 = "http://race2ged.org";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.contact_layout);

        TextView toll = (TextView) findViewById(R.id.tollFree);
        TextView local = (TextView) findViewById(R.id.localNumber);

        TextView version = (TextView) findViewById(R.id.version);
        try {
            version.setText("Version: " + getPackageManager().getPackageInfo(getPackageName(), 0).versionName);
        } catch (NameNotFoundException e) {
            version.setText("Version: 0.0");
            e.printStackTrace();
        }

        toll.setText(String.format(getResources().getString(R.string.tollFreeLabel), getResources().getString(R.string.tollFree)));
        local.setText(String.format(getResources().getString(R.string.localPhoneLabel), getResources().getString(R.string.localPhone)));

        ImageButton youtube = (ImageButton) findViewById(R.id.youtube);
        ImageButton facebook = (ImageButton) findViewById(R.id.facebook);
        ImageButton twitter = (ImageButton) findViewById(R.id.twitter);
        ImageButton webIcon = (ImageButton) findViewById(R.id.webIcon);

        webIcon.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                final CustomDialog dialog = new CustomDialog(ContactActivity.this);
                dialog.setTitle(R.string.do_you_want_to_continue);
                dialog.setMessage(R.string.external_website);
                dialog.setYesButtonAction(new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent nIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url4));
                        startActivity(nIntent);
                        dialog.cancel();
                    }
                });
                dialog.show();

            }
        }

        );

        youtube.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                final CustomDialog dialog = new CustomDialog(ContactActivity.this);
                dialog.setTitle(R.string.do_you_want_to_continue);
                dialog.setMessage(R.string.external_website);
                dialog.setYesButtonAction(new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent nIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url1));
                        startActivity(nIntent);
                        dialog.cancel();
                    }
                });
                dialog.show();

            }
        }

        );

        facebook.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                final CustomDialog dialog = new CustomDialog(ContactActivity.this);
                dialog.setTitle(R.string.do_you_want_to_continue);
                dialog.setMessage(R.string.external_website);
                dialog.setYesButtonAction(new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent nIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url2));
                        startActivity(nIntent);
                        dialog.cancel();
                    }
                });
                dialog.show();
            }
        });
        twitter.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                final CustomDialog dialog = new CustomDialog(ContactActivity.this);
                dialog.setTitle(R.string.do_you_want_to_continue);
                dialog.setMessage(R.string.external_website);
                dialog.setYesButtonAction(new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent nIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url3));
                        startActivity(nIntent);
                        dialog.cancel();
                    }
                });
                dialog.show();
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
                this.finish();
        }

        return true;
    }

}
