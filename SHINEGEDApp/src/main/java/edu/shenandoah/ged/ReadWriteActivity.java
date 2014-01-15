package edu.shenandoah.ged;

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
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class ReadWriteActivity extends Activity {
    /** Called when the activity is first created. */
	String url = "";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.readwrite_layout);
        
        
        
		  final CustomDialog dialog = new CustomDialog(this);
		  dialog.setTitle(R.string.do_you_want_to_continue);
		  dialog.setMessage(R.string.not_official_disclaimer);
		  dialog.setYesButtonAction(new View.OnClickListener() {
				public void onClick(View v) {
					Intent nIntent = new Intent(Intent.ACTION_VIEW,Uri.parse(url));
					startActivity(nIntent);
					dialog.cancel();
				}
		  });

        
        ListView list = (ListView)findViewById(R.id.listView1);
        
        String[] mTestArray = (String[])getResources().getStringArray(R.array.readwrite_items);
        ArrayAdapter<String> aa=new ArrayAdapter<String>(getApplicationContext(),
        	     R.layout.listview_layouts, mTestArray);
        	      list.setAdapter(aa);
        	      list.invalidate();
        	      
	      list.setOnItemClickListener(new OnItemClickListener() {
	    	  public void onItemClick(AdapterView<?> parent, View view, 
	    			int position, long id)        	
	    	  {
	    		  if (position == 0)
	    		  {
	    			  Intent nIntent = new Intent(view.getContext(), ReadingGuidelinesActivity.class);
	    			  startActivityForResult(nIntent, 0);
	    		  }
	    		  if (position == 1)
	    		  {
	    			  Intent nIntent = new Intent(view.getContext(), WritingGuidelinesActivity.class);
	    			  startActivityForResult(nIntent, 0);
	    		  }
	    		  if (position == 2)
	    		  {
	    			  Intent nIntent = new Intent(view.getContext(), ReadingMultiActivity.class);
	    			  startActivityForResult(nIntent, 0);
	    		  }
	    		  if (position == 3)
	    		  {
	    			  Intent nIntent = new Intent(view.getContext(), WritingMultiActivity.class);
	    			  startActivityForResult(nIntent, 0);
	    		  }
	    		  if (position == 4)
	    		  {
	    			  
						url = "http://highered.mcgraw-hill.com/sites/0809222310/student_view0/ged_practice_test.html";
						dialog.show();
									
	    		  }
	    		  if (position == 5)
	    		  {

						url="http://highered.mcgraw-hill.com/sites/0809222280/student_view0/ged_practice_test_pt_i.html";
						dialog.show();
									
	    		  }
	    		  if (position == 6)
	    		  {
						url="http://highered.mcgraw-hill.com/sites/0809222280/student_view0/ged_practice_test_pt_ii.html";
						dialog.show();

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
    		ReadWriteActivity.this.finish();
    	}
    	
    	return true;
    }
}
