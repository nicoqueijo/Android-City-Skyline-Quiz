package com.nicoqueijo.cityskylinequiz.activity;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.nicoqueijo.cityskylinequiz.R;
import com.nicoqueijo.cityskylinequiz.helper.ResourceByNameRetriever;
import com.nicoqueijo.cityskylinequiz.helper.SystemInfo;
import com.nicoqueijo.cityskylinequiz.model.City;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Locale;

// JSON file on the cloud:
// https://api.myjson.com/bins/x3m9t

/**
 *
 */
public class MainMenuActivity extends AppCompatActivity {

    public static final String DEVELOPER_GITHUB_URL = "https://github.com/nicoqueijo";
    public static final String DEVELOPER_EMAIL = "queijonicolas@gmail.com";

    private SharedPreferences mSharedPreferences;
    private String currentLanguage;
    private ArrayList<City> mCities;
    private Button mButtonPlayGame;
    private Button mButtonCityList;
    private Button mButtonSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSharedPreferences = getSharedPreferences("settings", Context.MODE_PRIVATE);
        setTheme(mSharedPreferences.getInt("theme", R.style.AppThemeLight));
        setLocale(mSharedPreferences.getString("language", SystemInfo.SYSTEM_LOCALE));
        setContentView(R.layout.activity_menu_main);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        currentLanguage = mSharedPreferences.getString("language", SystemInfo.SYSTEM_LOCALE);
        getSupportActionBar().setTitle(R.string.app_name);

        // Do something if it's the first time launching the app (maybe splash screen for image caching)
        // Note: Splash screen should probably execute every time the app is launched. It will just
        // take long on first time and will be instant on future launches.
        if (mSharedPreferences.getBoolean("first_launch", true)) {
            // Enter statements to do when first time launching here
            editor.putBoolean("first_launch", false);
            editor.commit();
        }

        mCities = new ArrayList<>();
        parseJsonAndCreateCityObjects();
        cacheImagesAndLoadToMemory();

        mButtonPlayGame = (Button) findViewById(R.id.button_start_game);
        mButtonCityList = (Button) findViewById(R.id.button_city_list);
        mButtonSettings = (Button) findViewById(R.id.button_settings);

        mButtonPlayGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPlayGame = new Intent(MainMenuActivity.this, QuizActivity.class);
                intentPlayGame.putExtra("cityList", mCities);
                startActivity(intentPlayGame);
            }
        });

        mButtonCityList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCityList = new Intent(MainMenuActivity.this, CityListActivity.class);
                intentCityList.putExtra("cityList", mCities);
                startActivity(intentCityList);
            }
        });

        mButtonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSettings = new Intent(MainMenuActivity.this, SettingsActivity.class);
                startActivity(intentSettings);
            }
        });

    }

    /**
     * Loads the theme if it was changed. Updates the city objects with city and country names in
     * its appropriate language if the language was changed.
     */
    @Override
    public void onResume() {
        super.onResume();
        loadTheme();
        updateCitiesWithCurrentLanguage();
    }

    /**
     * Creates a hamburger-style menu with options to view the app source code, make a suggestion,
     * report an error, or rate the app.
     *
     * @param menu The menu to be created.
     * @return Status of the operation.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Starts an intent based on the menu item selected. Source code intent opens the developer's
     * Github profile on a browser app. Suggestion intent and report intent open a new email message
     * with a predefined subject. Rate intent opens the app's url in the Google Play store.
     *
     * @param item The menu item being selected.
     * @return Status of the operation.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case (R.id.menu_item_source_code):
                Intent sourceCodeIntent = new Intent(Intent.ACTION_VIEW);
                sourceCodeIntent.setData(Uri.parse(DEVELOPER_GITHUB_URL));
                Intent sourceCodeChooser = Intent.createChooser(sourceCodeIntent, getString(R.string.launch_browser));
                startActivity(sourceCodeChooser);
                break;
            case (R.id.menu_item_suggest):
                Intent emailSuggestionIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:"
                        + DEVELOPER_EMAIL));
                emailSuggestionIntent.putExtra("subject", getResources().getString(R.string.app_name)
                        + " - " + getResources().getString(R.string.suggestion));
                Intent emailSuggestionChooser = Intent.createChooser(emailSuggestionIntent,
                        getString(R.string.launch_email));
                startActivity(emailSuggestionChooser);
                break;
            case (R.id.menu_item_report):
                Intent emailIssueIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:"
                        + DEVELOPER_EMAIL));
                emailIssueIntent.putExtra("subject", getResources().getString(R.string.app_name)
                        + " - " + getResources().getString(R.string.report_error));
                Intent emailIssueChooser = Intent.createChooser(emailIssueIntent,
                        getString(R.string.launch_email));
                startActivity(emailIssueChooser);
                break;
            case (R.id.menu_item_rate):
                Intent rateAppIntent = new Intent(Intent.ACTION_VIEW);
                rateAppIntent.setData(Uri.parse("market://details?id=" + getPackageName()));
                try {
                    startActivity(rateAppIntent);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(this, R.string.google_play_error, Toast.LENGTH_LONG).show();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Sets the locale to a new language.
     *
     * @param lang the new language to set the app to.
     */
    public void setLocale(String lang) {
        Locale myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
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
            InputStream inputStream = getAssets().open("cities.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
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

    /**
     * Checks if either the theme or language has been changed. If either has changed the current
     * activity restarts so the new theme/language can be applied to all views.
     */
    private void loadTheme() {
        if (mSharedPreferences.getInt("theme", R.style.AppThemeLight) != getThemeId()
                || !mSharedPreferences.getString("language", SystemInfo.SYSTEM_LOCALE)
                .equals(currentLanguage)) {
            this.finish();
            final Intent intent = this.getIntent();
            this.startActivity(intent);
        }
    }

    /**
     * Updates the city and country names of the model objects upon the language change with the
     * names in that language.
     */
    private void updateCitiesWithCurrentLanguage() {
        for (City city : mCities) {
            city.setCityNameInCurrentLanguage(ResourceByNameRetriever
                    .getStringResourceByName(city.getCityName(), this));
            city.setCountryNameInCurrentLanguage(ResourceByNameRetriever
                    .getStringResourceByName(city.getCountryName(), this));
        }
    }
}
