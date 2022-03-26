package com.example.funwithflags;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int score;
    TextView txt_highscore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent i = getIntent();
        score = i.getIntExtra("count", getSharedPreferences("MySharedPref", MODE_PRIVATE).getInt("highscore", 0));

        txt_highscore = findViewById(R.id.txt_highscore);
        txt_highscore.setText("Highscore: " + score);

        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putInt("highscore", score);
        myEdit.commit();
    }

    //klicke auf den start button und gelange zur eigentlichen Seite, auf der man spielt
    public void launchGame(View v){
        startActivity(new Intent(MainActivity.this, GameActivity.class));
    }
}