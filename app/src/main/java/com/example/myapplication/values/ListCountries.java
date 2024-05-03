package com.example.myapplication.values;

import com.example.myapplication.R;

import java.util.ArrayList;



public class ListCountries {


    public static ArrayList<Countries> Get() {

        ArrayList<Countries> countries = new ArrayList<>();
        countries.add(new Countries("Germany", R.drawable.germany));
        countries.add(new Countries("France", R.drawable.france));
        countries.add(new Countries("Italy", R.drawable.italy));
        countries.add(new Countries("Russia", R.drawable.russia));

        return countries;
    }

}
