package com.nicoqueijo.cityskylinequiz.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.nicoqueijo.cityskylinequiz.R;
import com.nicoqueijo.cityskylinequiz.model.City;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class QuizGameActivity extends AppCompatActivity {

    private ActionBar mActionBar;
    private SharedPreferences mSharedPreferences;
    private ArrayList<City> mCities;
    private Queue<City> mQuestions;
    private int mGroupPosition;
    private int mChildPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v("method call", "onCreate");
        mSharedPreferences = getSharedPreferences("settings", Context.MODE_PRIVATE);
        setTheme(mSharedPreferences.getInt("theme", R.style.AppThemeLight));
        setContentView(R.layout.activity_quiz_game);
        mActionBar = getSupportActionBar();
        mActionBar.setDisplayShowHomeEnabled(true);
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setIcon(R.drawable.ic_light_game);
        mActionBar.setTitle(R.string.actionbar_play_game);

        Intent intentQuizGame = getIntent();
        mCities = (ArrayList<City>) MainMenuActivity.cities;
        Collections.shuffle(mCities);
        mQuestions = new LinkedList<>();
        mGroupPosition = intentQuizGame.getIntExtra("parentMode", QuizMenuActivity.UNTIMED_MODE);
        mChildPosition = intentQuizGame.getIntExtra("childMode", QuizMenuActivity.QUESTIONS_10);

        Toast.makeText(QuizGameActivity.this, mGroupPosition + " : " + mChildPosition, Toast.LENGTH_SHORT).show();

        switch (mGroupPosition) {
            case (QuizMenuActivity.TIMED_MODE):
                switch (mChildPosition) {
                    case (QuizMenuActivity.SECONDS_30):
                        // Keep dequeueing questions while there is time remaining.
                        break;
                    case (QuizMenuActivity.SECONDS_60):
                        // Keep dequeueing questions while there is time remaining.
                        break;
                    case (QuizMenuActivity.SECONDS_120):
                        // Keep dequeueing questions while there is time remaining.
                        break;
                }
                break;
            case (QuizMenuActivity.UNTIMED_MODE):
                switch (mChildPosition) {
                    case (QuizMenuActivity.QUESTIONS_10):
                        // Keep dequeueing questions while the queue is not empty.
                        break;
                    case (QuizMenuActivity.QUESTIONS_20):
                        // Keep dequeueing questions while the queue is not empty.
                        break;
                    case (QuizMenuActivity.QUESTIONS_50):
                        // Keep dequeueing questions while the queue is not empty.
                        break;
                }
                break;
            case (QuizMenuActivity.EVERY_CITY_MODE):
                switch (mChildPosition) {
                    case (QuizMenuActivity.NO_FAULTS):
                        // Keep dequeueing questions while the queue is not empty.
                        // End the game immediately upon a wrong answer.
                        break;
                    case (QuizMenuActivity.FAULTS_ALLOWED):
                        // Keep dequeueing questions while the queue is not empty.
                        break;
                }
                break;
        }
    }
}
