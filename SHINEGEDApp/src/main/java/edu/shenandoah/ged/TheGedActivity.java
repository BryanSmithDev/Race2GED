package edu.shenandoah.ged;

import android.app.Activity;
import android.content.Intent;
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

public class TheGedActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.theged_layout);
        
        ListView list = (ListView)findViewById(R.id.listView1);
        
        String[] mTestArray = (String[])getResources().getStringArray(R.array.theged_items);
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
        			Intent nIntent = new Intent(view.getContext(), WhatGedActivity.class);
        			startActivityForResult(nIntent, 0);
        		}
        		
        		if (position == 1)
        		{
        			Intent nIntent = new Intent(view.getContext(), HowGedActivity.class);
        			startActivityForResult(nIntent, 0);
        		}
        		
        		if (position == 2)
        		{
        			Intent nIntent = new Intent(view.getContext(), NewGedActivity.class);
        			startActivityForResult(nIntent, 0);
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
    		TheGedActivity.this.finish();
    	}
    	
    	return true;
    }
}
