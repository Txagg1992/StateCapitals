package com.curiousca.statecapitals.Activities;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.curiousca.statecapitals.DataClasses.Question;
import com.curiousca.statecapitals.DataClasses.QuizDbHelper;
import com.curiousca.statecapitals.R;

import java.util.Collections;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    private TextView textViewQuestion;
    private TextView textViewScore;
    private TextView textViewQuestionCount;
    private TextView textViewCountDown;
    private RadioGroup radioGroup;
    private RadioButton rButton1;
    private RadioButton rButton2;
    private RadioButton rButton3;
    private Button buttonConfirmNext;

    private ColorStateList textColorDefaultRButton;

    private List<Question> questionList;
    private int questionCounter;
    private int questionCountTotal;
    private Question currentQuestion;

    private int score;
    private boolean answered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        textViewQuestion = findViewById(R.id.text_view_question);
        textViewScore = findViewById(R.id.text_view_score);
        textViewQuestionCount = findViewById(R.id.text_view_question_count);
        textViewCountDown = findViewById(R.id.text_view_countdown);
        radioGroup = findViewById(R.id.radio_group);
        rButton1 = findViewById(R.id.radio_button1);
        rButton2 = findViewById(R.id.radio_button2);
        rButton3 = findViewById(R.id.radio_button3);
        buttonConfirmNext = findViewById(R.id.button_confirm_next);

        textColorDefaultRButton = rButton1.getTextColors();

        QuizDbHelper dbHelper = new QuizDbHelper(this);
        questionList = dbHelper.getAllQuestions();
        questionCountTotal = questionList.size();
        Collections.shuffle(questionList);

        showNextQuestion();

        buttonConfirmNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (! answered){
                    if (rButton1.isChecked() || rButton2.isChecked() || rButton3.isChecked()){
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
        radioGroup.clearCheck();

        if (questionCounter < questionCountTotal){
            currentQuestion = questionList.get(questionCounter);

            textViewQuestion.setText(currentQuestion.getQuestion());
            rButton1.setText(currentQuestion.getOption1());
            rButton2.setText(currentQuestion.getOption2());
            rButton3.setText(currentQuestion.getOption3());

            questionCounter ++;
            textViewQuestionCount.setText("Question: " + questionCounter + "/" + questionCountTotal);
            answered = false;
            buttonConfirmNext.setBackgroundColor(Color.GREEN);
            buttonConfirmNext.setText("Confirm!");
        }else {
            finishQuiz();
        }
    }

    private void checkAnswer(){
        answered = true;

        RadioButton rButtonSelected = findViewById(radioGroup.getCheckedRadioButtonId());
        int answerNr = radioGroup.indexOfChild(rButtonSelected) +1;

        if (answerNr == currentQuestion.getAnswerNr()){
            score ++;
            textViewScore.setText("Score: " + score);
        }

        showSolution();
    }

    private void showSolution(){
        rButton1.setTextColor(Color.RED);
        rButton2.setTextColor(Color.RED);
        rButton3.setTextColor(Color.RED);

        switch (currentQuestion.getAnswerNr()){
            case 1:
                rButton1.setTextColor(Color.GREEN);
                rButton1.setTextSize(36);
                textViewQuestion.setText(currentQuestion.getOption1() + " is correct.");
                break;
            case 2:
                rButton2.setTextColor(Color.GREEN);
                rButton2.setTextSize(36);
                textViewQuestion.setText(currentQuestion.getOption2() + " is correct.");
                break;
            case 3:
                rButton3.setTextColor(Color.GREEN);
                rButton3.setTextSize(36);
                textViewQuestion.setText(currentQuestion.getOption3() + " is correct.");
                break;
        }

        if (questionCounter < questionCountTotal){
            buttonConfirmNext.setText("NEXT");
        }else {
            buttonConfirmNext.setText("Finish");
        }
    }

    private void finishQuiz(){
        finish();
    }
}
