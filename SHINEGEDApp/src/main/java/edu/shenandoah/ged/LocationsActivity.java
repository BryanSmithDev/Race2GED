package edu.shenandoah.ged;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;



public class LocationsActivity extends Activity {
    /** Called when the activity is first created. */
	ExpandableListAdapter mAdapter;
	ExpandableListView list;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.locations_layout);


	    final Dialog customDialog = new Dialog(this);
	    customDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
	    customDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


 				
        		
        	    
        	    list = (ExpandableListView) findViewById(R.id.placeList);
        	    list.setSelector(getResources().getDrawable(R.color.selector_bg));
 				list.setFocusable(true);
        	    mAdapter = new customListAdapter();
        		list.setAdapter(mAdapter);
        		
        		list.setOnGroupClickListener(new OnGroupClickListener() {
					
					public boolean onGroupClick(ExpandableListView parent, View v,
							int groupPosition, long id) {
						v.setBackgroundColor(getApplication().getResources().getColor(R.color.content_color));
						((TextView) v).setTextColor(getApplication().getResources().getColor(R.color.primary_color));
						return false;
					}
				});
        		

        		
        		list.setOnGroupClickListener(new OnGroupClickListener() {
					
					public boolean onGroupClick(ExpandableListView parent, View v,
							int groupPosition, long id) {
						if (id==4){
							
							customDialog.setContentView(R.layout.distance_popup);
			        	    ImageButton doneButton = (ImageButton)customDialog.findViewById(R.id.closeButton);
			 				doneButton.setOnClickListener(new OnClickListener() {	
			 					public void onClick(View v) {
			 						customDialog.dismiss();
			 					}
			 				});
							customDialog.show();
							
						} else if (id==5) {
							accessLink("http://www.valrc.org/providers/index.php");
						}
						return false;
					}
				});
        			
				list.setOnChildClickListener(new OnChildClickListener() {
					
					public boolean onChildClick(ExpandableListView parent, View v,
							int groupPosition, int childPosition, long id) {
						
		
						customDialog.setContentView(R.layout.location_popup);
		        	    ImageButton doneButton = (ImageButton)customDialog.findViewById(R.id.closeButton);
		 				doneButton.setOnClickListener(new OnClickListener() {	
		 					public void onClick(View v) {
		 						customDialog.dismiss();
		 					}
		 				});
		 				
						TextView title = (TextView)customDialog.findViewById(R.id.titleName);
						TextView place = (TextView)customDialog.findViewById(R.id.place);
						TextView address = (TextView)customDialog.findViewById(R.id.address);
						TextView phone = (TextView)customDialog.findViewById(R.id.phone);
						TextView time = (TextView)customDialog.findViewById(R.id.time);
						TextView instruct = (TextView)customDialog.findViewById(R.id.instructors);
						Button dirButton = (Button)customDialog.findViewById(R.id.dirButton);
						
						
						switch (groupPosition) {
						case 0:
							switch (childPosition) {
							case 0:
								
								title.setText(R.string.augusta_title);
								place.setText(R.string.augusta_place);
								address.setText(R.string.augusta_address);
								phone.setText(R.string.augusta_phone);
								time.setText(R.string.augusta_time);
								instruct.setText(R.string.augusta_instructors);
								
								instruct.setOnClickListener(new OnClickListener() {
									
									public void onClick(View v) {
										Intent i = new Intent(Intent.ACTION_SEND);
										i.setType("message/rfc822");
										i.putExtra(Intent.EXTRA_EMAIL  , new String[]{getResources().getString(R.string.augusta_email)});
										i.putExtra(Intent.EXTRA_SUBJECT, "");
										i.putExtra(Intent.EXTRA_TEXT   , "");
										try {
										    startActivity(Intent.createChooser(i, "Send mail..."));
										} catch (android.content.ActivityNotFoundException ex) {
										    Toast.makeText(LocationsActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
										}
										
									}
								});
								
				 				dirButton.setOnClickListener(new OnClickListener() {
				 					public void onClick(View v) {
				 						final String url1 = "https://maps.google.com/maps?q="+getResources().getString(R.string.augusta_address);
				 		    			  accessLink(url1);
				 					}
				 				});
								customDialog.show();
								break;
							case 1:
								title.setText(R.string.staunton_title);
								place.setText(R.string.staunton_place);
								address.setText(R.string.staunton_address);
								phone.setText(R.string.staunton_phone);
								time.setText(R.string.staunton_time);
								instruct.setText(R.string.staunton_instructors);
								
								instruct.setOnClickListener(new OnClickListener() {
									
									public void onClick(View v) {
										Intent i = new Intent(Intent.ACTION_SEND);
										i.setType("message/rfc822");
										i.putExtra(Intent.EXTRA_EMAIL  , new String[]{getResources().getString(R.string.staunton_email)});
										i.putExtra(Intent.EXTRA_SUBJECT, "");
										i.putExtra(Intent.EXTRA_TEXT   , "");
										try {
										    startActivity(Intent.createChooser(i, "Send mail..."));
										} catch (android.content.ActivityNotFoundException ex) {
										    Toast.makeText(LocationsActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
										}
										
									}
								});
								
				 				dirButton.setOnClickListener(new OnClickListener() {
				 					public void onClick(View v) {
				 						final String url1 = "https://maps.google.com/maps?q="+getResources().getString(R.string.staunton_address);
				 		    			  accessLink(url1);
				 					}
				 				});
								customDialog.show();
								break;
							case 2:
								title.setText(R.string.craigsville_title);
								place.setText(R.string.craigsville_place);
								address.setText(R.string.craigsville_address);
								phone.setText(R.string.craigsville_phone);
								time.setText(R.string.craigsville_time);
								instruct.setText(R.string.craigsville_instructors);
								
								instruct.setOnClickListener(new OnClickListener() {
									
									public void onClick(View v) {
										Intent i = new Intent(Intent.ACTION_SEND);
										i.setType("message/rfc822");
										i.putExtra(Intent.EXTRA_EMAIL  , new String[]{getResources().getString(R.string.craigsville_email)});
										i.putExtra(Intent.EXTRA_SUBJECT, "");
										i.putExtra(Intent.EXTRA_TEXT   , "");
										try {
										    startActivity(Intent.createChooser(i, "Send mail..."));
										} catch (android.content.ActivityNotFoundException ex) {
										    Toast.makeText(LocationsActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
										}
										
									}
								});
								
				 				dirButton.setOnClickListener(new OnClickListener() {
				 					public void onClick(View v) {
				 						final String url1 = "https://maps.google.com/maps?q="+getResources().getString(R.string.craigsville_address);
				 		    			  accessLink(url1);
				 					}
				 				});
								customDialog.show();
								break;
							}
							break;
						case 1:
							switch (childPosition) {

							case 0:
								title.setText(R.string.harrisonburg1_title);
								place.setText(R.string.harrisonburg1_place);
								address.setText(R.string.harrisonburg1_address);
								phone.setText(R.string.harrisonburg1_phone);
								time.setText(R.string.harrisonburg1_time);
								instruct.setText(R.string.harrisonburg1_instructors);
								
								instruct.setOnClickListener(new OnClickListener() {
									
									public void onClick(View v) {
										Intent i = new Intent(Intent.ACTION_SEND);
										i.setType("message/rfc822");
										i.putExtra(Intent.EXTRA_EMAIL  , new String[]{getResources().getString(R.string.harrisonburg1_email)});
										i.putExtra(Intent.EXTRA_SUBJECT, "");
										i.putExtra(Intent.EXTRA_TEXT   , "");
										try {
										    startActivity(Intent.createChooser(i, "Send mail..."));
										} catch (android.content.ActivityNotFoundException ex) {
										    Toast.makeText(LocationsActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
										}
										
									}
								});
								
				 				dirButton.setOnClickListener(new OnClickListener() {
				 					public void onClick(View v) {
				 						final String url1 = "https://maps.google.com/maps?q="+getResources().getString(R.string.harrisonburg1_address);
				 		    			  accessLink(url1);
				 					}
				 				});
								customDialog.show();
								break;
							case 1:
								title.setText(R.string.harrisonburg2_title);
								place.setText(R.string.harrisonburg2_place);
								address.setText(R.string.harrisonburg2_address);
								phone.setText(R.string.harrisonburg2_phone);
								time.setText(R.string.harrisonburg2_time);
								instruct.setText(R.string.harrisonburg2_instructors);
								
								instruct.setOnClickListener(new OnClickListener() {
									
									public void onClick(View v) {
										Intent i = new Intent(Intent.ACTION_SEND);
										i.setType("message/rfc822");
										i.putExtra(Intent.EXTRA_EMAIL  , new String[]{getResources().getString(R.string.harrisonburg2_email)});
										i.putExtra(Intent.EXTRA_SUBJECT, "");
										i.putExtra(Intent.EXTRA_TEXT   , "");
										try {
										    startActivity(Intent.createChooser(i, "Send mail..."));
										} catch (android.content.ActivityNotFoundException ex) {
										    Toast.makeText(LocationsActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
										}
										
									}
								});
								
				 				dirButton.setOnClickListener(new OnClickListener() {
				 					public void onClick(View v) {
				 						final String url1 = "https://maps.google.com/maps?q="+getResources().getString(R.string.harrisonburg2_address);
				 		    			  accessLink(url1);
				 					}
				 				});
								customDialog.show();
								break;
							case 2:
								title.setText(R.string.elkton_title);
								place.setText(R.string.elkton_place);
								address.setText(R.string.elkton_address);
								phone.setText(R.string.elkton_phone);
								time.setText(R.string.elkton_time);
								instruct.setText(R.string.elkton_instructors);
								
								instruct.setOnClickListener(new OnClickListener() {
									
									public void onClick(View v) {
										Intent i = new Intent(Intent.ACTION_SEND);
										i.setType("message/rfc822");
										i.putExtra(Intent.EXTRA_EMAIL  , new String[]{getResources().getString(R.string.elkton_email)});
										i.putExtra(Intent.EXTRA_SUBJECT, "");
										i.putExtra(Intent.EXTRA_TEXT   , "");
										try {
										    startActivity(Intent.createChooser(i, "Send mail..."));
										} catch (android.content.ActivityNotFoundException ex) {
										    Toast.makeText(LocationsActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
										}
										
									}
								});
								
				 				dirButton.setOnClickListener(new OnClickListener() {
				 					public void onClick(View v) {
				 						final String url1 = "https://maps.google.com/maps?q="+getResources().getString(R.string.elkton_address);
				 		    			  accessLink(url1);
				 					}
				 				});
								customDialog.show();
								break;
							case 3:
								title.setText(R.string.timberville_title);
								place.setText(R.string.timberville_place);
								address.setText(R.string.timberville_address);
								phone.setText(R.string.timberville_phone);
								time.setText(R.string.timberville_time);
								instruct.setText(R.string.timberville_instructors);
								
								instruct.setOnClickListener(new OnClickListener() {
									
									public void onClick(View v) {
										Intent i = new Intent(Intent.ACTION_SEND);
										i.setType("message/rfc822");
										i.putExtra(Intent.EXTRA_EMAIL  , new String[]{getResources().getString(R.string.timberville_email)});
										i.putExtra(Intent.EXTRA_SUBJECT, "");
										i.putExtra(Intent.EXTRA_TEXT   , "");
										try {
										    startActivity(Intent.createChooser(i, "Send mail..."));
										} catch (android.content.ActivityNotFoundException ex) {
										    Toast.makeText(LocationsActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
										}
										
									}
								});
								
				 				dirButton.setOnClickListener(new OnClickListener() {
				 					public void onClick(View v) {
				 						final String url1 = "https://maps.google.com/maps?q="+getResources().getString(R.string.timberville_address);
				 		    			  accessLink(url1);
				 					}
				 				});
								customDialog.show();
								break;
							}
							break;
							
						case 2:
							switch (childPosition) {
							case 0:
								title.setText(R.string.lexington_title);
								place.setText(R.string.lexington_place);
								address.setText(R.string.lexington_address);
								phone.setText(R.string.lexington_phone);
								time.setText(R.string.lexington_time);
								instruct.setText(R.string.lexington_instructors);
								
								instruct.setOnClickListener(new OnClickListener() {
									
									public void onClick(View v) {
										Intent i = new Intent(Intent.ACTION_SEND);
										i.setType("message/rfc822");
										i.putExtra(Intent.EXTRA_EMAIL  , new String[]{getResources().getString(R.string.lexington_email)});
										i.putExtra(Intent.EXTRA_SUBJECT, "");
										i.putExtra(Intent.EXTRA_TEXT   , "");
										try {
										    startActivity(Intent.createChooser(i, "Send mail..."));
										} catch (android.content.ActivityNotFoundException ex) {
										    Toast.makeText(LocationsActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
										}
										
									}
								});
								
				 				dirButton.setOnClickListener(new OnClickListener() {
				 					public void onClick(View v) {
				 						final String url1 = "https://maps.google.com/maps?q="+getResources().getString(R.string.lexington_address);
				 		    			  accessLink(url1);
				 					}
				 				});
								customDialog.show();
								break;
							case 1:
								title.setText(R.string.hotsprings_title);
								place.setText(R.string.hotsprings_place);
								address.setText(R.string.hotsprings_address);
								phone.setText(R.string.hotsprings_phone);
								time.setText(R.string.hotsprings_time);
								instruct.setText(R.string.hotsprings_instructors);
								
								instruct.setOnClickListener(new OnClickListener() {
									
									public void onClick(View v) {
										Intent i = new Intent(Intent.ACTION_SEND);
										i.setType("message/rfc822");
										i.putExtra(Intent.EXTRA_EMAIL  , new String[]{getResources().getString(R.string.hotsprings_email)});
										i.putExtra(Intent.EXTRA_SUBJECT, "");
										i.putExtra(Intent.EXTRA_TEXT   , "");
										try {
										    startActivity(Intent.createChooser(i, "Send mail..."));
										} catch (android.content.ActivityNotFoundException ex) {
										    Toast.makeText(LocationsActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
										}
										
									}
								});
								
				 				dirButton.setOnClickListener(new OnClickListener() {
				 					public void onClick(View v) {
				 						final String url1 = "https://maps.google.com/maps?q=Valley+Elementary+School,+Hot+Springs,+VA&hl=en&sll=37.972316,-79.850194&sspn=0.003256,0.006126&oq=Vall,+Hot+Springs,+VA&t=h&hq=Valley+Elementary+School,&hnear=Hot+Springs,+Cedar+Creek,+Bath,+Virginia&z=13";
				 		    			  accessLink(url1);
				 					}
				 				});
								customDialog.show();
								break;
							case 2:
								title.setText(R.string.buenavista1_title);
								place.setText(R.string.buenavista1_place);
								address.setText(R.string.buenavista1_address);
								phone.setText(R.string.buenavista1_phone);
								time.setText(R.string.buenavista1_time);
								instruct.setText(R.string.buenavista1_instructors);
								
								instruct.setOnClickListener(new OnClickListener() {
									
									public void onClick(View v) {
										Intent i = new Intent(Intent.ACTION_SEND);
										i.setType("message/rfc822");
										i.putExtra(Intent.EXTRA_EMAIL  , new String[]{getResources().getString(R.string.buenavista1_email)});
										i.putExtra(Intent.EXTRA_SUBJECT, "");
										i.putExtra(Intent.EXTRA_TEXT   , "");
										try {
										    startActivity(Intent.createChooser(i, "Send mail..."));
										} catch (android.content.ActivityNotFoundException ex) {
										    Toast.makeText(LocationsActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
										}
										
									}
								});
				        	    
				 				dirButton.setOnClickListener(new OnClickListener() {
				 					public void onClick(View v) {
				 						final String url1 = "https://maps.google.com/maps?q=Dabney+S.+Lancaster+Community+College+Rockbridge+Regional+Center,+Vista+Links+Drive,+Buena+Vista,+VA&hl=en&sll=37.972637,-79.85213&sspn=0.104198,0.196037&oq=Dabney+S.+Lancaster+Community+College&t=h&hq=Dabney+S.+Lancaster+Community+College+Rockbridge+Regional+Center,+Vista+Links+Drive,+Buena+Vista,+VA&z=15";
				 		    			  accessLink(url1);
				 					}
				 				});
								customDialog.show();
								break;
							case 3:
								title.setText(R.string.buenavista2_title);
								place.setText(R.string.buenavista2_place);
								address.setText(R.string.buenavista2_address);
								phone.setText(R.string.buenavista2_phone);
								time.setText(R.string.buenavista2_time);
								instruct.setText(R.string.buenavista2_instructors);
								
								instruct.setOnClickListener(new OnClickListener() {
									
									public void onClick(View v) {
										Intent i = new Intent(Intent.ACTION_SEND);
										i.setType("message/rfc822");
										i.putExtra(Intent.EXTRA_EMAIL  , new String[]{getResources().getString(R.string.buenavista2_email)});
										i.putExtra(Intent.EXTRA_SUBJECT, "");
										i.putExtra(Intent.EXTRA_TEXT   , "");
										try {
										    startActivity(Intent.createChooser(i, "Send mail..."));
										} catch (android.content.ActivityNotFoundException ex) {
										    Toast.makeText(LocationsActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
										}
										
									}
								});
				        	    
				 				dirButton.setOnClickListener(new OnClickListener() {
				 					public void onClick(View v) {
				 						final String url1 = "https://maps.google.com/maps?q="+getResources().getString(R.string.buenavista2_address);
				 		    			  accessLink(url1);
				 					}
				 				});
								customDialog.show();
								break;
							case 4:
								title.setText(R.string.goshen_title);
								place.setText(R.string.goshen_place);
								address.setText(R.string.goshen_address);
								phone.setText(R.string.goshen_phone);
								time.setText(R.string.goshen_time);
								instruct.setText(R.string.goshen_instructors);
								
								instruct.setOnClickListener(new OnClickListener() {
									
									public void onClick(View v) {
										Intent i = new Intent(Intent.ACTION_SEND);
										i.setType("message/rfc822");
										i.putExtra(Intent.EXTRA_EMAIL  , new String[]{getResources().getString(R.string.goshen_email)});
										i.putExtra(Intent.EXTRA_SUBJECT, "");
										i.putExtra(Intent.EXTRA_TEXT   , "");
										try {
										    startActivity(Intent.createChooser(i, "Send mail..."));
										} catch (android.content.ActivityNotFoundException ex) {
										    Toast.makeText(LocationsActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
										}
										
									}
								});
				        	    
				 				dirButton.setOnClickListener(new OnClickListener() {
				 					public void onClick(View v) {
				 						final String url1 = "https://maps.google.com/maps?q=Goshen+Library,+1124+Virginia+Ave,+Goshen,+VA+24439&hl=en&sll=37.988718,-79.498796&sspn=0.104175,0.196037&t=h&hq=Goshen+Library,&hnear=Virginia+Ave,+Goshen,+Virginia+24439&z=13";
				 		    			  accessLink(url1);
				 					}
				 				});
								customDialog.show();
								break;
							case 5:
								title.setText(R.string.glasgow_title);
								place.setText(R.string.glasgow_place);
								address.setText(R.string.glasgow_address);
								phone.setText(R.string.glasgow_phone);
								time.setText(R.string.glasgow_time);
								instruct.setText(R.string.glasgow_instructors);
								
								instruct.setOnClickListener(new OnClickListener() {
									
									public void onClick(View v) {
										Intent i = new Intent(Intent.ACTION_SEND);
										i.setType("message/rfc822");
										i.putExtra(Intent.EXTRA_EMAIL  , new String[]{getResources().getString(R.string.glasgow_email)});
										i.putExtra(Intent.EXTRA_SUBJECT, "");
										i.putExtra(Intent.EXTRA_TEXT   , "");
										try {
										    startActivity(Intent.createChooser(i, "Send mail..."));
										} catch (android.content.ActivityNotFoundException ex) {
										    Toast.makeText(LocationsActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
										}
										
									}
								});
				        	    
				 				dirButton.setOnClickListener(new OnClickListener() {
				 					public void onClick(View v) {
				 						final String url1 = "https://maps.google.com/maps?q="+getResources().getString(R.string.glasgow_address);
				 		    			  accessLink(url1);
				 					}
				 				});
								customDialog.show();
								break;
							case 6:
								title.setText(R.string.monterey_title);
								place.setText(R.string.monterey_place);
								address.setText(R.string.monterey_address);
								phone.setText(R.string.monterey_phone);
								time.setText(R.string.monterey_time);
								instruct.setText(R.string.monterey_instructors);
								
								instruct.setOnClickListener(new OnClickListener() {
									
									public void onClick(View v) {
										Intent i = new Intent(Intent.ACTION_SEND);
										i.setType("message/rfc822");
										i.putExtra(Intent.EXTRA_EMAIL  , new String[]{getResources().getString(R.string.monterey_email)});
										i.putExtra(Intent.EXTRA_SUBJECT, "");
										i.putExtra(Intent.EXTRA_TEXT   , "");
										try {
										    startActivity(Intent.createChooser(i, "Send mail..."));
										} catch (android.content.ActivityNotFoundException ex) {
										    Toast.makeText(LocationsActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
										}
										
									}
								});
				        	    
				 				dirButton.setOnClickListener(new OnClickListener() {
				 					public void onClick(View v) {
				 						final String url1 = "https://maps.google.com/maps?q="+getResources().getString(R.string.monterey_address);
				 		    			  accessLink(url1);
				 					}
				 				});
								customDialog.show();
								break;
							}
							break;
						case 3:
							switch (childPosition) {
							case 0:
								title.setText(R.string.waynesboro_title);
								place.setText(R.string.waynesboro_place);
								address.setText(R.string.waynesboro_address);
								phone.setText(R.string.waynesboro_phone);
								time.setText(R.string.waynesboro_time);
								instruct.setText(R.string.waynesboro_instructors);
								
								instruct.setOnClickListener(new OnClickListener() {
									
									public void onClick(View v) {
										Intent i = new Intent(Intent.ACTION_SEND);
										i.setType("message/rfc822");
										i.putExtra(Intent.EXTRA_EMAIL  , new String[]{getResources().getString(R.string.waynesboro_email)});
										i.putExtra(Intent.EXTRA_SUBJECT, "");
										i.putExtra(Intent.EXTRA_TEXT   , "");
										try {
										    startActivity(Intent.createChooser(i, "Send mail..."));
										} catch (android.content.ActivityNotFoundException ex) {
										    Toast.makeText(LocationsActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
										}
										
									}
								});
				        	    
				 				dirButton.setOnClickListener(new OnClickListener() {
				 					public void onClick(View v) {
				 						final String url1 = "https://maps.google.com/maps?q="+getResources().getString(R.string.waynesboro_address);
				 		    			  accessLink(url1);
				 					}
				 				});
								customDialog.show();
							break;
							}
						break;
						}
						return false;
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
    		LocationsActivity.this.finish();
    	}
    	
    	return true;
    }
    

    public class customListAdapter extends BaseExpandableListAdapter {

        private String[] groups = { "Adult Learning Center", "Massanutten Technical Center", "Rockbridge Regional Adult Education", "Building Blocks","Distance Learning","Other Virginia Locations"};
        private String[][] children = { {"Augusta","Staunton","Craigsville"}, {"Harrisonburg (MTC)","Harrisonburg (VWC)","Elkton","Timberville"}, {"Lexington","Hot Springs (Bath)","Buena Vista (DSLCC)","Buena Vista (Library)","Goshen","Glasgow","Monterey"},{"Waynesboro"},{""},{""}};

        public Object getChild(int groupPosition, int childPosition) {
            return children[groupPosition][childPosition];
        }

        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        public int getChildrenCount(int groupPosition) {
            return children[groupPosition].length;
        }

        public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                                    View convertView, ViewGroup parent) {

            TextView textView = new TextView(LocationsActivity.this);
            textView.setBackgroundColor(Color.WHITE);
            textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
            textView.setPadding(100, 15, 15, 15);
            textView.setTextColor(getResources().getColorStateList(R.color.selector_text));
            textView.setTextSize(25);
            textView.setText(getChild(groupPosition, childPosition).toString());
            return textView;
        }//getChildView

        public Object getGroup(int groupPosition) {
            return groups[groupPosition];
        }

        public int getGroupCount() {
            return groups.length;
        }

        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        public View getGroupView(int groupPosition, boolean isExpanded, View convertView,
                ViewGroup parent) {
        	//parent.setBackgroundColor(R.color.selector);
        	 TextView textView = new TextView(LocationsActivity.this);
            textView.setBackgroundColor(Color.WHITE);
            textView.setTextColor(getResources().getColorStateList(R.color.selector_text));
            textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
            textView.setPadding(100, 15, 15, 15);
            
            textView.setTextSize(36);
            textView.setText(getGroup(groupPosition).toString());

            return textView;
        }

        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }

        public boolean hasStableIds() {
            return true;
        }

    }
}

