package edu.mecc.race2ged;

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
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

public class ReadingMultiActivity extends Activity {

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

    public int getRemaining() {
        return (maxAttempts - attempts);
    }

    /**
     * Called when the activity is first created.
     */
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

        chkAnswer.setOnClickListener(new OnClickListener() {
            int id = 0;

            public void onClick(View view) {
                try {
                    if (selectedAnswer == "") {
                        id = DIALOG_ID_NO_SELECTION;

                        mp = MediaPlayer.create(ReadingMultiActivity.this, R.raw.noanswer);
                        mp.start();
                        mp.setOnCompletionListener(new OnCompletionListener() {
                            public void onCompletion(MediaPlayer mp) {
                                mp.release();
                            }
                        });
                    } else if (selectedAnswer == correctAnswer) {
                        id = DIALOG_ID_CORRECT_SELECTION;

                        mp = MediaPlayer.create(ReadingMultiActivity.this, R.raw.correct);
                        mp.start();
                        mp.setOnCompletionListener(new OnCompletionListener() {
                            public void onCompletion(MediaPlayer mp) {
                                mp.release();
                            }
                        });
                    } else if (selectedAnswer != correctAnswer) {
                        if (attempts < maxAttempts) {
                            id = DIALOG_ID_WRONG_SELECTION;

                            mp = MediaPlayer.create(ReadingMultiActivity.this, R.raw.incorrect);
                            mp.start();
                            mp.setOnCompletionListener(new OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();
                                }
                            });
                        } else {
                            id = DIALOG_ID_MAX_ATTEMPTS;

                            mp = MediaPlayer.create(ReadingMultiActivity.this, R.raw.incorrect);
                            mp.start();
                            mp.setOnCompletionListener(new OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();
                                }
                            });
                        }
                    } else
                        id = DIALOG_ID_ERROR_DURING_SELECTION;

