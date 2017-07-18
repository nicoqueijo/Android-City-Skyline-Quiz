package com.nicoqueijo.cityskylinequiz.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.nicoqueijo.cityskylinequiz.R;
import com.nicoqueijo.cityskylinequiz.model.City;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

// JSON file on the cloud:
// https://api.myjson.com/bins/oajlr

/**
 *
 */
public class MainMenuActivity extends AppCompatActivity {

    // caching example
    // for all cities fetch the images...
    // Picasso.with(MainActivity.this).load(url).fetch();

    List<City> cities;
    private Button mButtonStartGame;
    private Button mButtonCityList;
    private Button mButtonSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_main);

        cities = new ArrayList<>();
        parseJson();
        cacheImagesAndLoadToMemory();

        mButtonStartGame = (Button) findViewById(R.id.startGameButton);
        mButtonCityList = (Button) findViewById(R.id.cityListButton);
        mButtonSettings = (Button) findViewById(R.id.settingsButton);

        /**
         *
         */
        mButtonStartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        /**
         *
         */
        mButtonCityList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cityListIntent = new Intent(MainMenuActivity.this, CityListActivity.class);
                startActivity(cityListIntent);
            }
        });

        /**
         *
         */
        mButtonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    /**
     *
     */
    private void parseJson() {
        try {
            JSONObject jsonObject = new JSONObject(loadJSONFromAsset());
            JSONArray jsonArray = jsonObject.getJSONArray("cities");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject cityObject = jsonArray.getJSONObject(i);
                cities.add(new City(cityObject.getString("city"), cityObject.getString("country"),
                        cityObject.getString("url")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     */
    private void cacheImagesAndLoadToMemory() {
        for (City city : cities) {
            Picasso.with(MainMenuActivity.this).load(city.getImageUrl()).fetch();
        }
    }

    /**
     * @return
     */
    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("cities.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    /**
     * @param aString
     * @return
     */
    private String getStringResourceByName(String aString) {
        String packageName = getPackageName();
        int resId = getResources().getIdentifier(aString, "string", packageName);
        return getString(resId);
    }
}
