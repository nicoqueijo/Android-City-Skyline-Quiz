package com.nicoqueijo.cityskylinequiz.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.nicoqueijo.cityskylinequiz.R;
import com.nicoqueijo.cityskylinequiz.fragments.QuizFragment;
import com.nicoqueijo.cityskylinequiz.models.City;
import com.nicoqueijo.cityskylinequiz.models.Question;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * This activity hosts the fragment that will run the quiz game.
 */
public class QuizGameActivity extends AppCompatActivity {

    private final String LOG_V_QUIZ_GAME_ACTIVITY = "QuizGameActivity";

    public static final int CORRECT_CHOICE = 0;
    public static final int CHOICE_1 = 1;
    public static final int CHOICE_2 = 2;
    public static final int CHOICE_3 = 3;
    public static final int CHOICE_4 = 4;

    public static final int ALL_QUESTIONS = MainMenuActivity.cities.size();
    public static final int TEN_QUESTIONS = 10;
    public static final int TWENTY_QUESTIONS = 20;
    public static final int FIFTY_QUESTIONS = 50;

    public static Queue<Question> questions;

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
        mGroupPosition = intentQuizGame.getIntExtra("parentMode", QuizMenuActivity.PARENT_MODE_UNTIMED);
        mChildPosition = intentQuizGame.getIntExtra("childMode", QuizMenuActivity.CHILD_MODE_QUESTIONS_10);

        mCities = (ArrayList<City>) MainMenuActivity.cities;
        Collections.shuffle(mCities);
        questions = new LinkedList<>();
        generateQuestions();
        Picasso.with(QuizGameActivity.this).load(questions.peek().getCorrectChoice().getImageUrl())
                .fetch();

        // THIS MIGHT BE USELESS
        Bundle bundle = new Bundle();
        bundle.putSerializable("questions", (Serializable) questions);
        bundle.putInt("group", mGroupPosition);
        bundle.putInt("child", mChildPosition);

        FragmentManager mFragmentManager = getSupportFragmentManager();
        FragmentTransaction mTransaction = mFragmentManager.beginTransaction();
        mTransaction.add(R.id.quiz_fragment_container, new QuizFragment(), "quizFragment");
        mTransaction.commit();

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

    /**
     * Generates the questions for the quiz game. The Question objects are stored in a queue data
     * structure. Each Question is in the following format: It has five fields of City objects which
     * we'll call choices. There are four choices fields which are random cities from the shuffled
     * city list and a correct choice field which is a random city from the four choices.
     * <p>
     * The algorithm chooses the first city of the shuffled list as the correct choice and then
     * three random, non-repeating cities as the other choices. When a question with its choices has
     * been created it enqueues it to the Question queue. It does this n times where n is the number
     * of cities.
     * <p>
     * Credit to mav3n and Miroslav Lazovich who helped me implement this algorithm:
     * https://stackoverflow.com/a/46104762/5906793
     */
    private void generateQuestions() {
        Log.v("callorder", "generateQuestions() called");
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
            questions.add(new Question(choices));
            choices.clear();
            exclusionList.add(city);
        }
    }
}
