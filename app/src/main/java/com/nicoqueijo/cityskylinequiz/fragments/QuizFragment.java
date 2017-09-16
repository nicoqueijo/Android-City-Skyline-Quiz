package com.nicoqueijo.cityskylinequiz.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nicoqueijo.cityskylinequiz.R;

public class QuizFragment extends Fragment {

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

    private ImageView mCorrectMarkChoice1;
    private ImageView mCorrectMarkChoice2;
    private ImageView mCorrectMarkChoice3;
    private ImageView mCorrectMarkChoice4;

    private ImageView mIncorrectMarkChoice1;
    private ImageView mIncorrectMarkChoice2;
    private ImageView mIncorrectMarkChoice3;
    private ImageView mIncorrectMarkChoice4;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_quiz, container, false);

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

        mCorrectMarkChoice1 = (ImageView) view.findViewById(R.id.correct_mark_choice_one);
        mCorrectMarkChoice2 = (ImageView) view.findViewById(R.id.correct_mark_choice_two);
        mCorrectMarkChoice3 = (ImageView) view.findViewById(R.id.correct_mark_choice_three);
        mCorrectMarkChoice4 = (ImageView) view.findViewById(R.id.correct_mark_choice_four);

        mIncorrectMarkChoice1 = (ImageView) view.findViewById(R.id.incorrect_mark_choice_one);
        mIncorrectMarkChoice2 = (ImageView) view.findViewById(R.id.incorrect_mark_choice_two);
        mIncorrectMarkChoice3 = (ImageView) view.findViewById(R.id.incorrect_mark_choice_three);
        mIncorrectMarkChoice4 = (ImageView) view.findViewById(R.id.incorrect_mark_choice_four);

        return view;
    }
}
