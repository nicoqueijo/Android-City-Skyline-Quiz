package com.nicoqueijo.cityskylinequiz.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.nicoqueijo.cityskylinequiz.R;
import com.nicoqueijo.cityskylinequiz.fragments.QuizFragmentEveryCity;
import com.nicoqueijo.cityskylinequiz.fragments.QuizFragmentTimed;
import com.nicoqueijo.cityskylinequiz.fragments.QuizFragmentUntimed;
import com.nicoqueijo.cityskylinequiz.models.City;
import com.nicoqueijo.cityskylinequiz.models.Question;
import com.nicoqueijo.cityskylinequiz.models.QuestionReport;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * This activity hosts the fragment that will run the quiz game.
 */
public class QuizGameActivity extends AppCompatActivity {

    public static final int OFF_BY_ONE = 1;

    public static final int CORRECT_CHOICE = 0;
    public static final int CHOICE_1 = 1;
    public static final int CHOICE_2 = 2;
    public static final int CHOICE_3 = 3;
    public static final int CHOICE_4 = 4;

    public static final int TEN_QUESTIONS_MODE = 0;
    public static final int TWENTY_QUESTIONS_MODE = 1;
    public static final int FIFTY_QUESTIONS_MODE = 2;

    public static final int THIRTY_SECONDS_MODE = 0;
    public static final int SIXTY_SECONDS_MODE = 1;
    public static final int ONE_HUNDRED_TWENTY_SECONDS_MODE = 2;

    public static Queue<Question> questions;
    public static List<QuestionReport> questionReports;
    private FragmentManager mFragmentManager = getSupportFragmentManager();
    private FragmentTransaction mTransaction = mFragmentManager.beginTransaction();
    private Fragment mQuizFragment = null;

    private ActionBar mActionBar;
    private SharedPreferences mSharedPreferences;
    private ArrayList<City> mCities;
    private int mGroupPosition;
    private int mChildPosition;
    private CountDownTimer mCountDownTimer;
    private int mCount = 3;
    private TextView mCountdown;

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
        mCountdown = (TextView) findViewById(R.id.countdown);

        Intent intentQuizGame = getIntent();
        mGroupPosition = intentQuizGame.getIntExtra("parentMode", QuizMenuActivity.PARENT_MODE_UNTIMED);
        mChildPosition = intentQuizGame.getIntExtra("childMode", QuizMenuActivity.CHILD_MODE_QUESTIONS_10);

        mCities = (ArrayList<City>) MainMenuActivity.cities;
        Collections.shuffle(mCities);
        questions = new LinkedList<>();
        generateQuestions();
        questionReports = new ArrayList<>();

        Picasso.with(QuizGameActivity.this).load(questions.peek().getCorrectChoice().getImageUrl())
                .fetch();

        Bundle bundle = new Bundle();
        bundle.putInt("child", mChildPosition);

        switch (mGroupPosition) {
            case (QuizMenuActivity.PARENT_MODE_TIMED):
                mQuizFragment = new QuizFragmentTimed();
                break;
            case (QuizMenuActivity.PARENT_MODE_UNTIMED):
                mQuizFragment = new QuizFragmentUntimed();
                break;
            case (QuizMenuActivity.PARENT_MODE_EVERY_CITY):
                mQuizFragment = new QuizFragmentEveryCity();
                break;
        }

        mQuizFragment.setArguments(bundle);

        final long MARGIN_OF_ERROR = 200;
        long millisInFuture = (700 * 3) + MARGIN_OF_ERROR;
        long countDownInterval = 700;
        mCountDownTimer = new CountDownTimer(millisInFuture, countDownInterval) {
            /**
             * Callback fired on regular interval.
             * @param millisUntilFinished The amount of time until finished.
             */
            @Override
            public void onTick(long millisUntilFinished) {
                mCountdown.setText(String.valueOf(mCount));
                mCount--;
            }

            /**
             * Callback fired when the time is up. Once the three second countdown splash screen is
             * over the quiz fragment is added.
             */
            @Override
            public void onFinish() {
                mCountdown.setVisibility(View.GONE);
                mTransaction.add(R.id.quiz_fragment_container, mQuizFragment, "quizFragment");
                mTransaction.commitAllowingStateLoss();
            }
        };
        mCountDownTimer.start();
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

    /**
     * Perform any final cleanup before an activity is destroyed.
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCountDownTimer.cancel();
    }
}
