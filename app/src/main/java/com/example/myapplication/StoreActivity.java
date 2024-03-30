package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class StoreActivity extends AppCompatActivity {

    ListView lvProgram;
    String[] programName = {"Новый год", "Хэллоуин"};
    String[] programDescription = {"100 руб", "100 руб"};
    int[] programImages = {R.drawable.newyear, R.drawable.halloween};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        lvProgram = findViewById(R.id.lvProgram);
        StoreAdapter programAdapter = new StoreAdapter(this, programName, programImages, programDescription);
        lvProgram.setAdapter(programAdapter);
    }

    public void to_main(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}