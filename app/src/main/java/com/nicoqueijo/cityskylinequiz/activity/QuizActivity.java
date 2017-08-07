package com.nicoqueijo.cityskylinequiz.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nicoqueijo.cityskylinequiz.R;
import com.nicoqueijo.cityskylinequiz.model.City;

import java.util.ArrayList;

public class QuizActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    ArrayList<City> mCities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences("settings", Context.MODE_PRIVATE);
        setTheme(sharedPreferences.getInt("theme", R.style.AppThemeLight));
        setContentView(R.layout.activity_quiz);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intentPlayGame = getIntent();
        mCities = (ArrayList<City>) intentPlayGame.getSerializableExtra("cityList");
    }
}
