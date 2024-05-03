package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.myapplication.db.DataBase;

public class AddUserActivity2 extends AppCompatActivity {


    EditText user_input;
    Spinner choice_country;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user2);

        user_input = findViewById(R.id.Name_new_user);
        choice_country = findViewById(R.id.choice_country);

        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.countries));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        choice_country.setAdapter(adapter);
    }

    public void dataBaseSQLite(View view) {
        DataBase db = new DataBase(this);
        db.addUser(user_input.getText().toString().trim(), 0, choice_country.getSelectedItem().toString().trim());
        startActivity(new Intent(this, MainActivity.class));
    }
}