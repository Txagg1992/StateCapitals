package com.curiousca.statecapitals.Activities;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.curiousca.statecapitals.DataClasses.Question;
import com.curiousca.statecapitals.DataClasses.QuizDbHelper;
import com.curiousca.statecapitals.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

import es.dmoral.toasty.Toasty;

public class QuizActivity extends AppCompatActivity {

    public static final String EXTRA_SCORE = "extraScore";
    private static final long COUNTDOWN_IN_MILLIS = 30000;
    public static final String KEY_SCORE = "keyScore";
    public static final String KEY_QUESTION_COUNT = "keyQuestionCount";
    public static final String KEY_MILLIS_LEFT = "keyMillisLeft";
    public static final String KEY_ANSWERED = "keyAnswered";
    public static final String KEY_QUESTION_LIST = "keyQuestionList";

    private TextView textViewQuestion;
    private TextView textViewScore;
    private TextView textViewQuestionCount;
    private TextView textViewCategory;
    private TextView textViewCountDown;
    private RadioGroup radioGroup;
    private RadioButton rButton1;
    private RadioButton rButton2;
    private RadioButton rButton3;
    private RadioButton rButton4;
    private Button buttonConfirmNext;

    private ColorStateList textColorDefaultRButton;
    private ColorStateList textColorDefaultCountDown;

    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;

    private ArrayList<Question> questionList;
    private int questionCounter;
    private int questionCountTotal;
    private Question currentQuestion;

    private int score;
    private boolean answered;

    private long backPressedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        textViewQuestion = findViewById(R.id.text_view_question);
        textViewScore = findViewById(R.id.text_view_score);
        textViewQuestionCount = findViewById(R.id.text_view_question_count);
        textViewCategory = findViewById(R.id.text_view_category);
        textViewCountDown = findViewById(R.id.text_view_countdown);
        radioGroup = findViewById(R.id.radio_group);
        rButton1 = findViewById(R.id.radio_button1);
        rButton2 = findViewById(R.id.radio_button2);
        rButton3 = findViewById(R.id.radio_button3);
        rButton4 = findViewById(R.id.radio_button4);
        buttonConfirmNext = findViewById(R.id.button_confirm_next);

        textColorDefaultRButton = rButton1.getTextColors();
        textColorDefaultCountDown = textViewCountDown.getTextColors();

        Intent intent = getIntent();
        int categoryID = intent.getIntExtra(MainActivity.EXTRA_CATEGORY_ID, 0);
        String categoryName = intent.getStringExtra(MainActivity.EXTRA_CATEGORY_NAME);

        textViewCategory.setText("Category: " + categoryName);

        if (savedInstanceState == null) {
            QuizDbHelper dbHelper = QuizDbHelper.getInstance(this);
            questionList = dbHelper.getQuestions(categoryID);
            questionCountTotal = questionList.size();
            Collections.shuffle(questionList);

            showNextQuestion();
        }else {
            questionList = savedInstanceState.getParcelableArrayList(KEY_QUESTION_LIST);
            questionCountTotal = questionList.size();
            questionCounter = savedInstanceState.getInt(KEY_QUESTION_COUNT);
            currentQuestion = questionList.get(questionCounter -1);
            score = savedInstanceState.getInt(KEY_SCORE);
            timeLeftInMillis = savedInstanceState.getLong(KEY_MILLIS_LEFT);
            answered = savedInstanceState.getBoolean(KEY_ANSWERED);

            if (! answered){
                startCountDown();
            }else {
                updateCountDownText();
                showSolution();
            }
        }

        buttonConfirmNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (! answered){
                    if (rButton1.isChecked() || rButton2.isChecked() || rButton3.isChecked() ||rButton4.isChecked()){
                        checkAnswer();
                    }else{
                        Toast.makeText(QuizActivity.this, "Please select an answer.", Toast.LENGTH_LONG).show();
                    }
                }else {
                    showNextQuestion();
                }
            }
        });
    }

    private void showNextQuestion(){
        rButton1.setTextColor(textColorDefaultRButton);
        rButton1.setTextSize(24);
        rButton2.setTextColor(textColorDefaultRButton);
        rButton2.setTextSize(24);
        rButton3.setTextColor(textColorDefaultRButton);
        rButton3.setTextSize(24);
        rButton4.setTextColor(textColorDefaultRButton);
        rButton4.setTextSize(24);
        radioGroup.clearCheck();

        if (questionCounter < questionCountTotal){
            currentQuestion = questionList.get(questionCounter);

            textViewQuestion.setText(currentQuestion.getQuestion());
            rButton1.setText(currentQuestion.getOption1());
            rButton2.setText(currentQuestion.getOption2());
            rButton3.setText(currentQuestion.getOption3());
            rButton4.setText(currentQuestion.getOption4());

            questionCounter ++;
            textViewQuestionCount.setText("Question: " + questionCounter + "/" + questionCountTotal);
            answered = false;
            //buttonConfirmNext.setBackgroundColor(Color.BLUE);
            buttonConfirmNext.setText("Confirm!");

            timeLeftInMillis = COUNTDOWN_IN_MILLIS;
            startCountDown();
        }else {
            finishQuiz();
        }
    }

    private void startCountDown(){
        countDownTimer = new CountDownTimer(timeLeftInMillis + 100, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                timeLeftInMillis = 0;
                updateCountDownText();
                checkAnswer();
            }
        }.start();
    }

    private void updateCountDownText(){
        int minutes = (int) ((timeLeftInMillis / 1000) / 60);
        int seconds = (int) ((timeLeftInMillis / 1000) % 60);

        String timeFormated = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        textViewCountDown.setText(timeFormated);
        if (timeLeftInMillis < 30000){
            textViewCountDown.setTextColor(Color.GREEN);

            if (timeLeftInMillis < 20000){
                textViewCountDown.setTextColor(Color.YELLOW);

                if (timeLeftInMillis < 10000){
                    textViewCountDown.setTextColor(Color.RED);
                }
            }
        }else {
            textViewCountDown.setTextColor(textColorDefaultCountDown);
        }
    }

    private void checkAnswer(){
        answered = true;

        countDownTimer.cancel();

        RadioButton rButtonSelected = findViewById(radioGroup.getCheckedRadioButtonId());
        int answerNr = radioGroup.indexOfChild(rButtonSelected) +1;

        if (answerNr == currentQuestion.getAnswerNr()){
            if (timeLeftInMillis < 30000 && timeLeftInMillis > 20000){
                score = score + 5;
            }else if (timeLeftInMillis < 20000 && timeLeftInMillis > 10000){
                score = score +3;
            }else {
                score ++;
            }
            textViewScore.setText("Score: " + score);
            correctToast();
        }else {
            inCorrectToast();
        }
        showSolution();
    }

    private void showSolution(){
        rButton1.setTextColor(Color.RED);
        rButton2.setTextColor(Color.RED);
        rButton3.setTextColor(Color.RED);
        rButton4.setTextColor(Color.RED);

        switch (currentQuestion.getAnswerNr()){
            case 1:
                rButton1.setTextColor(Color.GREEN);
                rButton1.setTextSize(36);
                textViewQuestion.setText(currentQuestion.getQuestion() + "\n" + currentQuestion.getOption1() + " is correct.");
                break;
            case 2:
                rButton2.setTextColor(Color.GREEN);
                rButton2.setTextSize(36);
                textViewQuestion.setText(currentQuestion.getQuestion() + "\n" + currentQuestion.getOption2() + " is correct.");
                break;
            case 3:
                rButton3.setTextColor(Color.GREEN);
                rButton3.setTextSize(36);
                textViewQuestion.setText(currentQuestion.getQuestion() + "\n" + currentQuestion.getOption3() + " is correct.");
                break;
            case 4:
                rButton4.setTextColor(Color.GREEN);
                rButton4.setTextSize(36);
                textViewQuestion.setText(currentQuestion.getQuestion() + "\n" + currentQuestion.getOption4() + " is correct.");
                break;
        }

        if (questionCounter < questionCountTotal){
            buttonConfirmNext.setText("NEXT");
        }else {
            buttonConfirmNext.setText("Finish");
        }
    }

    private void correctToast(){
        Toast toast2 = Toasty.success(this, "Awesome! Answer is correct", Toast.LENGTH_SHORT);
        toast2.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL, 0, 0);
        toast2.show();
    }
    private void inCorrectToast(){
        Toast toast2 = Toasty.error(this, "Answer incorrect", Toast.LENGTH_SHORT);
        toast2.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL, 0, 0);
        toast2.show();
    }

    private void finishQuiz(){
        Intent resultIntent = new Intent();
        resultIntent.putExtra(EXTRA_SCORE, score);
        setResult(RESULT_OK, resultIntent);
        finish();
    }

    @Override
    public void onBackPressed() {

        if (backPressedTime + 2000 > System.currentTimeMillis()){
            finishQuiz();
        }else {
            Toast.makeText(this, "Press back button again to exit.", Toast.LENGTH_SHORT).show();
        }

        backPressedTime = System.currentTimeMillis();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (countDownTimer != null){
            countDownTimer.cancel();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_SCORE, score);
        outState.putInt(KEY_QUESTION_COUNT, questionCounter);
        outState.putLong(KEY_MILLIS_LEFT, timeLeftInMillis);
        outState.putBoolean(KEY_ANSWERED, answered);
        outState.putParcelableArrayList(KEY_QUESTION_LIST, questionList);
    }
}
