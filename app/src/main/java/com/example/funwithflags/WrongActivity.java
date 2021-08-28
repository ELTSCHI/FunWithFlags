package com.example.funwithflags;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class WrongActivity extends AppCompatActivity {

    TextView tv_wrong;
    TextView tv_correct;
    ImageView img_wrong;
    ImageView img_correct;
    Button home;
    int score;
    TextView tv_score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wrong);

        //init variables
        tv_wrong = findViewById(R.id.tv_wrong);
        tv_correct = findViewById(R.id.tv_correct);
        img_wrong = findViewById(R.id.img_wrong);
        img_correct = findViewById(R.id.img_correct);
        home = findViewById(R.id.try_again);
        tv_score =findViewById(R.id.tv_score);

        //get Variables from game activity
        Intent i = getIntent();
        String land = i.getStringExtra("land");
        String iso = i.getStringExtra("iso");
        String answer = i.getStringExtra("answer");
        String answer_iso = i.getStringExtra("answer_iso");
        int count = i.getIntExtra("count", 0);
        score = count;

        //set Picture and Text
        tv_score.setText("Score: " + score);
        tv_wrong.setText(answer);
        tv_correct.setText(land);
        int img_wrong_id = getResources().getIdentifier(answer_iso, "drawable", getPackageName());
        img_wrong.setImageResource(img_wrong_id);
        int img_correct_id = getResources().getIdentifier(iso, "drawable", getPackageName());
        img_correct.setImageResource(img_correct_id);

    }

    public void getHome(View v){
        Intent i = new Intent(WrongActivity.this, MainActivity.class);
        i.putExtra("count", score);
        startActivity(i);
    }
}