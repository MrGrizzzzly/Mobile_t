package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;

import com.example.myapplication.adapters.StoreAdapter;

public class StoreActivity extends AppCompatActivity {

    GridView lvProgram;
    String[] programName = {"Новый год", "Хэллоуин", "оформ №1", "оформ №1"};
    String[] programDescription = {"100 руб", "100 руб", "??? руб", "??? руб"};
    int[] programImages = {R.drawable.newyear, R.drawable.halloween, R.drawable.dlc, R.drawable.dlc};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        lvProgram = findViewById(R.id.lvProgram);
        lvProgram.setAdapter(new StoreAdapter(this, programName, programImages, programDescription));
    }

    public void to_main(View view) {
        startActivity(new Intent(this, MainActivity.class));

    }
}