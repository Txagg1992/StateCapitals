package com.curiousca.statecapitals.Activities;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.curiousca.statecapitals.DataClasses.Category;
import com.curiousca.statecapitals.DataClasses.QuizDbHelper;
import com.curiousca.statecapitals.R;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_QUIZ = 1;
    public static final String EXTRA_CATEGORY_ID = "extraCategoryID";
    public static final String EXTRA_CATEGORY_NAME = "extraCategoryName";

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String KEY_HIGHSCORE = "keyHighScore";

    private TextView textViewHighScore;
    private Spinner spinnerCategory;
    private int highScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewHighScore = findViewById(R.id.textView_highscore);
        spinnerCategory = findViewById(R.id.spinner_category);

        loadCategories();
        loadHighScore();

        Button buttonStartQuiz = findViewById(R.id.button_start_quiz);
        buttonStartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuiz();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.contact:
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("message/rfc822");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"curiousaps@gmail.com"});
                intent.putExtra(Intent.EXTRA_SUBJECT, "Capitals");
                intent.putExtra(Intent.EXTRA_TEXT, "");
                try {
                    startActivity(Intent.createChooser(intent, "Send email..."));
                }catch (ActivityNotFoundException ex){
                    Toast.makeText(this, "There are no email clients available for this device.",
                            Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.share:
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(Intent.EXTRA_TEXT,
                        "https://play.google.com/store/apps/details?id=com.curiousca.statecapitals");
                startActivity(Intent.createChooser(sharingIntent, "Share via..."));
                break;
            case R.id.about:
                Intent i = new Intent(this, AboutActivity.class);
                this.startActivity(i);
                break;
            case R.id.reset:
                highScore = 0;
                textViewHighScore.setText("High Score: " + highScore);
        }
        return super.onOptionsItemSelected(item);
    }

    private void startQuiz(){
        Category selectedCategory = (Category) spinnerCategory.getSelectedItem();
        int categoryID = selectedCategory.getId();
        String categoryName = selectedCategory.getName();

        Intent intent = new Intent(MainActivity.this, QuizActivity.class);
        intent.putExtra(EXTRA_CATEGORY_ID, categoryID);
        intent.putExtra(EXTRA_CATEGORY_NAME, categoryName);
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

    private void loadCategories(){
        QuizDbHelper dbHelper = QuizDbHelper.getInstance(this);
        List<Category> categories = dbHelper.getAllCategories();

        ArrayAdapter<Category> adapterCategory = new ArrayAdapter<>(this,
                android.R.layout.simple_selectable_list_item, categories);
        adapterCategory.setDropDownViewResource(android.R.layout.simple_list_item_checked);
        spinnerCategory.setAdapter(adapterCategory);
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
