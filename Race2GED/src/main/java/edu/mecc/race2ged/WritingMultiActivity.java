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

public class WritingMultiActivity extends Activity {

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

                        mp = MediaPlayer.create(WritingMultiActivity.this, R.raw.noanswer);
                        mp.start();
                        mp.setOnCompletionListener(new OnCompletionListener() {
                            public void onCompletion(MediaPlayer mp) {
                                mp.release();
                            }
                        });
                    } else if (selectedAnswer == correctAnswer) {
                        id = DIALOG_ID_CORRECT_SELECTION;

                        mp = MediaPlayer.create(WritingMultiActivity.this, R.raw.correct);
                        mp.start();
                        mp.setOnCompletionListener(new OnCompletionListener() {
                            public void onCompletion(MediaPlayer mp) {
                                mp.release();
                            }
                        });
                    } else if (selectedAnswer != correctAnswer) {
                        if (attempts < maxAttempts) {
                            id = DIALOG_ID_WRONG_SELECTION;

                            mp = MediaPlayer.create(WritingMultiActivity.this, R.raw.incorrect);
                            mp.start();
                            mp.setOnCompletionListener(new OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();
                                }
                            });
                        } else {
                            id = DIALOG_ID_MAX_ATTEMPTS;

                            mp = MediaPlayer.create(WritingMultiActivity.this, R.raw.incorrect);
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
                WritingMultiActivity.this.finish();
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
                        WritingMultiActivity.this.finish();
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
                additional_info.setText(R.string.loa_label);
                additional_info.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        openAdditionalInfo(1, v);
                    }
                });
                additional_info.setVisibility(0);
                quest_p1.setText("Sentence 2: My work experience and education combined with your need for an experienced " +
                        "landscape supervisor have resulted in a relationship that would profit both parties.");
                qImage.setImageResource(R.drawable.trans);
                qImage.setVisibility(8);
                quest_p2.setText("Which correction should be made to sentence 2?");
                a1.setText("insert a comma after education");
                a2.setText("change combined to combine");
                a3.setText("change have resulted to would result");
                a4.setText("replace profit with prophet");
                a5.setText("replace parties with party�s");
                correctAnswer = "change have resulted to would result";
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
                additional_info.setText(R.string.loa_label);
                additional_info.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        openAdditionalInfo(1, v);
                    }
                });
                additional_info.setVisibility(0);
                quest_p1.setText("Sentences 3 and 4: In May, I graduated from Prince William Community College. Graduating with " +
                        "an associate of arts degree in horticulture.");
                qImage.setImageResource(R.drawable.trans);
                qImage.setVisibility(8);
                quest_p2.setText("Which is the best way to write the underlined portion of these sentences? If the original is the " +
                        "best way, choose option (1).");
                a1.setText("College. Graduating with");
                a2.setText("College, I graduated with");
                a3.setText("College. A graduation with");
                a4.setText("College. Having graduated with");
                a5.setText("College with");
                correctAnswer = "College with";
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
                additional_info.setText(R.string.loa_label);
                additional_info.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        openAdditionalInfo(1, v);
                    }
                });
                additional_info.setVisibility(0);
                quest_p1.setText("Sentence 10: I will be, as a landscape supervisor at your firm, able to put to use the skills and " +
                        "knowledge that I have obtained from my professional career and education. ");
                qImage.setImageResource(R.drawable.trans);
                qImage.setVisibility(8);
                quest_p2.setText("If you rewrote sentence 10 with" +
                        "\n" +
                        "As a landscape supervisor at your firm," +
                        "\n" +
                        "the next words should be");
                a1.setText("and able I will be");
                a2.setText("I will be able");
                a3.setText("putting and using with ability ");
                a4.setText("obtaining my professional career and education");
                a5.setText("able to put to use I will be");
                correctAnswer = "I will be able";
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
                additional_info.setText(R.string.loa_label);
                additional_info.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        openAdditionalInfo(1, v);
                    }
                });
                additional_info.setVisibility(0);
                quest_p1.setText("Which sentence below would be most effective at the beginning of paragraph B?");
                qImage.setImageResource(R.drawable.trans);
                qImage.setVisibility(8);
                quest_p2.setText("");
                a1.setText("There are many companies in this community, and Capital City Gardening Services is one of them.");
                a2.setText("A company such as yours is known for a lot of things, especially the beautiful fountain, great " +
                        "billboard, and large parking area.");
                a3.setText("Like carpet-cleaning services, gardening services range in cost.");
                a4.setText("A company is only as good as its reputation.");
                a5.setText("Gosh, I don�t know where to begin when saying good things about your company.");
                correctAnswer = "A company is only as good as its reputation.";
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
                additional_info.setText(R.string.loa_label);
                additional_info.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        openAdditionalInfo(1, v);
                    }
                });
                additional_info.setVisibility(0);
                quest_p1.setText("Sentence 11: I have included a copy of my resume, which details my principal " +
                        "interests education, and past work experience.");
                qImage.setImageResource(R.drawable.trans);
                qImage.setVisibility(8);
                quest_p2.setText("Which correction should be made to sentence 11?");
                a1.setText("remove the comma after resume");
                a2.setText("replace principal with principle ");
                a3.setText("insert a comma after interests");
                a4.setText("replace past with passed");
                a5.setText("no correction  is necessary");
                correctAnswer = "insert a comma after interests";
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
                additional_info.setText(R.string.loa_label);
                additional_info.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        openAdditionalInfo(1, v);
                    }
                });
                additional_info.setVisibility(0);
                quest_p1.setText("Which revision would improve the effectiveness of this letter?");
                qImage.setImageResource(R.drawable.trans);
                qImage.setVisibility(8);
                quest_p2.setText("Begin a new paragraph with");
                a1.setText("sentence 3");
                a2.setText("sentence 5");
                a3.setText("sentence 7");
                a4.setText("sentence 9");
                a5.setText("sentence 12");
                correctAnswer = "sentence 3";
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
                Intent nIntent = new Intent(v.getContext(), LoAActivity.class);
                startActivityForResult(nIntent, 0);
                break;
        }
    }
}