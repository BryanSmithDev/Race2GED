package edu.shenandoah.ged;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
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

public class SocialStudiesMultiActivity extends Activity {
	
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
	int numberCorrect=0;
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
        			
        			mp = MediaPlayer.create(SocialStudiesMultiActivity.this, R.raw.noanswer);
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
        			
        			mp = MediaPlayer.create(SocialStudiesMultiActivity.this, R.raw.correct);
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
	        			
	        			mp = MediaPlayer.create(SocialStudiesMultiActivity.this, R.raw.incorrect);
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
        				
        				mp = MediaPlayer.create(SocialStudiesMultiActivity.this, R.raw.incorrect);
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
    			} catch(Exception e){}
    			
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
    		SocialStudiesMultiActivity.this.finish();
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
   			if (attempts <= 0) { numberCorrect+=1;}
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
   					SocialStudiesMultiActivity.this.finish();
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
   			
   			((CustomDialog) dialog).setMessage(String.format(getResources().getString(R.string.max_attempts_message,correctAnswer)));		
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
    		additional_info.setText(R.string.socinfo1_label);
    		additional_info.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                	openAdditionalInfo(1, v);
                }
            });    		
    		additional_info.setVisibility(0);
    		quest_p1.setText("The government of a country may restrict the number of immigrants allowed to enter that country.");
    		qImage.setImageResource(R.drawable.trans);
    		qImage.setVisibility(8);
    		quest_p2.setText("These restrictions on immigration are most likely based on what belief?");
    		a1.setText("An economy can support unlimited numbers of people.");
    		a2.setText("The “push” factors justify most immigration.");
    		a3.setText("Immigrants enrich the culture of a country.");
    		a4.setText("A country has a limited number of jobs and services.");
    		a5.setText("A government should not interfere with the migration of people.");
    		correctAnswer = "A country has a limited number of jobs and services.";
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
    		additional_info.setText(R.string.socinfo1_label);
    		additional_info.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                	openAdditionalInfo(1, v);
                }
            });
    		additional_info.setVisibility(0);
    		quest_p1.setText("Based on the information, which is an opinion rather than a fact about immigrants to North America?");
    		qImage.setImageResource(R.drawable.trans);
    		qImage.setVisibility(8);
    		quest_p2.setText("Immigrants");
    		a1.setText("traveled long distances to find a better life");
    		a2.setText("migrated to find employment");
    		a3.setText("learned to live in a foreign culture");
    		a4.setText("escaped from political persecution");
    		a5.setText("found a better life");
    		correctAnswer = "found a better life";
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
    		quest_p1.setText("What is the main idea represented by the artist of this cartoon?");
    		qImage.setImageResource(R.drawable.political_cartoon);
    		quest_p2.setText("");
    		a1.setText("Congress cannot pass legislation on health issues.");
    		a2.setText("Hatred exists between Congress and senior citizens.");
    		a3.setText("Senior citizens are a powerful interest group.");
    		a4.setText("Older people are healthier and stronger than the generation before them.");
    		a5.setText("Senior citizens favor Congress over the president.");
    		correctAnswer = "Senior citizens are a powerful interest group.";
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
    		quest_p1.setText("Which political and economic assumption does the cartoon suggest is held by U.S. senior citizens?");
    		qImage.setImageResource(R.drawable.political_cartoon);
    		quest_p2.setText("");
    		a1.setText("Seniors are entitled to certain social insurance programs.");
    		a2.setText("Seniors do not contribute to the economic productivity of the nation.");
    		a3.setText("Entitlements should be cut within the federal budget.");
    		a4.setText("Seniors should lobby at the state level of government, not the federal level.");
    		a5.setText("Social insurance programs can only be paid for by higher taxes.");
    		correctAnswer = "Seniors are entitled to certain social insurance programs.";
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
    		quest_p1.setText("Some people think that stores that sell fewer brands of computers provide faster service than stores " +
    				"that sell a wider variety of brands.");
    		qImage.setImageResource(R.drawable.computer_problem);
    		quest_p2.setText("What information from the chart supports this idea?");
    		correctAnswer = "Technodazzle – 100 percent service promptness";
    		a1.setText("AAA Business – 96 percent service promptness");
    		a2.setText("Operating System – 69 percent service promptness");
    		a3.setText("Programs ‘N Stuff – 79 percent service promptness");
    		a4.setText(correctAnswer);
    		a5.setText("Compu-Wonder – 89 percent service promptness");
    		
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
    		quest_p1.setText("\"We hold these Truths to be self-evident, that all Men are created equal, that they are endowed by " +
    				"their Creator with certain unalienable Rights, that among these rights are Life, Liberty, and the pursuit of Happiness.\"");
    		qImage.setImageResource(R.drawable.trans);
    		qImage.setVisibility(8);
    		quest_p2.setText("Which of the following political actions violated the principle of “unalienable Rights” of liberty " +
    				"that evolved from the above excerpt of the U.S. Declaration of Independence?");
    		a1.setText("In 1857, a U.S. Supreme Court ruling promoted the expansion of slavery in U.S. territories.");
    		a2.setText("In 1870, the Fifteenth Amendment to the Constitution outlawed the practice of denying the right to vote because " +
    				"of race, color, or previous condition of servitude.");
    		a3.setText("In 1920, the Nineteenth Amendment to the Constitution granted women the right to vote nationwide.");
    		a4.setText("In 1964, the Civil Rights Act outlawed racial discrimination in employment and public accommodations.");
    		a5.setText("In 1971, the Twenty-sixth Amendment to the Constitution extended the right to vote to 18-year-old citizens.");
    		correctAnswer = "In 1857, a U.S. Supreme Court ruling promoted the expansion of slavery in U.S. territories.";
    		b1.setPressed(false);
    		b2.setPressed(false);
    		b3.setPressed(false);
    		b4.setPressed(false);
    		b5.setPressed(false);
    		qNum++;
            chkAnswer.setText("Choose an Answer");
            selectedAnswer = "";
    		break;
    	default:
    		showDialog(DIALOG_ID_COMPLETED_QUESTIONS);
    	}
    }
    
    private void openAdditionalInfo(int id, View v)
    {
    	switch (id)
    	{
    	case 1:
    		Intent nIntent = new Intent(v.getContext(), SocInfo1Activity.class);
			startActivityForResult(nIntent, 0);
			break;
    	}
    }
}