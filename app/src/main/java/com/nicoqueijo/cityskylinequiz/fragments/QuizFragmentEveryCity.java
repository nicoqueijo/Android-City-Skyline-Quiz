package com.nicoqueijo.cityskylinequiz.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nicoqueijo.cityskylinequiz.R;
import com.nicoqueijo.cityskylinequiz.activities.QuizGameActivity;
import com.nicoqueijo.cityskylinequiz.helpers.CornerRounder;
import com.nicoqueijo.cityskylinequiz.helpers.ResourceByNameRetriever;
import com.nicoqueijo.cityskylinequiz.models.City;
import com.nicoqueijo.cityskylinequiz.models.Question;
import com.nicoqueijo.cityskylinequiz.models.QuestionReport;
import com.squareup.picasso.Picasso;

public class QuizFragmentEveryCity extends Fragment implements View.OnClickListener {

    private final QuizFragmentEveryCity THIS_FRAGMENT = this;

    private final int PROGRESS_BAR_UNITS = QuizGameActivity.questions.size();
    private boolean mNoFaults;
    private Question mCurrentQuestion;
    private int mQuestionCounter = 0;
    private int mAttemptNumber = 0;
    private Handler mHandler = new Handler();

    private ImageView mCityImage;
    private LinearLayout mContainerChoice1;
    private LinearLayout mContainerChoice2;
    private LinearLayout mContainerChoice3;
    private LinearLayout mContainerChoice4;
    private ImageView mFlagChoice1;
    private ImageView mFlagChoice2;
    private ImageView mFlagChoice3;
    private ImageView mFlagChoice4;
    private TextView mCityNameChoice1;
    private TextView mCityNameChoice2;
    private TextView mCityNameChoice3;
    private TextView mCityNameChoice4;
    private TextView mFeedback;
    private ProgressBar mProgressBar;

    /**
     * Required empty public constructor
     */
    public QuizFragmentEveryCity() {
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
        // ready-up the first question image for fast loading
        Picasso.with(getActivity()).load(QuizGameActivity.questions.peek().getCorrectChoice()
                .getImageUrl()).fetch();
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
        View view = inflater.inflate(R.layout.fragment_quiz, container, false);

        int mChildPosition = getArguments().getInt("child");
        switch (mChildPosition) {
            case 0:
                mNoFaults = true;
                break;
            case 1:
                mNoFaults = false;
                break;
        }

        mCityImage = (ImageView) view.findViewById(R.id.city_image);
        mContainerChoice1 = (LinearLayout) view.findViewById(R.id.answer_choice_one);
        mContainerChoice2 = (LinearLayout) view.findViewById(R.id.answer_choice_two);
        mContainerChoice3 = (LinearLayout) view.findViewById(R.id.answer_choice_three);
        mContainerChoice4 = (LinearLayout) view.findViewById(R.id.answer_choice_four);
        mFlagChoice1 = (ImageView) view.findViewById(R.id.flag_choice_one);
        mFlagChoice2 = (ImageView) view.findViewById(R.id.flag_choice_two);
        mFlagChoice3 = (ImageView) view.findViewById(R.id.flag_choice_three);
        mFlagChoice4 = (ImageView) view.findViewById(R.id.flag_choice_four);
        mCityNameChoice1 = (TextView) view.findViewById(R.id.city_name_choice_one);
        mCityNameChoice2 = (TextView) view.findViewById(R.id.city_name_choice_two);
        mCityNameChoice3 = (TextView) view.findViewById(R.id.city_name_choice_three);
        mCityNameChoice4 = (TextView) view.findViewById(R.id.city_name_choice_four);
        mFeedback = (TextView) view.findViewById(R.id.feedback);
        mProgressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
        mProgressBar.setMax(PROGRESS_BAR_UNITS);

        mContainerChoice1.setOnClickListener(this);
        mContainerChoice2.setOnClickListener(this);
        mContainerChoice3.setOnClickListener(this);
        mContainerChoice4.setOnClickListener(this);

        CornerRounder.roundImageCorners(mCityImage, mFlagChoice1, mFlagChoice2, mFlagChoice3,
                mFlagChoice4);
        loadNextQuestion();

        return view;
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        // gets the choice that was clicked
        LinearLayout choicePress = (LinearLayout) v;

        // load the image of the next question in cache
        if (!(QuizGameActivity.questions.isEmpty())) {
            Picasso.with(getActivity()).load(QuizGameActivity.questions.peek().getCorrectChoice()
                    .getImageUrl()).fetch();
        }

        int markNumber = 0;
        City guess = null;
        if (choicePress == mContainerChoice1) {
            guess = mCurrentQuestion.getChoice1();
            markNumber = QuizGameActivity.CHOICE_1;
        } else if (choicePress == mContainerChoice2) {
            guess = mCurrentQuestion.getChoice2();
            markNumber = QuizGameActivity.CHOICE_2;
        } else if (choicePress == mContainerChoice3) {
            guess = mCurrentQuestion.getChoice3();
            markNumber = QuizGameActivity.CHOICE_3;
        } else if (choicePress == mContainerChoice4) {
            guess = mCurrentQuestion.getChoice4();
            markNumber = QuizGameActivity.CHOICE_4;
        }

        if (guess.getCityName().equals(mCurrentQuestion.getCorrectChoice().getCityName())) {
            QuizGameActivity.questionReports.get(mQuestionCounter - QuizGameActivity.OFF_BY_ONE)
                    .setCorrectMark(markNumber);
            mAttemptNumber = 0;
            mContainerChoice1.setEnabled(false);
            mContainerChoice2.setEnabled(false);
            mContainerChoice3.setEnabled(false);
            mContainerChoice4.setEnabled(false);
            mFeedback.setTextColor(getResources().getColor(R.color.green));
            mFeedback.setText(getResources().getString(R.string.correct));

            mHandler.postDelayed(new Runnable() {
                public void run() {
                    loadNextQuestion();
                }
            }, 300);   // 0.3 seconds

        } else if (mNoFaults) {
            QuizGameActivity.questionReports.get(mQuestionCounter - QuizGameActivity.OFF_BY_ONE)
                    .setIncorrectMark(markNumber);
            choicePress.setAlpha(0.5f);
            mContainerChoice1.setEnabled(false);
            mContainerChoice2.setEnabled(false);
            mContainerChoice3.setEnabled(false);
            mContainerChoice4.setEnabled(false);
            mFeedback.setTextColor(getResources().getColor(R.color.red));
            mFeedback.setText(getResources().getString(R.string.incorrect));
            mHandler.postDelayed(new Runnable() {
                public void run() {
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .remove(THIS_FRAGMENT).commit();
                    getActivity().getSupportFragmentManager().beginTransaction().add(R.id.quiz_fragment_container,
                            new QuizReportFragment(), "quizReportFragment").commit();
                }
            }, 500);    // 0.5 seconds
        } else {
            QuizGameActivity.questionReports.get(mQuestionCounter - QuizGameActivity.OFF_BY_ONE)
                    .setIncorrectMark(markNumber);
            choicePress.setEnabled(false);
            choicePress.setAlpha(0.5f);
            mFeedback.setVisibility(View.INVISIBLE);
            mFeedback.setTextColor(getResources().getColor(R.color.red));
            mFeedback.setText(getResources().getString(R.string.try_again));
            if (mAttemptNumber > 0) {
                mHandler.postDelayed(new Runnable() {
                    public void run() {
                        mFeedback.setVisibility(View.VISIBLE);
                    }
                }, 100);   // 0.1 seconds
            } else {
                mFeedback.setVisibility(View.VISIBLE);
            }
            mAttemptNumber++;
        }
    }

