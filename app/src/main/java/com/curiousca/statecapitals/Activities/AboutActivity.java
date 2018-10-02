package com.curiousca.statecapitals.Activities;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.curiousca.statecapitals.R;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("message/rfc822");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"curiousaps@gmail.com"});
                intent.putExtra(Intent.EXTRA_SUBJECT, "Capitals");
                intent.putExtra(Intent.EXTRA_TEXT, "");
                try {
                    startActivity(Intent.createChooser(intent, "Send email..."));
                }catch (ActivityNotFoundException ex){
                    Toast.makeText(AboutActivity.this, "There are no email clients available for this device.",
                            Toast.LENGTH_SHORT).show();
                }            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
