package com.nicoqueijo.cityskylinequiz.fragments;

import android.net.Uri;
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
import com.squareup.picasso.Picasso;

public class QuizFragment extends Fragment implements View.OnClickListener {

    private final String LOG_V_QUIZ_FRAGMENT = "QuizFragment";
    private final int PROGRESS_BAR_MULTIPLIER = 10;

    public static int questionCounter = 0;
    private int attemptNumber = 0;
    private int mGroupPosition;
    private int mChildPosition;
    private Handler handler = new Handler();

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

    private OnFragmentInteractionListener mListener;

    /**
     * Required empty public constructor
     */
    public QuizFragment() {
    }

//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment QuizFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static QuizFragment newInstance(String param1, String param2) {
//        QuizFragment fragment = new QuizFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_quiz, container, false);

//        mGroupPosition = this.getArguments().getInt("group");
//        mChildPosition = this.getArguments().getInt("child");

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

        mContainerChoice1.setOnClickListener(this);
        mContainerChoice2.setOnClickListener(this);
        mContainerChoice3.setOnClickListener(this);
        mContainerChoice4.setOnClickListener(this);

        CornerRounder.roundImageCorners(mCityImage, mFlagChoice1, mFlagChoice2, mFlagChoice3,
                mFlagChoice4);

        return view;
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }


    @Override
    public void onStart() {
        super.onStart();
        loadNextQuestion();
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

        Question question = QuizGameActivity.questions.peek();
        City guess = null;
        if (choicePress == mContainerChoice1) {
            guess = question.getChoice1();
        } else if (choicePress == mContainerChoice2) {
            guess = question.getChoice2();
        } else if (choicePress == mContainerChoice3) {
            guess = question.getChoice3();
        } else if (choicePress == mContainerChoice4) {
            guess = question.getChoice4();
        }

        if (guess.getCityName().equals(question.getCorrectChoice().getCityName())) {
            attemptNumber = 0;
            mContainerChoice1.setEnabled(false);
            mContainerChoice2.setEnabled(false);
            mContainerChoice3.setEnabled(false);
            mContainerChoice4.setEnabled(false);
            mFeedback.setTextColor(getResources().getColor(R.color.green));
            mFeedback.setText(getResources().getString(R.string.correct));

            handler.postDelayed(new Runnable() {
                public void run() {
                    QuizGameActivity.questions.remove();
                    loadNextQuestion();
                }
            }, 500);   // 0.5 seconds

        } else {
            choicePress.setEnabled(false);
            choicePress.setAlpha(0.5f);
            Handler handler = new Handler();
            mFeedback.setVisibility(View.INVISIBLE);
            mFeedback.setTextColor(getResources().getColor(R.color.red));
            mFeedback.setText(getResources().getString(R.string.try_again));
            if (attemptNumber > 0) {
                handler.postDelayed(new Runnable() {
                    public void run() {
                        mFeedback.setVisibility(View.VISIBLE);
                    }
                }, 100);   // 0.5 seconds
            } else {
                mFeedback.setVisibility(View.VISIBLE);
            }
            attemptNumber++;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        questionCounter = 0;
    }

    private void loadNextQuestion() {

        mProgressBar.setProgress(questionCounter * PROGRESS_BAR_MULTIPLIER);
        questionCounter++;
        if (questionCounter > 10) {
            // We answered every question
            // If we remove another NullPointerException
            // Show game score
            getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
        }

        mContainerChoice1.setEnabled(true);
        mContainerChoice2.setEnabled(true);
        mContainerChoice3.setEnabled(true);
        mContainerChoice4.setEnabled(true);
        mContainerChoice1.setAlpha(1.0f);
        mContainerChoice2.setAlpha(1.0f);
        mContainerChoice3.setAlpha(1.0f);
        mContainerChoice4.setAlpha(1.0f);
        mFeedback.setText("");

        Picasso.with(getActivity()).load(QuizGameActivity.questions.peek().getCorrectChoice().getImageUrl()).into(mCityImage);

        mCityNameChoice1.setText(ResourceByNameRetriever.getStringResourceByName(QuizGameActivity.questions.peek().getChoice1().getCityName(), getActivity()));
        mCityNameChoice2.setText(ResourceByNameRetriever.getStringResourceByName(QuizGameActivity.questions.peek().getChoice2().getCityName(), getActivity()));
        mCityNameChoice3.setText(ResourceByNameRetriever.getStringResourceByName(QuizGameActivity.questions.peek().getChoice3().getCityName(), getActivity()));
        mCityNameChoice4.setText(ResourceByNameRetriever.getStringResourceByName(QuizGameActivity.questions.peek().getChoice4().getCityName(), getActivity()));

        mFlagChoice1.setImageResource(ResourceByNameRetriever.getDrawableResourceByName(QuizGameActivity.questions.peek().getChoice1().getCountryName(), getActivity()));
        mFlagChoice2.setImageResource(ResourceByNameRetriever.getDrawableResourceByName(QuizGameActivity.questions.peek().getChoice2().getCountryName(), getActivity()));
        mFlagChoice3.setImageResource(ResourceByNameRetriever.getDrawableResourceByName(QuizGameActivity.questions.peek().getChoice3().getCountryName(), getActivity()));
        mFlagChoice4.setImageResource(ResourceByNameRetriever.getDrawableResourceByName(QuizGameActivity.questions.peek().getChoice4().getCountryName(), getActivity()));
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void passGameMode(int group, int child) {
        mGroupPosition = group;
        mChildPosition = child;
    }
}
