package com.nicoqueijo.cityskylinequiz.activity;

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

// JSON file on the cloud:
// https://api.myjson.com/bins/oajlr

/**
 *
 */
public class MainMenuActivity extends AppCompatActivity {

    ArrayList<City> mCities;
    private Button mButtonPlayGame;
    private Button mButtonCityList;
    private Button mButtonSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_main);

        mCities = new ArrayList<>();
        parseJson();
        cacheImagesAndLoadToMemory();

        mButtonPlayGame = (Button) findViewById(R.id.button_start_game);
        mButtonCityList = (Button) findViewById(R.id.button_city_list);
        mButtonSettings = (Button) findViewById(R.id.button_settings);

        /**
         *
         */
        mButtonPlayGame.setOnClickListener(new View.OnClickListener() {
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
                Intent intentCityList = new Intent(MainMenuActivity.this, CityListActivity.class);
                intentCityList.putExtra("cityList", mCities);
                startActivity(intentCityList);
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
     * Caches the city image from each model object (if not cached already) and loads it to memory.
     */
    private void cacheImagesAndLoadToMemory() {
        for (City city : mCities) {
            Picasso.with(MainMenuActivity.this).load(city.getImageUrl()).fetch();
        }
    }

    /**
     * Parses the JSON file stored in the assets folder and returns a String representation of it.
     *
     * @return the JSON file in String format
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
     * Takes the String JSON and puts it in a JSON object. Then populates the array of model
     * objects with the data contained in the JSON object.
     */
    private void parseJson() {
        try {
            JSONObject jsonObject = new JSONObject(loadJSONFromAsset());
            JSONArray jsonArray = jsonObject.getJSONArray("cities");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject cityObject = jsonArray.getJSONObject(i);
                mCities.add(new City(cityObject.getString("city"), cityObject.getString("country"),
                        cityObject.getString("imageUrl"), cityObject.getString("coordinates"),
                        cityObject.getString("wikiUrl")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves String resources using a String instead of an int.
     *
     * @param name name of the String resource
     * @return the String resource
     */
    private String getStringResourceByName(String name) {
        int resId = getResources().getIdentifier(name, "string", this.getPackageName());
        return getString(resId);
    }

    /**
     * Retrieves drawable resources using a String instead of an int.
     *
     * @param name name of the drawable resource
     * @return the drawable resource id
     */
    private int getDrawableResourceByName(String name) {
        return getResources().getIdentifier(name, "drawable", this.getPackageName());
    }
}