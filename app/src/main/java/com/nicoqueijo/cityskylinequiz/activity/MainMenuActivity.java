package com.nicoqueijo.cityskylinequiz.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import java.lang.reflect.Method;
import java.util.ArrayList;

// JSON file on the cloud:
// https://api.myjson.com/bins/ijzsv

/**
 *
 */
public class MainMenuActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    ArrayList<City> mCities;
    private Button mButtonPlayGame;
    private Button mButtonCityList;
    private Button mButtonSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences("settings", Context.MODE_PRIVATE);
        setTheme(sharedPreferences.getInt("theme", R.style.AppThemeLight));
        setContentView(R.layout.activity_menu_main);

        mCities = new ArrayList<>();
        parseJsonAndCreateCityObjects();
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
                Intent intentPlayGame = new Intent(MainMenuActivity.this, QuizActivity.class);
                intentPlayGame.putExtra("cityList", mCities);
                startActivity(intentPlayGame);
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
                Intent intentSettings = new Intent(MainMenuActivity.this, SettingsActivity.class);
                startActivity(intentSettings);
            }
        });

    }

    /**
     * Checks if the theme has been changed. If so the current activity restarts so the new theme
     * can be applied to all views.
     */
    @Override
    public void onResume() {
        super.onResume();
        if (sharedPreferences.getInt("theme", R.style.AppThemeLight) != getThemeId()) {
            this.finish();
            final Intent intent = this.getIntent();
            this.startActivity(intent);
        }
    }

    /**
     * Gets the resource id of the current theme.
     *
     * @return the resource id as an int.
     */
    int getThemeId() {
        try {
            Class<?> wrapper = Context.class;
            Method method = wrapper.getMethod("getThemeResId");
            method.setAccessible(true);
            return (Integer) method.invoke(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
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
    private void parseJsonAndCreateCityObjects() {
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

    // MAKE SPLASH SCREEN FOR THIS IF CACHING THEM FOR THE FIRST TIME!!!!
    /**
     * Caches the city image from each model object (if not cached already) and loads it to memory.
     */
    private void cacheImagesAndLoadToMemory() {
        for (City city : mCities) {
            Picasso.with(MainMenuActivity.this).load(city.getImageUrl()).fetch();
        }
    }
}
