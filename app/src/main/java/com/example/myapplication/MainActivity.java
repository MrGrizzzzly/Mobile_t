package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.db.DataBase;
import com.example.myapplication.values.Countries;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayData();

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

    public void displayData(){
        Cursor cursor = new DataBase(this).readAllData();
        if(cursor.getCount() == 0)
            startActivity(new Intent(this, AddUserActivity2.class));
    }

}