package edu.shenandoah.ged;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class FeedBackActivity extends Activity {

	 /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.feedback_layout);
        
        final EditText name = (EditText)findViewById(R.id.editText1);
        final EditText email = (EditText)findViewById(R.id.editText2);
        final EditText emailBody = (EditText)findViewById(R.id.editText3);
        
        Button sendBtn = (Button)findViewById(R.id.send);
        sendBtn.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				if (!name.getText().toString().equals("")){
					if (!email.getText().toString().equals("")) {
						if (!emailBody.getText().toString().equals("")){
							Intent i = new Intent(Intent.ACTION_SEND);
							i.setType("text/plain");
							i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"bryan.smith.dev@gmail.com"});
							i.putExtra(Intent.EXTRA_SUBJECT, "An "+getResources().getString(R.string.app_name)+" feedback report from: "+name.getText().toString());
							i.putExtra(Intent.EXTRA_TEXT   , "Name: "+name.getText().toString()+"\n Email: "+email.getText().toString()+"\n\n Body: \n"+emailBody.getText().toString());
							try {
							    startActivity(Intent.createChooser(i, "Send Email..."));
							} catch (android.content.ActivityNotFoundException ex) {
							    Toast.makeText(getApplicationContext(), "There are no email clients installed.", Toast.LENGTH_SHORT).show();
							}
						} else {
							Toast.makeText(getApplicationContext(), "Please enter content for the email.", Toast.LENGTH_SHORT).show();
						}
					} else {
						Toast.makeText(getApplicationContext(), "Please enter your email.", Toast.LENGTH_SHORT).show();
					}
					
				} else {
					Toast.makeText(getApplicationContext(), "Please enter your name.", Toast.LENGTH_SHORT).show();
				}
			}
		});
    }
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.back_menu, menu);


    	return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
    	switch(item.getItemId())
    	{
    	case R.id.back:
    		this.finish();
    	}
    	
    	return true;
    }

}