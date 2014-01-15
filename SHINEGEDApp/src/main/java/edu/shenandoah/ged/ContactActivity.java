package edu.shenandoah.ged;

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
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class ContactActivity extends Activity {

	// String url1 = "http://www.youtube.com/regionaladulted";
	String url2 = "http://www.facebook.com/pages/Shenandoah-Initiative-for-Adult-Education/395174060553616?fref=ts";
	// String url3 = "http://twitter.com/#!/race2gednow";
	String url4 = "http://www.waynesboro.k12.va.us/main/centralshenandoahabe/";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.contact_layout);

		// TextView toll = (TextView) findViewById(R.id.tollFree);

		TextView version = (TextView) findViewById(R.id.version);
		try {
			version.setText("Version: "
					+ getPackageManager().getPackageInfo(getPackageName(), 0).versionName);
		} catch (NameNotFoundException e) {
			version.setText("Version: 0.0");
			e.printStackTrace();
		}

		// toll.setText(String.format(getResources().getString(R.string.tollFreeLabel),
		// getResources().getString(R.string.tollFree)));

		// ImageButton youtube = (ImageButton) findViewById(R.id.youtube);
		ImageButton facebook = (ImageButton) findViewById(R.id.facebook);
		// ImageButton twitter = (ImageButton) findViewById(R.id.twitter);
		ImageButton webIcon = (ImageButton) findViewById(R.id.webIcon);

		Button loc1Phone = (Button) findViewById(R.id.locality1_phone);
		Button loc1url = (Button) findViewById(R.id.locality1_url);

		Button loc2Phone = (Button) findViewById(R.id.locality2_phone);
		Button loc2url = (Button) findViewById(R.id.locality2_url);

		Button loc3Phone = (Button) findViewById(R.id.locality3_phone);
		Button loc3url = (Button) findViewById(R.id.locality3_url);

		Button loc4Phone = (Button) findViewById(R.id.locality4_phone);
		Button loc4url = (Button) findViewById(R.id.locality4_url);

		loc1Phone.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				try {
					Intent intent = new Intent(Intent.ACTION_DIAL);
					intent.setData(Uri.parse("tel:"
							+ getResources()
									.getString(R.string.locality1_phone)));
					startActivity(intent);
				} catch (Exception e) {
					Toast.makeText(getApplicationContext(),
							"ERROR: No application found to handle this data.",
							Toast.LENGTH_SHORT).show();
				}
			}
		});

		loc2Phone.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				try {
					Intent intent = new Intent(Intent.ACTION_DIAL);
					intent.setData(Uri.parse("tel:"
							+ getResources()
									.getString(R.string.locality2_phone)));
					startActivity(intent);
				} catch (Exception e) {
					Toast.makeText(getApplicationContext(),
							"ERROR: No application found to handle this data.",
							Toast.LENGTH_SHORT).show();
				}
			}
		});

		loc3Phone.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				try {
					Intent intent = new Intent(Intent.ACTION_DIAL);
					intent.setData(Uri.parse("tel:"
							+ getResources()
									.getString(R.string.locality3_phone)));
					startActivity(intent);
				} catch (Exception e) {
					Toast.makeText(getApplicationContext(),
							"ERROR: No application found to handle this data.",
							Toast.LENGTH_SHORT).show();
				}
			}
		});

		loc4Phone.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				try {
					Intent intent = new Intent(Intent.ACTION_DIAL);
					intent.setData(Uri.parse("tel:"
							+ getResources()
									.getString(R.string.locality4_phone)));
					startActivity(intent);
				} catch (Exception e) {
					Toast.makeText(getApplicationContext(),
							"ERROR: No application found to handle this data.",
							Toast.LENGTH_SHORT).show();
				}
			}
		});
		loc1url.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				try {
					final CustomDialog dialog = new CustomDialog(
							ContactActivity.this);
					dialog.setTitle(R.string.do_you_want_to_continue);
					dialog.setMessage(R.string.external_website);
					dialog.setYesButtonAction(new View.OnClickListener() {
						public void onClick(View v) {
							Intent nIntent = new Intent(Intent.ACTION_VIEW, Uri
									.parse(getResources().getString(
											R.string.locality1_url)));
							startActivity(nIntent);
							dialog.cancel();
						}
					});
					dialog.show();
				} catch (Exception e) {
					Toast.makeText(getApplicationContext(),
							"ERROR: No application found to handle this data.",
							Toast.LENGTH_SHORT).show();
				}
			}
		}

		);

		loc2url.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				try {
					final CustomDialog dialog = new CustomDialog(
							ContactActivity.this);
					dialog.setTitle(R.string.do_you_want_to_continue);
					dialog.setMessage(R.string.external_website);
					dialog.setYesButtonAction(new View.OnClickListener() {
						public void onClick(View v) {
							Intent nIntent = new Intent(Intent.ACTION_VIEW, Uri
									.parse(getResources().getString(
											R.string.locality2_url)));
							startActivity(nIntent);
							dialog.cancel();
						}
					});
					dialog.show();
				} catch (Exception e) {
					Toast.makeText(getApplicationContext(),
							"ERROR: No application found to handle this data.",
							Toast.LENGTH_SHORT).show();
				}
			}
		}

		);

		loc3url.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				try {
					final CustomDialog dialog = new CustomDialog(
							ContactActivity.this);
					dialog.setTitle(R.string.do_you_want_to_continue);
					dialog.setMessage(R.string.external_website);
					dialog.setYesButtonAction(new View.OnClickListener() {
						public void onClick(View v) {
							Intent nIntent = new Intent(Intent.ACTION_VIEW, Uri
									.parse(getResources().getString(
											R.string.locality3_url)));
							startActivity(nIntent);
							dialog.cancel();
						}
					});
					dialog.show();
				} catch (Exception e) {
					Toast.makeText(getApplicationContext(),
							"ERROR: No application found to handle this data.",
							Toast.LENGTH_SHORT).show();
				}
			}
		}

		);

		loc4url.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				Toast.makeText(getApplicationContext(),
						"Website Currently Under Construction",
						Toast.LENGTH_SHORT).show();
			}
		}

		);

		webIcon.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				try {
					final CustomDialog dialog = new CustomDialog(
							ContactActivity.this);
					dialog.setTitle(R.string.do_you_want_to_continue);
					dialog.setMessage(R.string.external_website);
					dialog.setYesButtonAction(new View.OnClickListener() {
						public void onClick(View v) {
							Intent nIntent = new Intent(Intent.ACTION_VIEW, Uri
									.parse(url4));
							startActivity(nIntent);
							dialog.cancel();
						}
					});
					dialog.show();
				} catch (Exception e) {
					Toast.makeText(getApplicationContext(),
							"ERROR: No application found to handle this data.",
							Toast.LENGTH_SHORT).show();
				}
			}
		}

		);

		/*
		 * youtube.setOnClickListener(new View.OnClickListener() {
		 * 
		 * public void onClick(View v) {
		 * 
		 * final CustomDialog dialog = new CustomDialog(ContactActivity.this);
		 * dialog.setTitle(R.string.do_you_want_to_continue);
		 * dialog.setMessage(R.string.external_website);
		 * dialog.setYesButtonAction(new View.OnClickListener() { public void
		 * onClick(View v) { Intent nIntent = new
		 * Intent(Intent.ACTION_VIEW,Uri.parse(url1)); startActivity(nIntent);
		 * dialog.cancel(); } }); dialog.show();
		 * 
		 * } }
		 * 
		 * );
		 */
		facebook.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				try {
					final CustomDialog dialog = new CustomDialog(
							ContactActivity.this);
					dialog.setTitle(R.string.do_you_want_to_continue);
					dialog.setMessage(R.string.external_website);
					dialog.setYesButtonAction(new View.OnClickListener() {
						public void onClick(View v) {
							Intent nIntent = new Intent(Intent.ACTION_VIEW, Uri
									.parse(url2));
							startActivity(nIntent);
							dialog.cancel();
						}
					});
					dialog.show();
				} catch (Exception e) {
					Toast.makeText(getApplicationContext(),
							"ERROR: No application found to handle this data.",
							Toast.LENGTH_SHORT).show();
				}
			}
		});
		/*
		 * twitter.setOnClickListener(new View.OnClickListener() {
		 * 
		 * public void onClick(View v) {
		 * 
		 * final CustomDialog dialog = new CustomDialog(ContactActivity.this);
		 * dialog.setTitle(R.string.do_you_want_to_continue);
		 * dialog.setMessage(R.string.external_website);
		 * dialog.setYesButtonAction(new View.OnClickListener() { public void
		 * onClick(View v) { Intent nIntent = new
		 * Intent(Intent.ACTION_VIEW,Uri.parse(url3)); startActivity(nIntent);
		 * dialog.cancel(); } }); dialog.show(); } });
		 */

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
