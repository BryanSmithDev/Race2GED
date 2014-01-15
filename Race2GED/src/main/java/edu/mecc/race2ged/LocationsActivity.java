package edu.mecc.race2ged;

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


public class LocationsActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
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
                if (id == 3) {

                    customDialog.setContentView(R.layout.distance_popup);
                    ImageButton doneButton = (ImageButton) customDialog.findViewById(R.id.closeButton);
                    doneButton.setOnClickListener(new OnClickListener() {
                        public void onClick(View v) {
                            customDialog.dismiss();
                        }
                    });
                    customDialog.show();

                } else if (id == 4) {
                    accessLink("http://www.valrc.org/providers/index.php");
                }
                return false;
            }
        });

        list.setOnChildClickListener(new OnChildClickListener() {

            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                switch (groupPosition) {
                    case 0: //Lee County
                        switch (childPosition) {
                            case 0: //Jonesville
                                customDialog.setContentView(R.layout.jonesville_popup);
                                ImageButton doneButton = (ImageButton) customDialog.findViewById(R.id.closeButton);
                                doneButton.setOnClickListener(new OnClickListener() {
                                    public void onClick(View v) {
                                        customDialog.dismiss();
                                    }
                                });
                                Button dirButton = (Button) customDialog.findViewById(R.id.dirButton);
                                dirButton.setOnClickListener(new OnClickListener() {
                                    public void onClick(View v) {
                                        final String url1 = "http://maps.google.com/maps/ms?source=s_q&hl=en&geocode=&aq=&ie=UTF8&hq=Adult+learning+center&hnear=Jonesville,+Lee,+Virginia&msa=0&msid=207439914853002129330.0004a0316737ceab20037&ll=36.690463,-83.111776&spn=0.005764,0.013078&z=17";
                                        accessLink(url1);
                                    }
                                });
                                customDialog.show();
                                break;
                            case 1: //Pennington Gap
                                customDialog.setContentView(R.layout.pennington_gap_popup);
                                doneButton = (ImageButton) customDialog.findViewById(R.id.closeButton);
                                doneButton.setOnClickListener(new OnClickListener() {
                                    public void onClick(View v) {
                                        customDialog.dismiss();
                                    }
                                });
                                dirButton = (Button) customDialog.findViewById(R.id.dirButton);
                                dirButton.setOnClickListener(new OnClickListener() {
                                    public void onClick(View v) {
                                        final String url1 = "http://maps.google.com/maps?f=q&source=s_q&hl=en&geocode=&q=Pennington+Gap+Middle+School+pennington+gap,+va&aq=&sll=36.884014,-82.792969&sspn=0.354242,0.837021&ie=UTF8&hq=Pennington+Gap+Middle+School&hnear=Pennington+Gap,+Lee,+Virginia&ll=36.762937,-83.016279&spn=0.011517,0.026157&z=16&iwloc=A";
                                        accessLink(url1);
                                    }
                                });
                                customDialog.show();
                                break;
                            case 2:  //Rose Hill
                                customDialog.setContentView(R.layout.rose_hill_popup);
                                doneButton = (ImageButton) customDialog.findViewById(R.id.closeButton);
                                doneButton.setOnClickListener(new OnClickListener() {
                                    public void onClick(View v) {
                                        customDialog.dismiss();
                                    }
                                });
                                dirButton = (Button) customDialog.findViewById(R.id.dirButton);
                                dirButton.setOnClickListener(new OnClickListener() {
                                    public void onClick(View v) {
                                        final String url1 = "http://maps.google.com/maps?hl=en&client=firefox-a&ie=UTF8&q=rose+hill+elementary+va+276-445&fb=1&gl=us&hq=rose+hill+elementary+va+276-445&hnear=rose+hill+elementary+va+276-445&view=map&cid=17451806032730576993&sll=36.676753,-83.364072&sspn=0.006295,0.006295&z=16&iwloc=A";
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
                                customDialog.setContentView(R.layout.duffield_popup);
                                ImageButton doneButton = (ImageButton) customDialog.findViewById(R.id.closeButton);
                                doneButton.setOnClickListener(new OnClickListener() {
                                    public void onClick(View v) {
                                        customDialog.dismiss();
                                    }
                                });
                                Button dirButton = (Button) customDialog.findViewById(R.id.dirButton);
                                dirButton.setOnClickListener(new OnClickListener() {
                                    public void onClick(View v) {
                                        final String url1 = "http://maps.google.com/maps?oe=utf-8&client=firefox-a&ie=UTF8&q=Pioneer+center+431-7226&fb=1&gl=us&hq=Pioneer+center+431-7226&hnear=Johnson+City,+TN&hl=en&view=map&cid=5508938580285505504&ll=36.719606,-82.802732&spn=0.009838,0.026157&z=16&iwloc=A";
                                        accessLink(url1);
                                    }
                                });
                                customDialog.show();
                                break;
                            case 1:
                                customDialog.setContentView(R.layout.gate_city_popup);
                                doneButton = (ImageButton) customDialog.findViewById(R.id.closeButton);
                                doneButton.setOnClickListener(new OnClickListener() {
                                    public void onClick(View v) {
                                        customDialog.dismiss();
                                    }
                                });
                                dirButton = (Button) customDialog.findViewById(R.id.dirButton);
                                dirButton.setOnClickListener(new OnClickListener() {
                                    public void onClick(View v) {
                                        final String url1 = "http://maps.google.com/maps/ms?msid=207439914853002129330.0004ac7377c17c932798e&msa=0";
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
                                customDialog.setContentView(R.layout.appalachia_popup);
                                ImageButton doneButton = (ImageButton) customDialog.findViewById(R.id.closeButton);
                                doneButton.setOnClickListener(new OnClickListener() {
                                    public void onClick(View v) {
                                        customDialog.dismiss();
                                    }
                                });
                                Button dirButton = (Button) customDialog.findViewById(R.id.dirButton);
                                dirButton.setOnClickListener(new OnClickListener() {
                                    public void onClick(View v) {
                                        final String url1 = "http://maps.google.com/maps?q=205+Lee+Street,+Appalachia,+VA+24216&hl=en&ll=36.925743,-82.772369&spn=0.744326,1.674042&sll=36.763349,-83.016279&sspn=0.012153,0.026157&vpsrc=6&hnear=205+Lee+St,+Appalachia,+Virginia+24216&t=m&z=10&iwloc=A";
                                        accessLink(url1);
                                    }
                                });
                                customDialog.show();
                                break;
                            case 1:
                                customDialog.setContentView(R.layout.big_stone_gap_popup);
                                doneButton = (ImageButton) customDialog.findViewById(R.id.closeButton);
                                doneButton.setOnClickListener(new OnClickListener() {
                                    public void onClick(View v) {
                                        customDialog.dismiss();
                                    }
                                });
                                dirButton = (Button) customDialog.findViewById(R.id.dirButton);
                                dirButton.setOnClickListener(new OnClickListener() {
                                    public void onClick(View v) {
                                        final String url1 = "http://maps.google.com/maps?hl=en&client=firefox-a&ie=UTF8&q=Lonesome+Pine+Office+on+Youth+523-5064&fb=1&gl=us&hq=Lonesome+Pine+Office+on+Youth+523-5064&hnear=Johnson+City,+TN&view=map&cid=7674766261979639707&sll=36.865986,-82.77639&sspn=0.006295,0.006295&ll=36.866335,-82.776382&spn=0.00939,0.026157&z=16&iwloc=A";
                                        accessLink(url1);
                                    }
                                });
                                customDialog.show();
                                break;
                            case 2:
                                customDialog.setContentView(R.layout.coeburn_popup);
                                doneButton = (ImageButton) customDialog.findViewById(R.id.closeButton);
                                doneButton.setOnClickListener(new OnClickListener() {
                                    public void onClick(View v) {
                                        customDialog.dismiss();
                                    }
                                });
                                customDialog.show();
                                break;
                            case 3:
                                customDialog.setContentView(R.layout.norton_popup);
                                doneButton = (ImageButton) customDialog.findViewById(R.id.closeButton);
                                doneButton.setOnClickListener(new OnClickListener() {
                                    public void onClick(View v) {
                                        customDialog.dismiss();
                                    }
                                });
                                dirButton = (Button) customDialog.findViewById(R.id.dirButton);
                                dirButton.setOnClickListener(new OnClickListener() {
                                    public void onClick(View v) {
                                        final String url1 = "https://maps.google.com/maps?q=John+I+Burton+High+School,+Norton,+VA&hl=en&ll=36.930409,-82.633324&spn=0.009417,0.017016&sll=36.593479,-82.482605&sspn=1.210622,2.17804&oq=John+I+Burton+&t=h&hq=John+I+Burton+High+School,+Norton,+VA&z=16&iwloc=A";
                                        accessLink(url1);
                                    }
                                });
                                customDialog.show();
                                break;
                            case 4:
                                customDialog.setContentView(R.layout.pound_popup);
                                doneButton = (ImageButton) customDialog.findViewById(R.id.closeButton);
                                doneButton.setOnClickListener(new OnClickListener() {
                                    public void onClick(View v) {
                                        customDialog.dismiss();
                                    }
                                });
                                dirButton = (Button) customDialog.findViewById(R.id.dirButton);
                                dirButton.setOnClickListener(new OnClickListener() {
                                    public void onClick(View v) {
                                        final String url1 = "http://maps.google.com/maps?oe=utf-8&client=firefox-a&ie=UTF8&q=pound+town+hall+virginia&fb=1&gl=us&hq=pound+town+hall&hnear=Virginia&hl=en&view=map&cid=1862178960033536001&z=16&iwloc=A";
                                        accessLink(url1);
                                    }
                                });
                                customDialog.show();
                                break;
                            case 5:
                                customDialog.setContentView(R.layout.st_paul_popup);
                                doneButton = (ImageButton) customDialog.findViewById(R.id.closeButton);
                                doneButton.setOnClickListener(new OnClickListener() {
                                    public void onClick(View v) {
                                        customDialog.dismiss();
                                    }
                                });
                                dirButton = (Button) customDialog.findViewById(R.id.dirButton);
                                dirButton.setOnClickListener(new OnClickListener() {
                                    public void onClick(View v) {
                                        final String url1 = "http://maps.google.com/maps?q=3103+Decan+Dr,+St+Paul+VA&hl=en&ll=36.906295,-82.310437&spn=0.001821,0.003484&hq=3103+Decan+Dr,&hnear=St+Paul,+Wise,+Virginia&t=h&z=19";
                                        accessLink(url1);
                                    }
                                });
                                customDialog.show();
                                break;
                            case 6:
                                customDialog.setContentView(R.layout.wise_popup);
                                doneButton = (ImageButton) customDialog.findViewById(R.id.closeButton);
                                doneButton.setOnClickListener(new OnClickListener() {
                                    public void onClick(View v) {
                                        customDialog.dismiss();
                                    }
                                });
                                dirButton = (Button) customDialog.findViewById(R.id.dirButton);
                                dirButton.setOnClickListener(new OnClickListener() {
                                    public void onClick(View v) {
                                        String url1 = "http://maps.google.com/maps?f=q&source=s_q&hl=en&geocode=&q=515+Hurrican+Road+Wise,+VA+24293&aq=&sll=36.976501,-82.575731&sspn=0.045941,0.104628&g=Wise,+Virginia&ie=UTF8&hq=&hnear=515+Hurricane+Rd+NE,+Wise,+Virginia+24293&z=16&iwloc=A";
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
                LocationsActivity.this.finish();
        }

        return true;
    }


    public class customListAdapter extends BaseExpandableListAdapter {

        private String[] groups = {"Lee", "Scott", "Wise/Norton", "Distance Learning", "Other Virginia Locations"};
        private String[][] children = {{"Jonesville", "Pennington Gap", "Rose Hill"}, {"Duffield", "Gate City"}, {"Appalachia", "Big Stone Gap", "Coeburn", "Norton", "Pound", "St Paul", "Wise"}, {""}, {""}};

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

