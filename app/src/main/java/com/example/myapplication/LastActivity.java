package com.example.myapplication;

import android.annotation.SuppressLint;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.values.Countries;
import com.example.myapplication.values.ListCountries;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class LastActivity extends AppCompatActivity implements OnMapReadyCallback{


    String id, name, country;
    ArrayList<Countries> countries;
    String key = "fe96944466d9d8940265f81830b1de75";
    TextView country_view;
    TextView feels_like_view;
    TextView description_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last);
        countries = ListCountries.Get();
        getIntentData();
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.test_maps);
        mapFragment.getMapAsync(this);
        country_view = findViewById(R.id.country_view);
        feels_like_view = findViewById(R.id.feels_like);
        description_view = findViewById(R.id.description_view);
        String url = "https://api.openweathermap.org/data/2.5/weather?q="+ country +"&appid=" + key + "&units=metric&lang=ru";
        new GetURLData().execute(url);
    }

    public void getIntentData() {
        if (getIntent().hasExtra("id") && getIntent().hasExtra("name")
                && getIntent().hasExtra("country")) {
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");

            for (Countries tmp : countries)
                if (Objects.equals(Integer.parseInt(getIntent().getStringExtra("country")), tmp.getFlag())) {
                    country = tmp.getCountry();
                    break;
                }
        }
    }

    @Override
    public void onMapReady(@NonNull @NotNull GoogleMap googleMap) {
        try {
            List<Address> addresses = new Geocoder(this, Locale.getDefault()).getFromLocationName(country, 1);
            if (addresses != null && !addresses.isEmpty()) {
                LatLng country = new LatLng(addresses.get(0).getLatitude(),
                        addresses.get(0).getLongitude());
                googleMap.addMarker(new MarkerOptions()
                        .position(country)
                        .title("country of player"));
                googleMap.moveCamera(CameraUpdateFactory.newLatLng(country));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @SuppressLint("StaticFieldLeak")
    private class GetURLData extends AsyncTask<String, String, String>{

        @Override
        protected String doInBackground(String... strings) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            try {
                connection = (HttpURLConnection) new URL(strings[0]).openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuilder buffer = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null)
                    buffer.append(line).append("\n");

                return buffer.toString();

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                connection.disconnect();
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result){
            super.onPostExecute(result);
            try {
                JSONObject jsonObject = new JSONObject(result);
                country_view.setText(jsonObject.getString("name"));
                feels_like_view.setText(String.valueOf(jsonObject.getJSONObject("main").getDouble("feels_like")));
                description_view.setText(jsonObject.getJSONArray("weather").getJSONObject(0).getString("description"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}