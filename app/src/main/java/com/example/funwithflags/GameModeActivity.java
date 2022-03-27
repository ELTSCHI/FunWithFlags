package com.example.funwithflags;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class GameModeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_mode);
    }

    public void launchEurope(View v){
        Intent i = new Intent(GameModeActivity.this, GameActivity.class);
        i.putExtra("modus", "europe");
        startActivity(i);
        this.finish();
    }
    public void launchMain(View v){
        Intent i = new Intent(GameModeActivity.this, GameActivity.class);
        i.putExtra("modus", "main");
        startActivity(i);
        this.finish();
    }
    public void launchAsia(View v){
        Intent i = new Intent(GameModeActivity.this, GameActivity.class);
        i.putExtra("modus", "asia");
        startActivity(i);
        this.finish();
    }
    public void launchAfrica(View v){
        Intent i = new Intent(GameModeActivity.this, GameActivity.class);
        i.putExtra("modus", "africa");
        startActivity(i);
        this.finish();
    }
    public void launchAmerica(View v){
        Intent i = new Intent(GameModeActivity.this, GameActivity.class);
        i.putExtra("modus", "america");
        startActivity(i);
        this.finish();
    }public void launchOceania(View v){
        Intent i = new Intent(GameModeActivity.this, GameActivity.class);
        i.putExtra("modus", "oceania");
        startActivity(i);
        this.finish();
    }

}