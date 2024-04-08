package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void start_game(View view) {
        startActivity(new Intent(this, GameActivity.class));
    }

    public void settings(View view) {
        startActivity(new Intent(this, SettingsActivity.class));
    }

    public void open_store(View view) {
        startActivity(new Intent(this, StoreActivity.class));
    }
    public void open_table_leaders(View view) {
        startActivity(new Intent(this, ListofleadersActivity.class));
    }
}