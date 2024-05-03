package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapplication.db.DataBase;
import com.example.myapplication.values.Countries;
import com.example.myapplication.values.ListCountries;

import java.util.ArrayList;
import java.util.Objects;

public class Update_user extends AppCompatActivity {

    EditText user_input;
    Spinner choice_country;

    String id, name;
    int country;
    ArrayList<Countries> countries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);

        user_input = findViewById(R.id.Name_update_user);
        choice_country = findViewById(R.id.update_country);
        countries = ListCountries.Get();

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,
                getResources().getStringArray(R.array.countries));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        choice_country.setAdapter(adapter);

        getIntentData(adapter);
    }

    public void updateDataBaseSQLite(View view) {
        DataBase db = new DataBase(this);
        db.updateData(id, user_input.getText().toString().trim(), 1000, choice_country.getSelectedItem().toString().trim());
        startActivity(new Intent(this, ListofleadersActivity.class));
    }

    public void deleteDataBaseSQLite(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete" + name + " ?");
        builder.setMessage("Are you sure you want to delete " + name + " ?");
        builder.setPositiveButton("Yes", (dialog, which) -> {
            DataBase db = new DataBase(Update_user.this);
            db.deleteData(id);
            startActivity(new Intent(Update_user.this, ListofleadersActivity.class));
        });
        builder.setNegativeButton("No", (dialog, which) ->
                startActivity(new Intent(Update_user.this, ListofleadersActivity.class)));
        builder.create().show();
    }


    public void getIntentData(ArrayAdapter adapter) {
        if (getIntent().hasExtra("id") && getIntent().hasExtra("name")
                && getIntent().hasExtra("country")) {
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            country = Integer.parseInt(getIntent().getStringExtra("country"));

            user_input.setText(name);
            for (Countries tmp : countries)
                if (Objects.equals(country, tmp.getFlag())) {
                    choice_country.setSelection(adapter.getPosition(tmp.getCountry()));
                    break;
                }
        } else
            Toast.makeText(this, "Fail", Toast.LENGTH_SHORT).show();

    }
}