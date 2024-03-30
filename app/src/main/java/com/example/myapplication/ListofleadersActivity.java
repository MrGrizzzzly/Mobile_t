package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class ListofleadersActivity extends AppCompatActivity {

    ListView leaders;
    String[] id = {"1"};
    String[] user = {"Name"};
    String[] score = {"0"};
    int[] programImages = {R.drawable.newyear};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listofleaders);

        leaders = findViewById(R.id.leaders);
        StoreAdapter2 programAdapter = new StoreAdapter2(this, id, user, score, programImages);
        leaders.setAdapter(programAdapter);
    }

    public void to_main(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}