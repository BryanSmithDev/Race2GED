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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

public class MathAltActivity extends Activity {

    private static final int DIALOG_ID_NO_SELECTION = 1;
    private static final int DIALOG_ID_CORRECT_SELECTION = 2;
    private static final int DIALOG_ID_WRONG_SELECTION = 3;
    private static final int DIALOG_ID_MAX_ATTEMPTS = 4;
    private static final int DIALOG_ID_ERROR_DURING_SELECTION = 5;
    private static final int DIALOG_ID_COMPLETED_QUESTIONS = 6;

    TextView quest_p1, quest_p2;
    ImageView qImage;
    EditText[] ans;
    String[] btn_values, correctAnswers;
    Button[] c1, c2, c3, c4, c5;
    Button reset, chkAnswer;
    String selectedAnswer;
    int qNum, numAnswers, attempts, maxAttempts;
    MediaPlayer mp;
    ScrollView scrollView;

    public int getRemaining() {
        return (maxAttempts - attempts);
    }

    public String showAnswers() {
        String results = "";

        for (int i = 0; i < correctAnswers.length; i++) {
            if (correctAnswers[i] != "NoAnswer")
                results += correctAnswers[i] + "\n";
        }

        return (results);
    }

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.mathalt_layout);
        scrollView = (ScrollView) findViewById(R.id.scrollView1);
        quest_p1 = (TextView) findViewById(R.id.mathalt_qp1);
        quest_p1.setText("");

        quest_p2 = (TextView) findViewById(R.id.mathalt_qp2);
        quest_p2.setText("");

        qImage = (ImageView) findViewById(R.id.mathalt_qimage);
        qImage.setVisibility(8);

        selectedAnswer = "";

        correctAnswers = new String[5];
        for (int i = 0; i < correctAnswers.length; i++)
            correctAnswers[i] = "NoAnswer";

        numAnswers = 0;
        qNum = 1;
        attempts = 0;
        maxAttempts = 0;

        reset = (Button) findViewById(R.id.mathalt_reset);
        reset.setText("Reset");
        reset.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {

                for (int i = 0; i < ans.length; i++)
                    ans[i].setText(" ");

                for (int i = 0; i < c1.length; i++) {
                    c1[i].setPressed(false);
                    c5[i].setPressed(false);
                }

                for (int i = 0; i < c2.length; i++) {
                    c2[i].setPressed(false);
                    c3[i].setPressed(false);
                    c4[i].setPressed(false);
                }

                chkAnswer.setText("Enter Your Answer");

            }
        });

        ans = new EditText[5];

        for (int i = 0; i < ans.length; i++) {
            String eID = "box_c" + (i + 1);
            int rID = getResources().getIdentifier(eID, "id", getPackageName());
            ans[i] = ((EditText) findViewById(rID));
            ans[i].setText(" ");
        }

        btn_values = new String[]{"/", ".", "0", "1", "2", "3", "4", "5",
                "6", "7", "8", "9"};

        c1 = new Button[11];

        for (int i = 0; i < c1.length; i++) {
            String bID = "c1_b" + (i + 1);
            int rID = getResources().getIdentifier(bID, "id", getPackageName());
            c1[i] = ((Button) findViewById(rID));
            c1[i].setPressed(false);

            c1[i].setOnTouchListener(new OnTouchListener() {

                public boolean onTouch(View v, MotionEvent event) {

                    for (int j = 0; j < c1.length; j++) {
                        if (c1[j].getId() == v.getId()) {
                            c1[j].setPressed(true);
                            ans[0].setText(btn_values[j + 1]);
                            chkAnswer.setText("Check Answer");
                        } else
                            c1[j].setPressed(false);
                    }

                    return true;
                }
            });
        }

        c2 = new Button[12];

        for (int i = 0; i < c2.length; i++) {
            String bID = "c2_b" + i;
            int rID = getResources().getIdentifier(bID, "id", getPackageName());
            c2[i] = ((Button) findViewById(rID));
            c2[i].setPressed(false);

            c2[i].setOnTouchListener(new OnTouchListener() {

                public boolean onTouch(View v, MotionEvent event) {

                    for (int j = 0; j < c2.length; j++) {
                        if (c2[j].getId() == v.getId()) {
                            c2[j].setPressed(true);
                            ans[1].setText(btn_values[j]);
                            chkAnswer.setText("Check Answer");
                        } else
                            c2[j].setPressed(false);
                    }

                    return true;
                }
            });
        }

        c3 = new Button[12];

        for (int i = 0; i < c3.length; i++) {
            String bID = "c3_b" + i;
            int rID = getResources().getIdentifier(bID, "id", getPackageName());
            c3[i] = ((Button) findViewById(rID));
            c3[i].setPressed(false);

            c3[i].setOnTouchListener(new OnTouchListener() {

                public boolean onTouch(View v, MotionEvent event) {

                    for (int j = 0; j < c3.length; j++) {
                        if (c3[j].getId() == v.getId()) {
                            c3[j].setPressed(true);
                            ans[2].setText(btn_values[j]);
                            chkAnswer.setText("Check Answer");
                        } else
                            c3[j].setPressed(false);
                    }

                    return true;
                }
            });
        }

        c4 = new Button[12];

        for (int i = 0; i < c4.length; i++) {
            String bID = "c4_b" + i;
            int rID = getResources().getIdentifier(bID, "id", getPackageName());
            c4[i] = ((Button) findViewById(rID));
            c4[i].setPressed(false);

            c4[i].setOnTouchListener(new OnTouchListener() {

                public boolean onTouch(View v, MotionEvent event) {

                    for (int j = 0; j < c4.length; j++) {
                        if (c4[j].getId() == v.getId()) {
                            c4[j].setPressed(true);
                            ans[3].setText(btn_values[j]);
                            chkAnswer.setText("Check Answer");
                        } else
                            c4[j].setPressed(false);
                    }

                    return true;
                }
            });
        }

        c5 = new Button[11];

        for (int i = 0; i < c5.length; i++) {
            String bID = "c5_b" + (i + 1);
            int rID = getResources().getIdentifier(bID, "id", getPackageName());
            c5[i] = ((Button) findViewById(rID));
            c5[i].setPressed(false);

            c5[i].setOnTouchListener(new OnTouchListener() {

                public boolean onTouch(View v, MotionEvent event) {

                    for (int j = 0; j < c5.length; j++) {
                        if (c5[j].getId() == v.getId()) {
                            c5[j].setPressed(true);
                            ans[4].setText(btn_values[j + 1]);
                            chkAnswer.setText("Check Answer");
                        } else
                            c5[j].setPressed(false);
                    }

                    return true;
                }
            });
        }

        chkAnswer = (Button) findViewById(R.id.mathalt_chkanswer);
        chkAnswer.setText("Enter Your Answer");

        chkAnswer.setOnClickListener(new OnClickListener() {


            public void onClick(View view) {
                try {
                    int id = 0;
                    boolean correct = false;
                    selectedAnswer = "";

                    for (int i = 0; i < ans.length; i++)
                        selectedAnswer += ans[i].getText().toString();

                    selectedAnswer = selectedAnswer.trim();

                    if (chkAnswer.getText() == "Enter Your Answer") {
                        id = DIALOG_ID_NO_SELECTION;

                        mp = MediaPlayer.create(MathAltActivity.this,
                                R.raw.noanswer);
                        mp.start();
                        mp.setOnCompletionListener(new OnCompletionListener() {
                            public void onCompletion(MediaPlayer mp) {
                                mp.release();
                            }
                        });
                    } else {
                        for (int i = 0; i < numAnswers; i++) {
                            if (selectedAnswer.equals(correctAnswers[i])) {
                                id = DIALOG_ID_CORRECT_SELECTION;

                                correct = true;
                            }
                        }

                        if (correct) {
                            mp = MediaPlayer.create(MathAltActivity.this, R.raw.correct);
                            mp.start();
                            mp.setOnCompletionListener(new OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();
                                }
                            });
                        } else {
                            if (attempts < maxAttempts) {
                                id = DIALOG_ID_WRONG_SELECTION;

                                mp = MediaPlayer.create(MathAltActivity.this,
                                        R.raw.incorrect);
                                mp.start();
                                mp.setOnCompletionListener(new OnCompletionListener() {
                                    public void onCompletion(MediaPlayer mp) {
                                        mp.release();
                                    }
                                });
                            } else {
                                id = DIALOG_ID_MAX_ATTEMPTS;

                                mp = MediaPlayer.create(MathAltActivity.this,
                                        R.raw.incorrect);
                                mp.start();
                                mp.setOnCompletionListener(new OnCompletionListener() {
                                    public void onCompletion(MediaPlayer mp) {
                                        mp.release();
                                    }
                                });
                            }
                        }
                    }

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
                MathAltActivity.this.finish();
        }

        return true;
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // super.onCreateDialog(id);
        Dialog dialog = null;

        switch (id) {
            case DIALOG_ID_NO_SELECTION:
                final CustomDialog noSelection = new CustomDialog(this);
                noSelection.setTitle(R.string.no_selection_title);
                noSelection.setMessage(R.string.no_selection_message);
                noSelection.setIcon(R.drawable.ex_icon);
                noSelection.setNoButtonVisibility(false);
                noSelection.setCloseButtonVisibility(false);
                noSelection.setYesButtonAction(R.string.no_selection_yes_button,
                        new View.OnClickListener() {

                            public void onClick(View v) {
                                selectedAnswer = "";
                                for (int i = 0; i < ans.length; i++)
                                    ans[i].setText("");

                                for (int i = 0; i < c1.length; i++) {
                                    c1[i].setPressed(false);
                                    c5[i].setPressed(false);
                                }

                                for (int i = 0; i < c2.length; i++) {
                                    c2[i].setPressed(false);
                                    c3[i].setPressed(false);
                                    c4[i].setPressed(false);
                                }
                                chkAnswer.setText("Enter Your Answer");
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
                correctSelection.setYesButtonAction(
                        R.string.correct_selection_yes_button,
                        new View.OnClickListener() {
                            public void onClick(View v) {
                                for (int i = 0; i < correctAnswers.length; i++)
                                    correctAnswers[i] = "NoAnswer";
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
                wrongSelection.setMessage(getResources().getQuantityString(
                        R.plurals.wrong_selection_message, getRemaining()));
                wrongSelection.setIcon(R.drawable.wrong_icon);
                wrongSelection.setNoButtonVisibility(false);
                wrongSelection.setCloseButtonVisibility(false);
                wrongSelection.setYesButtonAction(
                        R.string.wrong_selection_yes_button,
                        new View.OnClickListener() {
                            public void onClick(View v) {
                                selectedAnswer = "";
                                for (int i = 0; i < ans.length; i++)
                                    ans[i].setText("");

                                for (int i = 0; i < c1.length; i++) {
                                    c1[i].setPressed(false);
                                    c5[i].setPressed(false);
                                }

                                for (int i = 0; i < c2.length; i++) {
                                    c2[i].setPressed(false);
                                    c3[i].setPressed(false);
                                    c4[i].setPressed(false);
                                }
                                chkAnswer.setText("Enter Your Answer");
                                attempts++;
                                wrongSelection.cancel();
                            }
                        });
                dialog = wrongSelection;
                break;
            case DIALOG_ID_MAX_ATTEMPTS:
                final CustomDialog maxAttempts = new CustomDialog(this);
                maxAttempts.setTitle(R.string.max_attempts_title);
                maxAttempts.setMessage(String.format(getResources().getString(
                        R.string.max_attempts_message, showAnswers())));
                maxAttempts.setIcon(R.drawable.wrong_icon);
                maxAttempts.setNoButtonVisibility(false);
                maxAttempts.setCloseButtonVisibility(false);
                maxAttempts.setYesButtonAction(R.string.max_attempts_yes_button,
                        new View.OnClickListener() {
                            public void onClick(View v) {
                                for (int i = 0; i < correctAnswers.length; i++)
                                    correctAnswers[i] = "NoAnswer";
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
                selectionError.setYesButtonAction(
                        R.string.selection_error_yes_button,
                        new View.OnClickListener() {
                            public void onClick(View v) {
                                selectedAnswer = "";
                                chkAnswer.setText("Enter Your Answer");
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
                completedQuestions.setYesButtonAction(
                        R.string.completed_questions_yes_button,
                        new View.OnClickListener() {
                            public void onClick(View v) {
                            /*
							 * qNum = 1; chkAnswer.setText("Enter Your Answer");
							 * setQuestionInfo(qNum); scrollView.fullScroll(0);
							 * completedQuestions.cancel();
							 */
                                Intent nIntent = new Intent(MathAltActivity.this
                                        .getBaseContext(), MathAltActivity.class);
                                startActivityForResult(nIntent, 0);
                                MathAltActivity.this.finish();
                            }

                        });
                completedQuestions.setNoButtonAction(
                        R.string.completed_questions_no_button,
                        new View.OnClickListener() {

                            public void onClick(View v) {
                                MathAltActivity.this.finish();
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
                ((CustomDialog) dialog).setMessage(getResources()
                        .getQuantityString(R.plurals.wrong_selection_message,
                                getRemaining()));
                break;
            case DIALOG_ID_MAX_ATTEMPTS:
                ((CustomDialog) dialog).setMessage(String.format(getResources()
                        .getString(R.string.max_attempts_message, showAnswers())));
                break;
        }
    }

    private void setQuestionInfo(int questionNum) {
        attempts = 1;
        maxAttempts = 2;

        switch (questionNum) {
            case 1:
                quest_p1.setText("Byron purchased a $5000 certificate of deposit (CD) at his local bank. The CD will "
                        + "pay him 7% simple interest at the end of 2 years.");
                qImage.setImageResource(R.drawable.trans);
                qImage.setVisibility(8);
                quest_p2.setText("How much INTEREST, in dollars, will Byron have earned from his CD at the end of the 2-year period?");

                numAnswers = 1;
                correctAnswers[0] = "700";
                correctAnswers[1] = "NoAnswer";
                correctAnswers[2] = "NoAnswer";
                correctAnswers[3] = "NoAnswer";
                correctAnswers[4] = "NoAnswer";

                for (int i = 0; i < ans.length; i++)
                    ans[i].setText("");

                for (int i = 0; i < c1.length; i++) {
                    c1[i].setPressed(false);
                    c5[i].setPressed(false);
                }

                for (int i = 0; i < c2.length; i++) {
                    c2[i].setPressed(false);
                    c3[i].setPressed(false);
                    c4[i].setPressed(false);
                }

                chkAnswer.setText("Enter Your Answer");
                qNum++;
                selectedAnswer = "";
                break;
            case 2:
                quest_p1.setText("One ingredient in the sauce Kyle is perparing for tonight's dinner is 1/2 teaspoon of red pepper. If "
                        + "the recipe he is using is designed to make enough sauce for 8 servings, but Kyle is making only 4 servings, what "
                        + "fraction of a teaspoon of red pepper should he use?");
                qImage.setImageResource(R.drawable.trans);
                qImage.setVisibility(8);
                quest_p2.setText("");

                numAnswers = 3;
                correctAnswers[0] = "1/4";
                correctAnswers[1] = "0.25";
                correctAnswers[2] = ".25";
                correctAnswers[3] = "NoAnswer";
                correctAnswers[4] = "NoAnswer";

                for (int i = 0; i < ans.length; i++)
                    ans[i].setText("");

                for (int i = 0; i < c1.length; i++) {
                    c1[i].setPressed(false);
                    c5[i].setPressed(false);
                }

                for (int i = 0; i < c2.length; i++) {
                    c2[i].setPressed(false);
                    c3[i].setPressed(false);
                    c4[i].setPressed(false);
                }

                chkAnswer.setText("Enter Your Answer");
                qNum++;
                selectedAnswer = "";
                break;
            default:
                showDialog(DIALOG_ID_COMPLETED_QUESTIONS);
        }
    }
}