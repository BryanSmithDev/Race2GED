package edu.shenandoah.ged;


import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomDialog extends Dialog {
	
    TextView title;
    TextView message;
    ImageView icon;
    Button noButton;
	Button yesButton;
	ImageButton doneButton;

	protected CustomDialog(Context context) {
		super(context);
	    requestWindowFeature(Window.FEATURE_NO_TITLE);
	    getWindow().setBackgroundDrawableResource(android.R.color.transparent);
		setContentView(R.layout.continue_cancel_popup);
		setCancelable(false);
		
	    title = (TextView)findViewById(R.id.titleName);
	    message = (TextView)findViewById(R.id.message);
	    icon = (ImageView)findViewById(R.id.icon);
	    noButton = (Button)findViewById(R.id.noButton);
    	yesButton = (Button)findViewById(R.id.yesButton);
    	doneButton = (ImageButton)findViewById(R.id.closeButton);
    	
    	title.setVisibility(View.GONE);
    	message.setVisibility(View.GONE);
    	icon.setVisibility(View.GONE);
    	yesButton.setVisibility(View.GONE);
    	noButton.setVisibility(View.GONE);
    	
    	setNoButtonAction(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CustomDialog.this.cancel();
			}
		});
    	
    	setCloseButtonAction(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CustomDialog.this.cancel();
			}
		});
    	
		
	}
	
	protected CustomDialog(Context context, boolean cancelable,
			OnCancelListener cancelListener) {
		super(context, cancelable, cancelListener);
	    requestWindowFeature(Window.FEATURE_NO_TITLE);
	    getWindow().setBackgroundDrawableResource(android.R.color.transparent);
		setContentView(R.layout.continue_cancel_popup);
		
		
	    title = (TextView)findViewById(R.id.titleName);
	    message = (TextView)findViewById(R.id.message);
	    icon = (ImageView)findViewById(R.id.icon);
	    noButton = (Button)findViewById(R.id.noButton);
    	yesButton = (Button)findViewById(R.id.yesButton);
    	doneButton = (ImageButton)findViewById(R.id.closeButton);
    	
    	title.setVisibility(View.GONE);
    	message.setVisibility(View.GONE);
    	icon.setVisibility(View.GONE);
    	yesButton.setVisibility(View.GONE);
    	noButton.setVisibility(View.GONE);
    	
    	setNoButtonAction(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CustomDialog.this.cancel();
			}
		});
    	
    	setCloseButtonAction(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CustomDialog.this.cancel();
			}
		});
		
	}

	public void setYesButtonAction(String buttonText,View.OnClickListener onClickListener) {
		yesButton.setText(buttonText);
		setYesButtonVisibility(true);
		yesButton.setOnClickListener(onClickListener);
	}
	
	public void setYesButtonAction(int buttonID,View.OnClickListener onClickListener) {
		yesButton.setText(buttonID);
		setYesButtonVisibility(true);
		yesButton.setOnClickListener(onClickListener);
	}
	
	
	public void setYesButtonAction(View.OnClickListener onClickListener) {
		setYesButtonVisibility(true);
		yesButton.setOnClickListener(onClickListener);
	}
	
	public void setNoButtonAction(View.OnClickListener onClickListener) {
		setNoButtonVisibility(true);
		noButton.setOnClickListener(onClickListener);
	}
	
	public void setNoButtonAction(String buttonText,View.OnClickListener onClickListener) {
		noButton.setText(buttonText);
		setNoButtonVisibility(true);
		noButton.setOnClickListener(onClickListener);
	}
	
	public void setNoButtonAction(int buttonID,View.OnClickListener onClickListener) {
		noButton.setText(buttonID);
		setNoButtonVisibility(true);
		noButton.setOnClickListener(onClickListener);
	}
	
	public void setCloseButtonAction(View.OnClickListener onClickListener) {
		doneButton.setVisibility(View.VISIBLE);
		doneButton.setOnClickListener(onClickListener);
	}
	
	public void setYesButtonVisibility(boolean visible) {
		if (visible){
			yesButton.setVisibility(View.VISIBLE);
		} else {
			yesButton.setVisibility(View.GONE);
		}
	}
	
	public void setNoButtonVisibility(boolean visible) {
		if (visible){
			noButton.setVisibility(View.VISIBLE);
		} else {
			noButton.setVisibility(View.GONE);
		}
	}
	
	public void setCloseButtonVisibility(boolean visible) {
		if (visible){
			doneButton.setVisibility(View.VISIBLE);
		} else {
			doneButton.setVisibility(View.GONE);
		}
	}
	
	public void setYesButtonWidth(int sizeInDP) {
		DisplayMetrics metrics = getContext().getResources().getDisplayMetrics();
		float dp = 20f;
		//float fpixels = metrics.density * dp;
		int pixels = (int) (metrics.density * dp + 0.5f);
		
		yesButton.setWidth(pixels);
	}
	
	public void setNoButtonWidth(int sizeInDP) {
		float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, sizeInDP, this.getContext().getResources().getDisplayMetrics());
		noButton.setWidth((int) px);
	}

	

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		cancel();
	}

	
	

	@Override
	public void setOnCancelListener(OnCancelListener listener) {
		// TODO Auto-generated method stub
		super.setOnCancelListener(listener);
	}

	
	public void setIcon(Drawable icon) {
		
		try {
			this.icon.setVisibility(View.VISIBLE);
			this.icon.setImageDrawable(icon);
		} catch (Exception e) {
			Log.e("CustomDialog", "Icon is probably null. - "+e.toString());
		}


	}

	

	public void setIcon(int iconID) {
		try {
			this.icon.setVisibility(View.VISIBLE);
			this.icon.setImageResource(iconID);
		} catch (Exception e) {
			Log.e("CustomDialog", "Icon is probably null. - "+e.toString());
		}
	}
	
	
	public void setMessage(CharSequence message) {
		
		try {
			this.message.setVisibility(View.VISIBLE);
			this.message.setText(message);
		} catch (Exception e) {
			Log.e("CustomDialog", "Message Object is probably null. - "+e.toString());
		}


	}

	

	public void setMessage(int messageID) {
		// TODO Auto-generated method stub
		try {
			this.message.setVisibility(View.VISIBLE);
			message.setText(messageID);
		} catch (Exception e) {
			Log.e("CustomDialog", "Message Object is probably null. - "+e.toString());
		}
	}

	@Override
	public void setTitle(CharSequence title) {
		
		try {
			
			this.title.setVisibility(View.VISIBLE);
			this.title.setText(title);
		} catch (Exception e) {
			Log.e("CustomDialog", "Title Object is probably null. - "+e.toString());
		}


	}

	
	

	@Override
	public void setTitle(int titleId) {
		// TODO Auto-generated method stub
		try {
			this.title.setVisibility(View.VISIBLE);
			this.title.setText(titleId);
		} catch (Exception e) {
			Log.e("CustomDialog", "Title Object is probably null. - "+e.toString());
		}
	}

}
