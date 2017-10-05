package com.nicoqueijo.cityskylinequiz.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nicoqueijo.cityskylinequiz.R;
import com.nicoqueijo.cityskylinequiz.activities.QuizGameActivity;
import com.nicoqueijo.cityskylinequiz.activities.QuizMenuActivity;

public class QuizScoreFragment extends Fragment {

    public static final float DEFAULT_HIGHSCORE = 0.0f;
    public static final double ATTEMPT_ONE_MULTIPLIER = 1.0;
    public static final double ATTEMPT_TWO_MULTIPLIER = 0.5;
    public static final double ATTEMPT_THREE_MULTIPLIER = 0.25;
    public static final double ATTEMPT_FOUR_MULTIPLIER = 0.0;

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private boolean newHighscoreAcquired = false;

    private LinearLayout mNewRecordLabel;
    private TextView mAttemptOneCorrect;
    private TextView mAttemptTwoCorrect;
    private TextView mAttemptThreeCorrect;
    private TextView mAttemptFourCorrect;
    private TextView mAttemptOneScore;
    private TextView mAttemptTwoScore;
    private TextView mAttemptThreeScore;
    private TextView mAttemptFourScore;
    private TextView mTotalScore;
    private Button mGameMenuButton;
    private Button mViewReportButton;

    private int mCorrectAnswersOnAttemptOne = QuizGameActivity.correctAnswersOnAttemptOne;
    private int mCorrectAnswersOnAttemptTwo = QuizGameActivity.correctAnswersOnAttemptTwo;
    private int mCorrectAnswersOnAttemptThree = QuizGameActivity.correctAnswersOnAttemptThree;
    private int mCorrectAnswersOnAttemptFour = QuizGameActivity.correctAnswersOnAttemptFour;

    private double mScoreAttemptOne = mCorrectAnswersOnAttemptOne * ATTEMPT_ONE_MULTIPLIER;
    private double mScoreAttemptTwo = mCorrectAnswersOnAttemptTwo * ATTEMPT_TWO_MULTIPLIER;
    private double mScoreAttemptThree = mCorrectAnswersOnAttemptThree * ATTEMPT_THREE_MULTIPLIER;
    private double mScoreAttemptFour = mCorrectAnswersOnAttemptFour * ATTEMPT_FOUR_MULTIPLIER;
    private float mFinalScore = (float) (mScoreAttemptOne + mScoreAttemptTwo + mScoreAttemptThree +
            mScoreAttemptFour);

    /**
     * Required empty public constructor
     */
    public QuizScoreFragment() {
    }

