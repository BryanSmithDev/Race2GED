package edu.mecc.race2ged;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.HashMap;

public class VideoTestimonialActivity extends Activity {

    private SoundPool mSoundPool;
    private HashMap<Integer, Integer> mSoundPoolMap;
    private AudioManager mAudioManager;
    private Context mContext;
    boolean playing = true;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.vidtest_layout);

        final Dialog customDialog = new Dialog(this);
        customDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        customDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


        customDialog.setContentView(R.layout.media_controller);

        initSounds(getBaseContext());


        final ImageButton stopButton = (ImageButton) customDialog.findViewById(R.id.stopButton);
        ImageButton doneButton = (ImageButton) customDialog.findViewById(R.id.closeButton);
        doneButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                stopSounds();
                customDialog.dismiss();
            }
        });

        int maxVolume = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int curVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        SeekBar volControl = (SeekBar) customDialog.findViewById(R.id.leftVolume);
        volControl.setMax(maxVolume);
        volControl.setProgress(curVolume);
        volControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {


            public void onStopTrackingTouch(SeekBar arg0) {
                // TODO Auto-generated method stub
            }

            public void onStartTrackingTouch(SeekBar arg0) {
                // TODO Auto-generated method stub
            }

            public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
                // TODO Auto-generated method stub
                mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, arg1, 0);
            }
        });


        addSound(0, R.raw.ae_promotional_voice_over);
        addSound(1, R.raw.channie);
        addSound(2, R.raw.travis);
        addSound(3, R.raw.tammy);
        addSound(4, R.raw.rebecca);
        addSound(5, R.raw.sandy);
        addSound(6, R.raw.jan);
        addSound(7, R.raw.liz);
        addSound(8, R.raw.ramsey);
        addSound(9, R.raw.tony);

        ListView list = (ListView) findViewById(R.id.listView1);

        final String[] mTestArray = (String[]) getResources().getStringArray(R.array.videosAndTestimonials);
        ArrayAdapter<String> aa = new ArrayAdapter<String>(getApplicationContext(),
                R.layout.listview_layouts, mTestArray);
        list.setAdapter(aa);
        list.invalidate();

        list.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    final int position, long id) {
                if (position == 0) {
                    final CustomDialog dialog = new CustomDialog(VideoTestimonialActivity.this);
                    dialog.setTitle(R.string.do_you_want_to_continue);
                    dialog.setMessage(R.string.external_website);
                    dialog.setYesButtonAction(new View.OnClickListener() {
                        public void onClick(View v) {
                            Intent nIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?feature=player_embedded&v=lxGF_J5mNqg"));
                            startActivity(nIntent);
                            dialog.cancel();
                        }
                    });
                    dialog.show();

                } else {
                    playing = true;
                    stopButton.setImageResource(android.R.drawable.ic_media_pause);

                    playSound(position - 1);
                    TextView title = (TextView) customDialog.findViewById(R.id.titleName);
                    title.setText(mTestArray[position].toString());

                    stopButton.setOnClickListener(new OnClickListener() {
                        public void onClick(View v) {
                            if (playing) {
                                mSoundPool.autoPause();
                                playing = false;
                                stopButton.setImageResource(android.R.drawable.ic_media_play);
                            } else {
                                mSoundPool.autoResume();
                                playing = true;
                                stopButton.setImageResource(android.R.drawable.ic_media_pause);
                            }
                        }
                    });

                    customDialog.setOnDismissListener(new OnDismissListener() {

                        public void onDismiss(DialogInterface dialog) {
                            // TODO Auto-generated method stub
                            stopSound(position - 1);
                            playing = false;
                        }
                    });
                    customDialog.show();
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
                VideoTestimonialActivity.this.finish();
                stopSounds();
        }

        return true;
    }

    private void initSounds(Context theContext) {
        mContext = theContext;
        mSoundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        mSoundPoolMap = new HashMap<Integer, Integer>();
        mAudioManager = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);
    }

    private void addSound(int index, int SoundID) {
        mSoundPoolMap.put(index, mSoundPool.load(mContext, SoundID, 1));
    }

    private void playSound(int index) {
        float streamVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        streamVolume = streamVolume / mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        mSoundPool.play((Integer) mSoundPoolMap.get(index), streamVolume, streamVolume, 1, 0, 1f);
        playing = true;
    }

    private void stopSound(int index) {
        mSoundPool.stop((Integer) mSoundPoolMap.get(index));
        playing = false;
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (Integer.parseInt(android.os.Build.VERSION.SDK) < 5
                && keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            onBackPressed();
        }

        return super.onKeyDown(keyCode, event);
    }

    public void onBackPressed() {
        stopSounds();
        VideoTestimonialActivity.this.finish();

        return;
    }

    private void stopSounds() {
        for (int i = 0; i < 10; i++) {
            mSoundPool.stop((Integer) mSoundPoolMap.get(i));
            playing = false;
        }
    }
}