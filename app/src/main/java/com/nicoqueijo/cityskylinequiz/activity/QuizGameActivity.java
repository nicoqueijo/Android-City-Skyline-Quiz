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

public class QuizGameActivity extends AppCompatActivity {

    private ActionBar mActionBar;
    private SharedPreferences mSharedPreferences;
    private ArrayList<City> mCities;
    private int mGroupPosition;
    private int mChildPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSharedPreferences = getSharedPreferences("settings", Context.MODE_PRIVATE);
        setTheme(mSharedPreferences.getInt("theme", R.style.AppThemeLight));
        setContentView(R.layout.activity_quiz_game);
        mActionBar = getSupportActionBar();
        mActionBar.setDisplayShowHomeEnabled(true);
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setIcon(R.drawable.ic_light_game);
        mActionBar.setTitle(R.string.actionbar_play_game);

        Intent intentQuizGame = getIntent();
        mCities = (ArrayList<City>) intentQuizGame.getSerializableExtra("cityList");
        mGroupPosition = intentQuizGame.getIntExtra("parentMode", QuizMenuActivity.UNTIMED_MODE);
        mChildPosition = intentQuizGame.getIntExtra("childMode", QuizMenuActivity.QUESTIONS_10);


        switch (mGroupPosition) {
            case (QuizMenuActivity.TIMED_MODE):
                switch (mChildPosition) {
                    case (QuizMenuActivity.SECONDS_30):
                        // Start quiz game intent appropriate for unlimited questions within 30 seconds.
                        // Keep dequeueing questions while there is time remaining.
                        // Don't forget to pass the city list!
                        break;
                    case (QuizMenuActivity.SECONDS_60):
                        // Start quiz game intent appropriate for unlimited questions within 60 seconds.
                        // Keep dequeueing questions while there is time remaining.
                        // Don't forget to pass the city list!
                        break;
                    case (QuizMenuActivity.SECONDS_120):
                        // Start quiz game intent appropriate for unlimited questions within 120 seconds.
                        // Keep dequeueing questions while there is time remaining.
                        // Don't forget to pass the city list!
                        break;
                }
                break;
            case (QuizMenuActivity.UNTIMED_MODE):
                switch (mChildPosition) {
                    case (QuizMenuActivity.QUESTIONS_10):
                        // Start quiz game intent appropriate for 10 questions.
                        // Keep dequeueing questions while the queue is not empty.
                        // Don't forget to pass the city list!
                        break;
                    case (QuizMenuActivity.QUESTIONS_20):
                        // Start quiz game intent appropriate for 20 questions.
                        // Keep dequeueing questions while the queue is not empty.
                        // Don't forget to pass the city list!
                        break;
                    case (QuizMenuActivity.QUESTIONS_50):
                        // Start quiz game intent appropriate for 50 questions.
                        // Keep dequeueing questions while the queue is not empty.
                        // Don't forget to pass the city list!
                        break;
                }
                break;
            case (QuizMenuActivity.EVERY_CITY_MODE):
                switch (mChildPosition) {
                    case (QuizMenuActivity.NO_FAULTS):
                        // Start quiz game intent appropriate for unlimited questions without faults.
                        // Keep dequeueing questions while the queue is not empty.
                        // End the game immediately upon a wrong answer.
                        // Don't forget to pass the city list!
                        break;
                    case (QuizMenuActivity.FAULTS_ALLOWED):
                        // Start quiz game intent appropriate for unlimited questions with faults allowed.
                        // Keep dequeueing questions while the queue is not empty.
                        // Don't forget to pass the city list!
                        break;
                }
                break;
        }
    }
}
