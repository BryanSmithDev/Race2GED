package edu.mecc.race2ged;

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
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

public class ScienceMultiActivity extends Activity {

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

                        mp = MediaPlayer.create(ScienceMultiActivity.this, R.raw.noanswer);
                        mp.start();
                        mp.setOnCompletionListener(new OnCompletionListener() {
                            public void onCompletion(MediaPlayer mp) {
                                mp.release();
                            }
                        });
                    } else if (selectedAnswer == correctAnswer) {
                        id = DIALOG_ID_CORRECT_SELECTION;

                        mp = MediaPlayer.create(ScienceMultiActivity.this, R.raw.correct);
                        mp.start();
                        mp.setOnCompletionListener(new OnCompletionListener() {
                            public void onCompletion(MediaPlayer mp) {
                                mp.release();
                            }
                        });
                    } else if (selectedAnswer != correctAnswer) {
                        if (attempts < maxAttempts) {
                            id = DIALOG_ID_WRONG_SELECTION;

                            mp = MediaPlayer.create(ScienceMultiActivity.this, R.raw.incorrect);
                            mp.start();
                            mp.setOnCompletionListener(new OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();
                                }
                            });
                        } else {
                            id = DIALOG_ID_MAX_ATTEMPTS;

                            mp = MediaPlayer.create(ScienceMultiActivity.this, R.raw.incorrect);
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
                ScienceMultiActivity.this.finish();
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
                        ScienceMultiActivity.this.finish();
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
                additional_info.setText("");
                additional_info.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {

                    }
                });
                quest_p1.setText("The closer we get to Earth's surface, the stronger the force of Earth's gravity becomes. " +
                        "This force of gravity (measured in Newtons, or N) has a much stronger effect on an individual " +
                        "on Earth's surface than on someone who is thousands of kilometers above Earth's atmosphere. (Note: " +
                        "One kilometer equals 0.6 mile.)");
                qImage.setImageResource(R.drawable.astronaut_cartoon);
                quest_p2.setText("Which of the following statements best describes the force of gravity's effect on an astronaut " +
                        "floating more than 30,000 kilometers above Earth's surface?" +
                        "\n" +
                        "Gravity's effect");
                a1.setText("is the same as on Earth's surface");
                a2.setText("is much less than on Earth's surface");
                a3.setText("is significantly affected by weather conditions");
                a4.setText("varies with the astronaut's age, diet, and physical condition");
                a5.setText("would be less on an astronaut than on people in other occupations");
                correctAnswer = "is much less than on Earth's surface";
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
                quest_p1.setText("Clay soil forms a fairly effective barrier against the movements of water. " +
                        "It also swells and shrinks significantly as its water content changes. Sandy soil, in " +
                        "contrast, allows water to move freely and does not change shape as the water content varies.");
                qImage.setImageResource(R.drawable.trans);
                qImage.setVisibility(8);
                quest_p2.setText("In which statement is the appropriate soil selected for its intended site?");
                a1.setText("Sandy soil would make a good lining for a toxic waste site.");
                a2.setText("Clay soil would work well in a drain field.");
                a3.setText("Clay soil would be a good foundation for a large building.");
                a4.setText("Clay soil would from a good liner if a person built a pond.");
                a5.setText("A sandy lake bottom would prevent water from seeping out of the lake.");
                correctAnswer = "Clay soil would from a good liner if a person built a pond.";
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
                quest_p1.setText("The root hair of a plant, shown in the diagram below, is the most efficient " +
                        "way for the plant to absorb water from surrounding soil.");
                qImage.setImageResource(R.drawable.cell_drawing);
                quest_p2.setText("At what point is the flow of water the GREATEST?");
                a1.setText("C to B");
                a2.setText("C to A");
                a3.setText("D to B");
                a4.setText("A to B");
                a5.setText("D to C");
                correctAnswer = "A to B";
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
                quest_p1.setText("A cook decides to recover some table salt that has been completely dissolved " +
                        "in water. Which of the following processes would be the most effective method of extracting " +
                        "salt from the solution?");
                qImage.setImageResource(R.drawable.trans);
                qImage.setVisibility(8);
                quest_p2.setText("");
                a1.setText("spinning the solution in a mixer");
                a2.setText("boiling away the water");
                a3.setText("pouring the solution through cloth");
                a4.setText("dripping the solution through a paper filter");
                a5.setText("bubbling oxygen through the solution");
                correctAnswer = "boiling away the water";
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
                quest_p1.setText("A large fiberglass tank was placed in a pit, as shown below. " +
                        "Before pipes could be attached and the tank filled with gasoline, however, " +
                        "the workers were asked to move the heavy tank to another location");
                qImage.setImageResource(R.drawable.tank_problem);
                quest_p2.setText("Which of the following methods would be the best way to raise the " +
                        "tank off the bottom of the pit so that cables could be placed under the tank?");
                a1.setText("Fill the tank with gasoline.");
                a2.setText("Fill the tank with water.");
                a3.setText("Fill the pit with water.");
                a4.setText("Fill the pit with water and fill the tank with gasoline.");
                a5.setText("Fill both the pit and the tank with water.");
                correctAnswer = "Fill the pit with water.";
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
                quest_p1.setText("Viscosity is a measure of the internal resistance of a fluid to flow. " +
                        "For example, motor oil is more viscous than water. The viscosity of a fluid will " +
                        "change with temperature. The graph below illustrates how the viscosity of oil changes " +
                        "with temperature.");
                qImage.setImageResource(R.drawable.graph_problem);
                quest_p2.setText("Under which situation will the viscosity of the oil increase?");
                a1.setText("as temperature decreases");
                a2.setText("when mixed with water");
                a3.setText("as its volume decreases");
                a4.setText("as its flow increases");
                a5.setText("if its resistance stabilizes");
                correctAnswer = "as temperature decreases";
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
}