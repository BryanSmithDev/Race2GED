package edu.shenandoah.ged;

import android.app.Activity;
import android.app.Dialog;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

public class MathMultipleActivity extends Activity {
	
	private static final int DIALOG_ID_NO_SELECTION = 1;
	private static final int DIALOG_ID_CORRECT_SELECTION = 2;
	private static final int DIALOG_ID_WRONG_SELECTION = 3;
	private static final int DIALOG_ID_MAX_ATTEMPTS = 4;
	private static final int DIALOG_ID_ERROR_DURING_SELECTION = 5;
	private static final int DIALOG_ID_COMPLETED_QUESTIONS = 6;
	
	TextView quest_p1, quest_p2, a1, a2, a3, a4, a5;
	String correctAnswer, selectedAnswer;
	Button additional_info, b1, b2, b3, b4, b5, chkAnswer;
	ImageView qImage;
	int qNum, attempts, maxAttempts;
	MediaPlayer mp;
	ScrollView scrollView;
	
	public int getRemaining()
	{
		return (maxAttempts - attempts);
	}
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.multiplechoice_layout);   
        scrollView = (ScrollView) findViewById(R.id.scrollView1);
        
        correctAnswer = "";
        selectedAnswer = "";
        qNum = 1;
        attempts = 0;
        maxAttempts = 0;
        
        additional_info = (Button) findViewById(R.id.additionalInfo_btn);
        
        quest_p1 = (TextView) findViewById(R.id.multi_qp1);
        quest_p1.setText("");
        quest_p2 = (TextView) findViewById(R.id.multi_qp2);
        quest_p2.setText("");
        
        a1 = (TextView) findViewById(R.id.multi_first);
        a1.setText("");
        a2 = (TextView) findViewById(R.id.multi_second);
        a2.setText("");
        a3 = (TextView) findViewById(R.id.multi_third);
        a3.setText("");
        a4 = (TextView) findViewById(R.id.multi_fourth);
        a4.setText("");
        a5 = (TextView) findViewById(R.id.multi_fifth);
        a5.setText("");
        
        qImage = (ImageView) findViewById(R.id.multi_qimage);
        qImage.setImageResource(R.drawable.trans);
        
        b1 = (Button) findViewById(R.id.multi_firstchoice);
        b1.setPressed(false);
        b1.setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				b1.setPressed(true);
        		b2.setPressed(false);
        		b3.setPressed(false);
        		b4.setPressed(false);
        		b5.setPressed(false);
        		
        		selectedAnswer = a1.getText().toString();
        		chkAnswer.setText("Check Answer");
        		return true;
			}
        });
        b2 = (Button) findViewById(R.id.multi_secondchoice);
        b2.setPressed(false);
        b2.setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				b1.setPressed(false);
        		b2.setPressed(true);
        		b3.setPressed(false);
        		b4.setPressed(false);
        		b5.setPressed(false);
        		
        		selectedAnswer = a2.getText().toString();
        		chkAnswer.setText("Check Answer");
        		return true;
			}
        });
        b3 = (Button) findViewById(R.id.multi_thirdchoice);
        b3.setPressed(false);
        b3.setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				b1.setPressed(false);
        		b2.setPressed(false);
        		b3.setPressed(true);
        		b4.setPressed(false);
        		b5.setPressed(false);
        		
        		selectedAnswer = a3.getText().toString();
        		chkAnswer.setText("Check Answer");
        		return true;
			}
        });
        b4 = (Button) findViewById(R.id.multi_fourthchoice);
        b4.setPressed(false);
        b4.setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				b1.setPressed(false);
        		b2.setPressed(false);
        		b3.setPressed(false);
        		b4.setPressed(true);
        		b5.setPressed(false);
        		
        		selectedAnswer = a4.getText().toString();
        		chkAnswer.setText("Check Answer");
        		return true;
			}
        });
        b5 = (Button) findViewById(R.id.multi_fifthchoice);
        b5.setPressed(false);
        b5.setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				b1.setPressed(false);
        		b2.setPressed(false);
        		b3.setPressed(false);
        		b4.setPressed(false);
        		b5.setPressed(true);
        		
        		selectedAnswer = a5.getText().toString();
        		chkAnswer.setText("Check Answer");
        		return true;
			}
        });
        
        chkAnswer = (Button) findViewById(R.id.multi_chkanswer);
        chkAnswer.setText("Choose an Answer");
        
        chkAnswer.setOnClickListener(new OnClickListener()
        {
        	int id = 0;
        	
        	public void onClick(View view) {
        		try{
	        		if (selectedAnswer == "")
	        		{
	        			id = DIALOG_ID_NO_SELECTION;
	        			
	        			mp = MediaPlayer.create(MathMultipleActivity.this, R.raw.noanswer);
	        			mp.start();
	        			mp.setOnCompletionListener(new OnCompletionListener() {
	        				public void onCompletion(MediaPlayer mp) {
	        					mp.release();
	        				}
	        			});
	        		}
	        		else if (selectedAnswer == correctAnswer)
	        		{
	        			id = DIALOG_ID_CORRECT_SELECTION;
	        			
	        			
		        			mp = MediaPlayer.create(MathMultipleActivity.this, R.raw.correct);
		        			mp.start();
		        			mp.setOnCompletionListener(new OnCompletionListener() {
		        				public void onCompletion(MediaPlayer mp) {
		        					mp.release();
		        				}
		        			});
	        			
	        		}
	        		else if (selectedAnswer != correctAnswer)
	        		{
	        			if (attempts < maxAttempts)
	        			{
		        			id = DIALOG_ID_WRONG_SELECTION;
		        			
		        			mp = MediaPlayer.create(MathMultipleActivity.this, R.raw.incorrect);
		        			mp.start();
		        			mp.setOnCompletionListener(new OnCompletionListener() {
		        				public void onCompletion(MediaPlayer mp) {
		        					mp.release();
		        				}
		        			});
	        			}
	        			else
	        			{
	        				id = DIALOG_ID_MAX_ATTEMPTS;
	        				
	        				mp = MediaPlayer.create(MathMultipleActivity.this, R.raw.incorrect);
		        			mp.start();
		        			mp.setOnCompletionListener(new OnCompletionListener() {
		        				public void onCompletion(MediaPlayer mp) {
		        					mp.release();
		        				}
		        			});
	        			}
	        		}
	        		else
	        			id = DIALOG_ID_ERROR_DURING_SELECTION;
	        		
	        		showDialog(id);
        	} catch (Exception e){
        	}
        	}
        });
        
        setQuestionInfo(qNum);
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
    		MathMultipleActivity.this.finish();
    	}
    	
    	return true;
    }
    
    @Override
	protected Dialog onCreateDialog(int id) {
		super.onCreateDialog(id);
		Dialog dialog=null;
		

		
		switch(id) {
		case DIALOG_ID_NO_SELECTION:
			final CustomDialog noSelection = new CustomDialog(this);
			noSelection.setTitle(R.string.no_selection_title);
			noSelection.setMessage(R.string.no_selection_message);
			noSelection.setIcon(R.drawable.ex_icon);
			noSelection.setNoButtonVisibility(false);
			noSelection.setCloseButtonVisibility(false);
			noSelection.setYesButtonAction(R.string.no_selection_yes_button,new View.OnClickListener() {
					public void onClick(View v) {
						selectedAnswer = "";
						chkAnswer.setText(R.string.choose_answer);
						noSelection.cancel();
					}
			  });
		    dialog=noSelection;

			break;
		case DIALOG_ID_CORRECT_SELECTION:
			final CustomDialog correctSelection = new CustomDialog(this);
			correctSelection.setTitle(R.string.correct_selection_title);
			correctSelection.setMessage(R.string.correct_selection_message);
			correctSelection.setIcon(R.drawable.correct_icon);
			correctSelection.setNoButtonVisibility(false);
			correctSelection.setCloseButtonVisibility(false);
			correctSelection.setYesButtonAction(R.string.correct_selection_yes_button,new View.OnClickListener() {
					public void onClick(View v) {
						setQuestionInfo(qNum);
						scrollView.fullScroll(0);
						correctSelection.cancel();
					}
			  });
			dialog=correctSelection;
			break;
		case DIALOG_ID_WRONG_SELECTION:
			final CustomDialog wrongSelection = new CustomDialog(this);
			wrongSelection.setTitle(R.string.wrong_selection_title);
			wrongSelection.setMessage(getResources().getQuantityString(R.plurals.wrong_selection_message, getRemaining()));
			wrongSelection.setIcon(R.drawable.wrong_icon);
			wrongSelection.setNoButtonVisibility(false);
			wrongSelection.setCloseButtonVisibility(false);
			wrongSelection.setYesButtonAction(R.string.wrong_selection_yes_button,new View.OnClickListener() {
					public void onClick(View v) {
						selectedAnswer = "";
						chkAnswer.setText(R.string.choose_answer);
						attempts++;
						wrongSelection.cancel();
					}
			  });
			dialog=wrongSelection;
			break;
		case DIALOG_ID_MAX_ATTEMPTS:
			final CustomDialog maxAttempts = new CustomDialog(this);
			maxAttempts.setTitle(R.string.max_attempts_title);
			maxAttempts.setMessage(String.format(getResources().getString(R.string.max_attempts_message, correctAnswer)));
			maxAttempts.setIcon(R.drawable.wrong_icon);
			maxAttempts.setNoButtonVisibility(false);
			maxAttempts.setCloseButtonVisibility(false);
			maxAttempts.setYesButtonAction(R.string.max_attempts_yes_button,new View.OnClickListener() {
					public void onClick(View v) {
						setQuestionInfo(qNum);
						scrollView.fullScroll(0);
						maxAttempts.cancel();
					}
			  });
			dialog=maxAttempts;
			break;
		case DIALOG_ID_ERROR_DURING_SELECTION:
			final CustomDialog selectionError = new CustomDialog(this);
			selectionError.setTitle(R.string.selection_error_title);
			selectionError.setMessage(R.string.selection_error_message);
			selectionError.setIcon(R.drawable.ex_icon);
			selectionError.setNoButtonVisibility(false);
			selectionError.setCloseButtonVisibility(false);
			selectionError.setYesButtonAction(R.string.selection_error_yes_button,new View.OnClickListener() {
					public void onClick(View v) {
						selectedAnswer = "";
						chkAnswer.setText(R.string.choose_answer);
						setQuestionInfo(qNum - 1);
						scrollView.fullScroll(0);
						selectionError.cancel();
					}
			  });
			dialog=selectionError;
			break;
		case DIALOG_ID_COMPLETED_QUESTIONS:	
			final CustomDialog completedQuestions = new CustomDialog(this);
			completedQuestions.setTitle(R.string.completed_questions_title);
			completedQuestions.setMessage(R.string.completed_questions_message);
			completedQuestions.setIcon(R.drawable.correct_icon);
			completedQuestions.setCloseButtonVisibility(false);
			completedQuestions.setYesButtonAction(R.string.completed_questions_yes_button,new View.OnClickListener() {
					public void onClick(View v) {
						qNum = 1;
				        chkAnswer.setText(R.string.choose_answer);
						setQuestionInfo(qNum);
						scrollView.fullScroll(0);
						completedQuestions.cancel();
					}
			  });
			completedQuestions.setNoButtonAction(R.string.completed_questions_no_button, new View.OnClickListener() {
				
				public void onClick(View v) {
					MathMultipleActivity.this.finish();
					completedQuestions.cancel();
				}
			});
			dialog=completedQuestions;
			break;
		default:
			break;
		}
		
		return dialog;
	}
    
    @Override
    protected void onPrepareDialog(final int id, final Dialog dialog)
    {
    	switch(id)
    	{
    	case DIALOG_ID_WRONG_SELECTION:
    		((CustomDialog) dialog).setMessage(getResources().getQuantityString(R.plurals.wrong_selection_message, getRemaining()));
			break;
		case DIALOG_ID_MAX_ATTEMPTS:
			((CustomDialog) dialog).setMessage(String.format(getResources().getString(R.string.max_attempts_message, correctAnswer)));		
			break;
    	}
    }
    
    private void setQuestionInfo(int questionNum)
    {
    	additional_info.setVisibility(8);
    	qImage.setVisibility(0);
    	a1.setVisibility(0);
		a2.setVisibility(0);
		a3.setVisibility(0);
		a4.setVisibility(0);
		a5.setVisibility(0);
		b1.setVisibility(0);
		b2.setVisibility(0);
		b3.setVisibility(0);
		b4.setVisibility(0);
		b5.setVisibility(0);
		attempts = 1;
		maxAttempts = 2;
		
    	switch (questionNum)
    	{
    	case 1:
    		additional_info.setText("");
    		additional_info.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    
                }
            });
    		quest_p1.setText("A painter mixes gallons of paint in a large cylindrical bucket so " +
    				"that there will be no difference in color among individual gallons.");
    		qImage.setImageResource(R.drawable.mathmulti_one);
    		quest_p2.setText("If one gallon of paint has a volume of approximately 8000cm^3, what " +
    				"is the maximum number of whole gallons of paint that can be poured into the bucket?");
    		a1.setText("3");
    		a2.setText("7");
    		a3.setText("9");
    		a4.setText("11");
    		a5.setText("37");
    		correctAnswer = "9";
    		b1.setPressed(false);
    		b2.setPressed(false);
    		b3.setPressed(false);
    		b4.setPressed(false);
    		b5.setPressed(false);
    		qNum++;
            chkAnswer.setText("Choose an Answer");
            selectedAnswer = "";
    		break;
    	case 2:
    		additional_info.setText("");
    		additional_info.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    
                }
            });
    		quest_p1.setText("A surveyor made the measurements shown in the diagram below.");
    		qImage.setImageResource(R.drawable.stream_problem);
    		quest_p2.setText("What is the measure, in feet, of AB, the straight-line distance across the stream?");
    		a1.setText("50");
    		a2.setText("75");
    		a3.setText("80");
    		a4.setText("100");
    		a5.setText("150");
    		correctAnswer = "100";
    		b1.setPressed(false);
    		b2.setPressed(false);
    		b3.setPressed(false);
    		b4.setPressed(false);
    		b5.setPressed(false);
    		qNum++;
            chkAnswer.setText("Choose an Answer");
            selectedAnswer = "";
    		break;
    	case 3:
    		additional_info.setText("");
    		additional_info.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    
                }
            });
    		quest_p1.setText("Shane is working with a spreadsheet on his computer. The spreadsheet will " +
    				"calculate the cost of the wood trim around rectangular windows based on the dimensions " +
    				"of the window and the price of the wood. The following entries have been made.");
    		qImage.setImageResource(R.drawable.mathmulti_three);
    		quest_p2.setText("Shane wants to enter a formula in the last column so that the spreadsheet " +
    				"will calculate the final cost of the job. Which of the following should he enter?");
    		a1.setText("A7 x B7 x C7");
    		a2.setText("(2 x A7 + 2 x B7) x C7");
    		a3.setText("A7 x B7 + C7");
    		a4.setText("(A7 + B7) x C7");
    		a5.setText("A7 x B7 x C7");
    		correctAnswer="(2 x A7 + 2 x B7) x C7";
    		b1.setPressed(false);
    		b2.setPressed(false);
    		b3.setPressed(false);
    		b4.setPressed(false);
    		b5.setPressed(false);
    		qNum++;
            chkAnswer.setText("Choose an Answer");
            selectedAnswer = "";
    		break;
    	case 4:
    		additional_info.setText("");
    		additional_info.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    
                }
            });
    		quest_p1.setText("Last month, the balance in Tisha's checkbook was $1219.17. Since " +
    				"then she has deposited her latest paycheck of $2425.66 and written checks for " +
    				"$850.00 (rent), $235.89 (car payment), and $418.37 (credit card payment).");
    		qImage.setImageResource(R.drawable.trans);
    		qImage.setVisibility(8);
    		quest_p2.setText("");
    		a1.setText("$ 921.40");
    		a2.setText("$2140.57");
    		a3.setText("$3215.27");
    		a4.setText("$3929.92");
    		a5.setText("$5149.09");
    		correctAnswer = "$2140.57";
    		b1.setPressed(false);
    		b2.setPressed(false);
    		b3.setPressed(false);
    		b4.setPressed(false);
    		b5.setPressed(false);
    		qNum++;
            chkAnswer.setText("Choose an Answer");
            selectedAnswer = "";
    		break;
    	case 5:
    		additional_info.setText("");
    		additional_info.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    
                }
            });
    		quest_p1.setText("");
    		qImage.setImageResource(R.drawable.cpgrid_1);
    		quest_p2.setText("What is the location of point on the above coordinate plane grid?");
    		a1.setText("(-4, 3)");
    		a2.setText("(3, 4)");
    		a3.setText("(-3, 4)");
    		a4.setText("(4, -3)");
    		a5.setText("(3, -4)");
    		correctAnswer = "(3, -4)";
    		b1.setPressed(false);
    		b2.setPressed(false);
    		b3.setPressed(false);
    		b4.setPressed(false);
    		b5.setPressed(false);
    		qNum++;
            chkAnswer.setText("Choose an Answer");
            selectedAnswer = "";
    		break;
    	case 6:
    		additional_info.setText("");
    		additional_info.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    
                }
            });
    		quest_p1.setText("");
    		qImage.setImageResource(R.drawable.cpgrid_1);
    		quest_p2.setText("The coordinates above are in which quadrant of the graph?");
    		a1.setText("First");
    		a2.setText("Second");    		
    		a3.setText("Third");
    		a4.setText("Fourth");
    		a5.setText("");
    		a5.setVisibility(8);
    		correctAnswer = "Fourth";
    		b1.setPressed(false);
    		b2.setPressed(false);
    		b3.setPressed(false);
    		b4.setPressed(false);
    		b5.setPressed(false);
    		b5.setVisibility(8);
    		qNum++;
            chkAnswer.setText("Choose an Answer");
            selectedAnswer = "";
    		break;
    	default:
    		showDialog(DIALOG_ID_COMPLETED_QUESTIONS);
    	}
    }
}