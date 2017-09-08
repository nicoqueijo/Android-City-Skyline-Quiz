package com.nicoqueijo.cityskylinequiz.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.nicoqueijo.cityskylinequiz.R;
import com.nicoqueijo.cityskylinequiz.model.City;
import com.nicoqueijo.cityskylinequiz.model.Question;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class QuizGameActivity extends AppCompatActivity {

    public static final int CORRECT_CHOICE = 0;
    public static final int CHOICE_1 = 1;
    public static final int CHOICE_2 = 2;
    public static final int CHOICE_3 = 3;
    public static final int CHOICE_4 = 4;

    public static final int ALL_QUESTIONS = MainMenuActivity.cities.size();
    public static final int TEN_QUESTIONS = 10;
    public static final int TWENTY_QUESTIONS = 20;
    public static final int FIFTY_QUESTIONS = 50;

    private ActionBar mActionBar;
    private SharedPreferences mSharedPreferences;
    private ArrayList<City> mCities;
    private Queue<Question> mQuestions;
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
        mGroupPosition = intentQuizGame.getIntExtra("parentMode", QuizMenuActivity.PARENT_MODE_UNTIMED);
        mChildPosition = intentQuizGame.getIntExtra("childMode", QuizMenuActivity.CHILD_MODE_QUESTIONS_10);

        mCities = (ArrayList<City>) MainMenuActivity.cities;
        Collections.shuffle(mCities);
        mQuestions = new LinkedList<>();

        createQuestions();

        // REMOVE THIS LATER
        for (Question question : mQuestions) {
            Log.v("question:", question.getCorrectChoice().getCityName()
                    + " " + question.getChoice1().getCityName()
                    + " " + question.getChoice2().getCityName()
                    + " " + question.getChoice3().getCityName()
                    + " " + question.getChoice4().getCityName());
        }

        switch (mGroupPosition) {
            case (QuizMenuActivity.PARENT_MODE_TIMED):
                switch (mChildPosition) {
                    case (QuizMenuActivity.CHILD_MODE_SECONDS_30):
                        // Keep dequeueing questions while there is time remaining.
                        break;
                    case (QuizMenuActivity.CHILD_MODE_SECONDS_60):

                        // Keep dequeueing questions while there is time remaining.
                        break;
                    case (QuizMenuActivity.CHILD_MODE_SECONDS_120):

                        // Keep dequeueing questions while there is time remaining.
                        break;
                }
                break;
            case (QuizMenuActivity.PARENT_MODE_UNTIMED):
                switch (mChildPosition) {
                    case (QuizMenuActivity.CHILD_MODE_QUESTIONS_10):
                        // Keep dequeueing questions while the queue is not empty.
                        break;
                    case (QuizMenuActivity.CHILD_MODE_QUESTIONS_20):
                        // Keep dequeueing questions while the queue is not empty.
                        break;
                    case (QuizMenuActivity.CHILD_MODE_QUESTIONS_50):
                        // Keep dequeueing questions while the queue is not empty.
                        break;
                }
                break;
            case (QuizMenuActivity.PARENT_MODE_EVERY_CITY):
                switch (mChildPosition) {
                    case (QuizMenuActivity.CHILD_MODE_NO_FAULTS):
                        // Keep dequeueing questions while the queue is not empty.
                        // End the game immediately upon a wrong answer.
                        break;
                    case (QuizMenuActivity.CHILD_MODE_FAULTS_ALLOWED):
                        // Keep dequeueing questions while the queue is not empty.
                        break;
                }
                break;
        }
    }

    private void createQuestions() {
        List<City> choices = new ArrayList<>();
        List<City> exclusionList = new ArrayList<>();
        exclusionList.addAll(mCities);
        for (City city : mCities) {
            exclusionList.remove(city);
            Collections.shuffle(exclusionList);
            choices.add(city);
            choices.add(city);
            choices.addAll(exclusionList.subList(0, 3));
            Collections.shuffle(choices.subList(1, 5));
            mQuestions.add(new Question(choices));
            choices.clear();
            exclusionList.add(city);
        }
    }
}
