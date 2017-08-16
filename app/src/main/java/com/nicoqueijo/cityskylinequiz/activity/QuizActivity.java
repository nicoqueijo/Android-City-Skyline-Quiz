package com.nicoqueijo.cityskylinequiz.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nicoqueijo.cityskylinequiz.R;
import com.nicoqueijo.cityskylinequiz.model.City;

import java.util.ArrayList;

public class QuizActivity extends AppCompatActivity {

    private ActionBar mActionBar;
    private SharedPreferences mSharedPreferences;
    private ArrayList<City> mCities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSharedPreferences = getSharedPreferences("settings", Context.MODE_PRIVATE);
        setTheme(mSharedPreferences.getInt("theme", R.style.AppThemeLight));
        setContentView(R.layout.activity_quiz);
        mActionBar = getSupportActionBar();
        mActionBar.setDisplayShowHomeEnabled(true);
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setIcon(R.drawable.ic_game);
        mActionBar.setTitle(R.string.play_game);

        Intent intentPlayGame = getIntent();
        mCities = (ArrayList<City>) intentPlayGame.getSerializableExtra("cityList");
    }
}