                    showDialog(id);
                } catch (Exception e) {
                }
            }
        });

        setQuestionInfo(qNum);
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
                ReadingMultiActivity.this.finish();
        }

        return true;
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        super.onCreateDialog(id);
        Dialog dialog = null;


        switch (id) {
            case DIALOG_ID_NO_SELECTION:
                final CustomDialog noSelection = new CustomDialog(this);
                noSelection.setTitle(R.string.no_selection_title);
                noSelection.setMessage(R.string.no_selection_message);
                noSelection.setIcon(R.drawable.ex_icon);
                noSelection.setNoButtonVisibility(false);
                noSelection.setCloseButtonVisibility(false);
                noSelection.setYesButtonAction(R.string.no_selection_yes_button, new View.OnClickListener() {
                    public void onClick(View v) {
                        selectedAnswer = "";
                        chkAnswer.setText(R.string.choose_answer);
                        noSelection.cancel();
                    }
                });
                dialog = noSelection;

                break;
            case DIALOG_ID_CORRECT_SELECTION:
                final CustomDialog correctSelection = new CustomDialog(this);
                correctSelection.setTitle(R.string.correct_selection_title);
                correctSelection.setMessage(R.string.correct_selection_message);
                correctSelection.setIcon(R.drawable.correct_icon);
                correctSelection.setNoButtonVisibility(false);
                correctSelection.setCloseButtonVisibility(false);
                correctSelection.setYesButtonAction(R.string.correct_selection_yes_button, new View.OnClickListener() {
                    public void onClick(View v) {
                        setQuestionInfo(qNum);
                        scrollView.fullScroll(0);
                        correctSelection.cancel();
                    }
                });
                dialog = correctSelection;
                break;
            case DIALOG_ID_WRONG_SELECTION:
                final CustomDialog wrongSelection = new CustomDialog(this);
                wrongSelection.setTitle(R.string.wrong_selection_title);
                wrongSelection.setMessage(getResources().getQuantityString(R.plurals.wrong_selection_message, getRemaining()));
                wrongSelection.setIcon(R.drawable.wrong_icon);
                wrongSelection.setNoButtonVisibility(false);
                wrongSelection.setCloseButtonVisibility(false);
                wrongSelection.setYesButtonAction(R.string.wrong_selection_yes_button, new View.OnClickListener() {
                    public void onClick(View v) {
                        selectedAnswer = "";
                        chkAnswer.setText(R.string.choose_answer);
                        attempts++;
                        wrongSelection.cancel();
                    }
                });
                dialog = wrongSelection;
                break;
            case DIALOG_ID_MAX_ATTEMPTS:
                final CustomDialog maxAttempts = new CustomDialog(this);
                maxAttempts.setTitle(R.string.max_attempts_title);
                maxAttempts.setMessage(String.format(getResources().getString(R.string.max_attempts_message, correctAnswer)));
                maxAttempts.setIcon(R.drawable.wrong_icon);
                maxAttempts.setNoButtonVisibility(false);
                maxAttempts.setCloseButtonVisibility(false);
                maxAttempts.setYesButtonAction(R.string.max_attempts_yes_button, new View.OnClickListener() {
                    public void onClick(View v) {
                        setQuestionInfo(qNum);
                        scrollView.fullScroll(0);
                        maxAttempts.cancel();
                    }
                });
                dialog = maxAttempts;
                break;
            case DIALOG_ID_ERROR_DURING_SELECTION:
                final CustomDialog selectionError = new CustomDialog(this);
                selectionError.setTitle(R.string.selection_error_title);
                selectionError.setMessage(R.string.selection_error_message);
                selectionError.setIcon(R.drawable.ex_icon);
                selectionError.setNoButtonVisibility(false);
                selectionError.setCloseButtonVisibility(false);
                selectionError.setYesButtonAction(R.string.selection_error_yes_button, new View.OnClickListener() {
                    public void onClick(View v) {
                        selectedAnswer = "";
                        chkAnswer.setText(R.string.choose_answer);
                        setQuestionInfo(qNum - 1);
                        scrollView.fullScroll(0);
                        selectionError.cancel();
                    }
                });
                dialog = selectionError;
                break;
            case DIALOG_ID_COMPLETED_QUESTIONS:
                final CustomDialog completedQuestions = new CustomDialog(this);
                completedQuestions.setTitle(R.string.completed_questions_title);
                completedQuestions.setMessage(R.string.completed_questions_message);
                completedQuestions.setIcon(R.drawable.correct_icon);
                completedQuestions.setCloseButtonVisibility(false);
                completedQuestions.setYesButtonAction(R.string.completed_questions_yes_button, new View.OnClickListener() {
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
                        ReadingMultiActivity.this.finish();
                        completedQuestions.cancel();
                    }
                });
                dialog = completedQuestions;
                break;
            default:
                break;
        }

        return dialog;
    }

    @Override
    protected void onPrepareDialog(final int id, final Dialog dialog) {
        switch (id) {
            case DIALOG_ID_WRONG_SELECTION:
                ((CustomDialog) dialog).setMessage(getResources().getQuantityString(R.plurals.wrong_selection_message, getRemaining()));
                break;
            case DIALOG_ID_MAX_ATTEMPTS:
                ((CustomDialog) dialog).setMessage(String.format(getResources().getString(R.string.max_attempts_message, correctAnswer)));
                break;
        }
    }

    private void setQuestionInfo(int questionNum) {
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

        switch (questionNum) {
            case 1:
                additional_info.setText(R.string.readinginfo1_label);
                additional_info.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        openAdditionalInfo(1, v);
                    }
                });
                additional_info.setVisibility(0);
                quest_p1.setText("Based on the information in this excerpt, what would be the company's policy " +
                        "about accepting gifts or entertainment from people representing your competitors?");
                qImage.setImageResource(R.drawable.trans);
                qImage.setVisibility(8);
                quest_p2.setText("Accepting gifts or entertainment from people representing competitors would be considered");
                a1.setText("dangerous");
                a2.setText("practical");
                a3.setText("reasonable");
                a4.setText("unacceptable");
                a5.setText("good business");
                correctAnswer = "unacceptable";
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
                additional_info.setText(R.string.readinginfo1_label);
                additional_info.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        openAdditionalInfo(1, v);
                    }
                });
                additional_info.setVisibility(0);
                quest_p1.setText("Based on the information in this excerpt, when is it permissible to give money that might " +
                        "be considered a bribe or a tip to a government official in order to facilitate business?");
                qImage.setImageResource(R.drawable.trans);
                qImage.setVisibility(8);
                quest_p2.setText("");
                a1.setText("It is never permissible to give a gift or other payment to influence government or business decisions.");
                a2.setText("It may be necessary in certain foreign countries where it is expected.");
                a3.setText("Payments to government officials can be made at any time with prior approval of the division president.");
                a4.setText("Modest gratuities can be offered at any time.");
                a5.setText("All payments or gratuities must be worth their fair trade value.");
                correctAnswer = "It is never permissible to give a gift or other payment to influence government or business decisions.";
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
                additional_info.setText(R.string.readinginfo1_label);
                additional_info.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        openAdditionalInfo(1, v);
                    }
                });
                additional_info.setVisibility(0);
                quest_p1.setText("What is the meaning of the phrase \"or even creates the appearance of\" (lines 14-15)?");
                qImage.setImageResource(R.drawable.trans);
                qImage.setVisibility(8);
                quest_p2.setText("");
                a1.setText("an actual conflict of interest");
                a2.setText("a conflict between the company's interests and your personal or immediate family's interest");
                a3.setText("a situation that is not truly a conflict of interest but may appear to be");
                a4.setText("the company's high standards for employee personal appearance and behavior");
                a5.setText("a situation that is within your personal affairs");
                correctAnswer = "a situation that is not truly a conflict of interest but may appear to be";
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
                additional_info.setText(R.string.readinginfo1_label);
                additional_info.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        openAdditionalInfo(1, v);
                    }
                });
                additional_info.setVisibility(0);
                quest_p1.setText("What is an example of a conflict of interest?");
                qImage.setImageResource(R.drawable.trans);
                qImage.setVisibility(8);
                quest_p2.setText("");
                a1.setText("Your cousin works for the same company.");
                a2.setText("You have given gratuities to foreign government officials.");
                a3.setText("You often meet with the employees' union representative.");
                a4.setText("You have cheated on your income tax.");
                a5.setText("Your spouse owns stock in one of the company's competitors.");
                correctAnswer = "Your spouse owns stock in one of the company's competitors.";
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
                additional_info.setText(R.string.readinginfo1_label);
                additional_info.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        openAdditionalInfo(1, v);
                    }
                });
                additional_info.setVisibility(0);
                quest_p1.setText("While this excerpt is discussing a very serious topic, it tries to maintain a feeling of sincere interest in the employee.");
                qImage.setImageResource(R.drawable.trans);
                qImage.setVisibility(8);
                quest_p2.setText("Which of the statements below most clearly reflects that feeling?");
                a1.setText("\"No payment or gift of money, goods, or services should be given or received to influence government or business decisions\" (lines6-7).");
                a2.setText("\"...records for all accounts will be strictly monitored...\" (lines7-8).");
                a3.setText("\"Don't use your position with this company to obtain a personal benefit of any kind\" (line 19).");
                a4.setText("\"This company respects your privacy, as well as your right to conduct your personal affairs without interference\" (lines 25-26).");
                a5.setText("\"Your cooperation... is a condition of your employment\" (lines 32-33).");
                correctAnswer = "\"This company respects your privacy, as well as your right to conduct your personal affairs without interference\" (lines 25-26).";
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
                additional_info.setText(R.string.readinginfo1_label);
                additional_info.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        openAdditionalInfo(1, v);
                    }
                });
                additional_info.setVisibility(0);
                quest_p1.setText("How is this excerpt organized?");
                qImage.setImageResource(R.drawable.trans);
                qImage.setVisibility(8);
                quest_p2.setText("");
                a1.setText("statements of fact supported by personal stories");
                a2.setText("specific topics followed by explanations");
                a3.setText("rules for behavior supported by warnings of consequences");
                a4.setText("general policy statement without specific performance standards");
                a5.setText("debate of public versus personal philosophies");
                correctAnswer = "specific topics followed by explanations";
                b1.setPressed(false);
                b2.setPressed(false);
                b3.setPressed(false);
                b4.setPressed(false);
                b5.setPressed(false);
                qNum++;
                chkAnswer.setText("Choose an Answer");
                selectedAnswer = "";
                break;
            case 7:
                additional_info.setText(R.string.readinginfo1_label);
                additional_info.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        openAdditionalInfo(1, v);
                    }
                });
                additional_info.setVisibility(0);
                quest_p1.setText("Earlier in this document, the company describes how employees of this company are to " +
                        "behave toward each other. It includes a statement about complete and enforced intolerance for " +
                        "discrimination in any form.");
                qImage.setImageResource(R.drawable.trans);
                qImage.setVisibility(8);
                quest_p2.setText("Based on this information and the information in this excerpt, why might a potential employee " +
                        "want to read all this company's rules and regulations for employee behavior carefully?");
                a1.setText("One mistake with this company could cost an employee his or her job.");
                a2.setText("Competitors have several options for securing their rights.");
                a3.setText("Employees and their managers must interact carefully.");
                a4.setText("Unfair labor practices will not be tolerated.");
                a5.setText("This company has high expectations for employee behavior.");
                correctAnswer = "This company has high expectations for employee behavior.";
                b1.setPressed(false);
                b2.setPressed(false);
                b3.setPressed(false);
                b4.setPressed(false);
                b5.setPressed(false);
                qNum++;
                chkAnswer.setText("Choose an Answer");
                selectedAnswer = "";
                break;
            case 8:
                additional_info.setText(R.string.readinginfo1_label);
                additional_info.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        openAdditionalInfo(1, v);
                    }
                });
                additional_info.setVisibility(0);
                quest_p1.setText("If you were running this company and you wanted to hire someone to enforce these rules, " +
                        "what primary characteristic would you want in this potential employee?");
                qImage.setImageResource(R.drawable.trans);
                qImage.setVisibility(8);
                quest_p2.setText("");
                a1.setText("knowledge about the company's product");
                a2.setText("honesty and integrity");
                a3.setText("communication skills");
                a4.setText("human relations skills");
                a5.setText("familiarity with your employee");
                correctAnswer = "honesty and integrity";
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

    private void openAdditionalInfo(int id, View v) {
        switch (id) {
            case 1:
                Intent nIntent = new Intent(v.getContext(), ReadingInfo1Activity.class);
                startActivityForResult(nIntent, 0);
                break;
        }
    }
}