    private void loadNextQuestion() {
        if (QuizGameActivity.questions.isEmpty()) {
            // We answered every question
            // If we remove another: NullPointerException
            // Show game score
            getActivity().getSupportFragmentManager().beginTransaction().remove(THIS_FRAGMENT).commit();
            getActivity().getSupportFragmentManager().beginTransaction().add(R.id.quiz_fragment_container,
                    new QuizReportFragment(), "quizReportFragment").commit();
        } else {
            mCurrentQuestion = QuizGameActivity.questions.remove();
            QuestionReport mCurrentQuestionReport = new QuestionReport(mCurrentQuestion, mQuestionCounter);
            QuizGameActivity.questionReports.add(mCurrentQuestionReport);
            mProgressBar.setProgress(mQuestionCounter);
            mQuestionCounter++;

            mContainerChoice1.setEnabled(true);
            mContainerChoice2.setEnabled(true);
            mContainerChoice3.setEnabled(true);
            mContainerChoice4.setEnabled(true);
            mContainerChoice1.setAlpha(1.0f);
            mContainerChoice2.setAlpha(1.0f);
            mContainerChoice3.setAlpha(1.0f);
            mContainerChoice4.setAlpha(1.0f);
            mFeedback.setText("");

            Picasso.with(getActivity()).load(mCurrentQuestion.getCorrectChoice().getImageUrl())
                    .into(mCityImage);

            mCityNameChoice1.setText(ResourceByNameRetriever.getStringResourceByName
                    (mCurrentQuestion.getChoice1().getCityName(), getActivity()));
            mCityNameChoice2.setText(ResourceByNameRetriever.getStringResourceByName
                    (mCurrentQuestion.getChoice2().getCityName(), getActivity()));
            mCityNameChoice3.setText(ResourceByNameRetriever.getStringResourceByName
                    (mCurrentQuestion.getChoice3().getCityName(), getActivity()));
            mCityNameChoice4.setText(ResourceByNameRetriever.getStringResourceByName
                    (mCurrentQuestion.getChoice4().getCityName(), getActivity()));

            mFlagChoice1.setImageResource(ResourceByNameRetriever.getDrawableResourceByName
                    (mCurrentQuestion.getChoice1().getCountryName(), getActivity()));
            mFlagChoice2.setImageResource(ResourceByNameRetriever.getDrawableResourceByName
                    (mCurrentQuestion.getChoice2().getCountryName(), getActivity()));
            mFlagChoice3.setImageResource(ResourceByNameRetriever.getDrawableResourceByName
                    (mCurrentQuestion.getChoice3().getCountryName(), getActivity()));
            mFlagChoice4.setImageResource(ResourceByNameRetriever.getDrawableResourceByName
                    (mCurrentQuestion.getChoice4().getCountryName(), getActivity()));
        }
    }
}
