package edu.shenandoah.ged;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;



public class GoTab extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

            setContentView(R.layout.gotab_layout);
            Button findCenter = (Button)findViewById(R.id.findCenter);
            
            TextView content = (TextView)findViewById(R.id.num4);
            content.setText(String.format(getResources().getString(R.string.gotab_content_info), getResources().getString(R.string.tollFree)));
            
            findCenter.setOnClickListener(new OnClickListener() {
				
				public void onClick(View v) {
					accessLink("http://www2.acenet.edu/resources/ged/center_locator.cfm");
					
				}
			});
        }
    
    private void accessLink(String s)
    {
    	final String linkUrl = s;
		  final CustomDialog dialog = new CustomDialog(this);
		  dialog.setTitle(R.string.do_you_want_to_continue);
		  dialog.setMessage(R.string.external_website);
		  dialog.setYesButtonAction(new View.OnClickListener() {
				public void onClick(View v) {
					Intent nIntent = new Intent(Intent.ACTION_VIEW,Uri.parse(linkUrl));
					startActivity(nIntent);
					dialog.cancel();
				}
		  });
		  dialog.show();
		}
}
