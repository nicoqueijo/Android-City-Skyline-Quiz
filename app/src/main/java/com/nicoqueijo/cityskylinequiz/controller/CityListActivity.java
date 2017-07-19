package com.nicoqueijo.cityskylinequiz.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.nicoqueijo.cityskylinequiz.R;
import com.nicoqueijo.cityskylinequiz.model.City;

import java.util.ArrayList;

public class CityListActivity extends AppCompatActivity {

    ArrayList<City> mCities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_list);

        Intent intentCityList = getIntent();
        mCities = (ArrayList<City>) intentCityList.getSerializableExtra("cityList");

    }

    /**
     * Retrieves String resources using a String instead of an int.
     *
     * @param name name of the String resource
     * @return the String resource
     */
    private String getStringResourceByName(String name) {
        String packageName = getPackageName();
        int resId = getResources().getIdentifier(name, "string", packageName);
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
