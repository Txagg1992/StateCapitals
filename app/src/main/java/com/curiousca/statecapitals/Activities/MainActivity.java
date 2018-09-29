package com.curiousca.statecapitals.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.curiousca.statecapitals.R;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_QUIZ = 1;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String KEY_HIGHSCORE = "keyHighScore";

    private TextView textViewHighScore;
    private int highScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewHighScore = findViewById(R.id.textView_highscore);

        loadHighScore();

        Button buttonStartQuiz = findViewById(R.id.button_start_quiz);
        buttonStartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuiz();
            }
        });
    }

    private void startQuiz(){

        Intent intent = new Intent(MainActivity.this, QuizActivity.class);
        startActivityForResult(intent, REQUEST_CODE_QUIZ);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_QUIZ){
            if (resultCode == RESULT_OK){
                int score = data.getIntExtra(QuizActivity.EXTRA_SCORE, 0);
                if (score > highScore){
                    updateHighScore(score);
                }
            }
        }
    }

    private void loadHighScore(){
        SharedPreferences sPrefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        highScore = sPrefs.getInt(KEY_HIGHSCORE, 0);
        textViewHighScore.setText("High Score: " + highScore);

    }

    private void updateHighScore(int highScoreNew){
        highScore = highScoreNew;
        textViewHighScore.setText("High Score: " + highScore);

        SharedPreferences sPref = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sPref.edit();
        editor.putInt(KEY_HIGHSCORE, highScore);
        editor.apply();
    }
}