    /**
     * Called to do initial creation of a fragment.
     *
     * @param savedInstanceState If the fragment is being re-created from
     *                           a previous saved state, this is the state.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Called to have the fragment instantiate its user interface view.
     *
     * @param inflater           The LayoutInflater object that can be used to inflate
     *                           any views in the fragment,
     * @param container          If non-null, this is the parent view that the fragment's
     *                           UI should be attached to.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     *                           from a previous saved state as given here.
     * @return Return the View for the fragment's UI, or null.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_quiz_score, container, false);
        mNewRecordLabel = (LinearLayout) view.findViewById(R.id.new_record_label);
        mAttemptOneCorrect = (TextView) view.findViewById(R.id.attempt_one_correct);
        mAttemptTwoCorrect = (TextView) view.findViewById(R.id.attempt_two_correct);
        mAttemptThreeCorrect = (TextView) view.findViewById(R.id.attempt_three_correct);
        mAttemptFourCorrect = (TextView) view.findViewById(R.id.attempt_four_correct);
        mAttemptOneScore = (TextView) view.findViewById(R.id.attempt_one_score);
        mAttemptTwoScore = (TextView) view.findViewById(R.id.attempt_two_score);
        mAttemptThreeScore = (TextView) view.findViewById(R.id.attempt_three_score);
        mAttemptFourScore = (TextView) view.findViewById(R.id.attempt_four_score);
        mTotalScore = (TextView) view.findViewById(R.id.total_score);
        mGameMenuButton = (Button) view.findViewById(R.id.game_menu_button);
        mViewReportButton = (Button) view.findViewById(R.id.view_report_button);
        mSharedPreferences = getActivity().getSharedPreferences("settings", Context.MODE_PRIVATE);

        // Used to reshow the new record label if the user chooses to see the report and then
        // returns to this fragment.
        if (newHighscoreAcquired) {
            mNewRecordLabel.setVisibility(View.VISIBLE);
        }

        float currentHighscore;
        switch (QuizGameActivity.groupPosition) {
            case QuizMenuActivity.PARENT_MODE_TIMED:
                switch (QuizGameActivity.childPosition) {
                    case QuizMenuActivity.CHILD_MODE_SECONDS_30:
                        currentHighscore = mSharedPreferences.getFloat("highscore_30_seconds"
                                , DEFAULT_HIGHSCORE);
                        checkNewHighscore(mFinalScore, currentHighscore,
                                "highscore_30_seconds");
                        break;
                    case QuizMenuActivity.CHILD_MODE_SECONDS_60:
                        currentHighscore = mSharedPreferences.getFloat("highscore_60_seconds"
                                , DEFAULT_HIGHSCORE);
                        checkNewHighscore(mFinalScore, currentHighscore,
                                "highscore_60_seconds");
                        break;
                    case QuizMenuActivity.CHILD_MODE_SECONDS_120:
                        currentHighscore = mSharedPreferences.getFloat("highscore_120_seconds"
                                , DEFAULT_HIGHSCORE);
                        checkNewHighscore(mFinalScore, currentHighscore,
                                "highscore_120_seconds");
                        break;
                }
                break;
            case QuizMenuActivity.PARENT_MODE_UNTIMED:
                switch (QuizGameActivity.childPosition) {
                    case QuizMenuActivity.CHILD_MODE_QUESTIONS_10:
                        currentHighscore = mSharedPreferences.getFloat("highscore_10_questions"
                                , DEFAULT_HIGHSCORE);
                        checkNewHighscore(mFinalScore, currentHighscore,
                                "highscore_10_questions");
                        break;
                    case QuizMenuActivity.CHILD_MODE_QUESTIONS_20:
                        currentHighscore = mSharedPreferences.getFloat("highscore_20_questions"
                                , DEFAULT_HIGHSCORE);
                        checkNewHighscore(mFinalScore, currentHighscore,
                                "highscore_20_questions");
                        break;
                    case QuizMenuActivity.CHILD_MODE_QUESTIONS_50:
                        currentHighscore = mSharedPreferences.getFloat("highscore_50_questions"
                                , DEFAULT_HIGHSCORE);
                        checkNewHighscore(mFinalScore, currentHighscore,
                                "highscore_50_questions");
                        break;
                }
                break;
            case QuizMenuActivity.PARENT_MODE_EVERY_CITY:
                switch (QuizGameActivity.childPosition) {
                    case QuizMenuActivity.CHILD_MODE_EVERY_CITY_NO_FAULTS:
                        currentHighscore = mSharedPreferences.getFloat
                                ("highscore_every_city_no_faults", DEFAULT_HIGHSCORE);
                        checkNewHighscore(mFinalScore, currentHighscore,
                                "highscore_every_city_no_faults");
                        break;
                    case QuizMenuActivity.CHILD_MODE_EVERY_CITY_FAULTS_ALLOWED:
                        currentHighscore = mSharedPreferences.getFloat
                                ("highscore_every_city_faults_allowed", DEFAULT_HIGHSCORE);
                        checkNewHighscore(mFinalScore, currentHighscore,
                                "highscore_every_city_faults_allowed");
                        break;
                }
                break;
        }

        mAttemptOneCorrect.setText(String.valueOf(mCorrectAnswersOnAttemptOne));
        mAttemptOneCorrect.setText(String.valueOf(mCorrectAnswersOnAttemptOne));
        mAttemptTwoCorrect.setText(String.valueOf(mCorrectAnswersOnAttemptTwo));
        mAttemptThreeCorrect.setText(String.valueOf(mCorrectAnswersOnAttemptThree));
        mAttemptFourCorrect.setText(String.valueOf(mCorrectAnswersOnAttemptFour));

        mAttemptOneScore.setText(String.format("%3.2f", mScoreAttemptOne));
        mAttemptTwoScore.setText(String.format("%3.2f", mScoreAttemptTwo));
        mAttemptThreeScore.setText(String.format("%3.2f", mScoreAttemptThree));
        mAttemptFourScore.setText(String.format("%3.2f", mScoreAttemptFour));
        mTotalScore.setText(String.format("%3.2f", mFinalScore));

        mGameMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        mViewReportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().addToBackStack
                        ("quizScoreFragment").replace(R.id.quiz_fragment_container,
                        new QuizReportFragment()).commit();
            }
        });
        return view;
    }

    /**
     * If the user achieved a score in this game that is higher than the current highscore, that
     * score replaces the current highscore. Also the label notifying the user that a new
     * highscore was achieved gets displayed.
     *
     * @param finalScore           score of this game
     * @param currentHighscore     highscore stored in sharedPreferences
     * @param sharedPreferencesKey the key of the game mode that belongs to that highscore
     */
    private void checkNewHighscore(float finalScore, float currentHighscore,
                                   String sharedPreferencesKey) {
        mEditor = mSharedPreferences.edit();
        if (finalScore > currentHighscore) {
            mNewRecordLabel.setVisibility(View.VISIBLE);
            mEditor.putFloat(sharedPreferencesKey, mFinalScore);
            mEditor.commit();
            newHighscoreAcquired = true;
        }
    }
}
