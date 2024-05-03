package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.adapters.CustomAdapter;
import com.example.myapplication.db.DataBase;
import com.example.myapplication.values.Countries;
import com.example.myapplication.values.ListCountries;

import java.util.ArrayList;
import java.util.Objects;

public class ListofleadersActivity extends AppCompatActivity {

    RecyclerView leaders;
    DataBase dataBase;
    ArrayList<String> user_id, user_name, user_score;
    ArrayList<Integer> user_country;
    ArrayList<Countries> countries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_listofleaders);

        countries = ListCountries.Get();

        leaders = findViewById(R.id.leaders);
        dataBase = new DataBase(this);
        user_id = new ArrayList<>();
        user_name = new ArrayList<>();
        user_score = new ArrayList<>();
        user_country = new ArrayList<>();
        displayData();

        leaders.setAdapter(new CustomAdapter(ListofleadersActivity.this, user_id, user_name, user_score, user_country));
        leaders.setLayoutManager(new LinearLayoutManager(this));
    }

    public void to_main(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }

    public void displayData(){
        Cursor cursor = dataBase.readAllData();
        //if(cursor.getCount() == 0)
        //    Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        //else {
            while (cursor.moveToNext()){
                user_id.add(cursor.getString(0));
                user_name.add(cursor.getString(1));
                user_score.add(cursor.getString(2));
                for (Countries tmp : countries)
                    if (Objects.equals(cursor.getString(3), tmp.getCountry())) {
                        user_country.add(tmp.getFlag());
                        break;
                    }
            }
        //}
    }
}