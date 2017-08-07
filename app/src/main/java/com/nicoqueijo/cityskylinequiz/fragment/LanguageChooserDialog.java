package com.nicoqueijo.cityskylinequiz.fragment;

import android.app.DialogFragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import com.nicoqueijo.cityskylinequiz.R;
import com.nicoqueijo.cityskylinequiz.helper.CornerRounder;

public class LanguageChooserDialog extends DialogFragment {

    private SharedPreferences mSharedPreferences;

    private ImageView mUnitedKingdomFlag;
    private ImageView mSpainFlag;
    private ImageView mFanceFlag;
    private ImageView mGermanyFlag;
    private ImageView mItalyFlag;
    private ImageView mNetherlandsFlag;
    private ImageView mPortugalFlag;
    private ImageView mPolandFlag;
    private ImageView mRussiaFlag;
    private ImageView mTurkeyFlag;
    private ImageView mChinaFlag;
    private ImageView mJapanFlag;
    private ImageView mSouthKoreaFlag;
    private ImageView mSaudiArabiaFlag;
    private ImageView mIndiaFlag;
    private ImageView mMalaysiaFlag;

    private LinearLayout mEnglishOption;
    private LinearLayout mSpanishOption;
    private LinearLayout mFrenchOption;
    private LinearLayout mGermanOption;
    private LinearLayout mItalianOption;
    private LinearLayout mDutchOption;
    private LinearLayout mPortugueseOption;
    private LinearLayout mPolishOption;
    private LinearLayout mRussianOption;
    private LinearLayout mTurkishOption;
    private LinearLayout mChineseOption;
    private LinearLayout mJapaneseOption;
    private LinearLayout mKoreanOption;
    private LinearLayout mArabicOption;
    private LinearLayout mHindiOption;
    private LinearLayout mMalayOption;

    private Button mCancelButton;

    public LanguageChooserDialog() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mSharedPreferences = getActivity().getSharedPreferences("settings", Context.MODE_PRIVATE);
        return inflater.inflate(R.layout.dialog_languague_chooser, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        removeTitleBar();

        mUnitedKingdomFlag = (ImageView) view.findViewById(R.id.flag_united_kingdom);
        mSpainFlag = (ImageView) view.findViewById(R.id.flag_spain);
        mFanceFlag = (ImageView) view.findViewById(R.id.flag_france);
        mGermanyFlag = (ImageView) view.findViewById(R.id.flag_germany);
        mItalyFlag = (ImageView) view.findViewById(R.id.flag_italy);
        mNetherlandsFlag = (ImageView) view.findViewById(R.id.flag_netherlands);
        mPortugalFlag = (ImageView) view.findViewById(R.id.flag_portugal);
        mPolandFlag = (ImageView) view.findViewById(R.id.flag_poland);
        mRussiaFlag = (ImageView) view.findViewById(R.id.flag_russia);
        mTurkeyFlag = (ImageView) view.findViewById(R.id.flag_turkey);
        mChinaFlag = (ImageView) view.findViewById(R.id.flag_china);
        mJapanFlag = (ImageView) view.findViewById(R.id.flag_japan);
        mSouthKoreaFlag = (ImageView) view.findViewById(R.id.flag_south_korea);
        mSaudiArabiaFlag = (ImageView) view.findViewById(R.id.flag_saudi_arabia);
        mIndiaFlag = (ImageView) view.findViewById(R.id.flag_india);
        mMalaysiaFlag = (ImageView) view.findViewById(R.id.flag_malaysia);

        mEnglishOption = (LinearLayout) view.findViewById(R.id.choice_english);
        mSpanishOption = (LinearLayout) view.findViewById(R.id.choice_spanish);
        mFrenchOption = (LinearLayout) view.findViewById(R.id.choice_french);
        mGermanOption = (LinearLayout) view.findViewById(R.id.choice_german);
        mItalianOption = (LinearLayout) view.findViewById(R.id.choice_italian);
        mDutchOption = (LinearLayout) view.findViewById(R.id.choice_dutch);
        mPortugueseOption = (LinearLayout) view.findViewById(R.id.choice_portuguese);
        mPolishOption = (LinearLayout) view.findViewById(R.id.choice_polish);
        mRussianOption = (LinearLayout) view.findViewById(R.id.choice_russian);
        mTurkishOption = (LinearLayout) view.findViewById(R.id.choice_turkish);
        mChineseOption = (LinearLayout) view.findViewById(R.id.choice_chinese);
        mJapaneseOption = (LinearLayout) view.findViewById(R.id.choice_japanese);
        mKoreanOption = (LinearLayout) view.findViewById(R.id.choice_korean);
        mArabicOption = (LinearLayout) view.findViewById(R.id.choice_arabic);
        mHindiOption = (LinearLayout) view.findViewById(R.id.choice_hindi);
        mMalayOption = (LinearLayout) view.findViewById(R.id.choice_malay);

        mCancelButton = (Button) view.findViewById(R.id.cancel_button);

        CornerRounder.roundImageCorners(mUnitedKingdomFlag, mSpainFlag, mFanceFlag, mGermanyFlag,
                mItalyFlag, mNetherlandsFlag, mPortugalFlag, mPolandFlag, mRussiaFlag, mTurkeyFlag,
                mChinaFlag, mJapanFlag, mSouthKoreaFlag, mSaudiArabiaFlag, mIndiaFlag, mMalaysiaFlag);

        mEnglishOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mSpanishOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mFrenchOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mGermanOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mItalianOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mDutchOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mPortugueseOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mPolishOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mRussianOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mTurkishOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mChineseOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mJapaneseOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mKoreanOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mArabicOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mHindiOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mMalayOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    /**
     * Overrides the class's onStart method so the window size could be adjusted at run-time.
     */
    @Override
    public void onStart() {
        super.onStart();
        adjustWindowSize();
    }

    /**
     * Removes title bar from dialog fragment that is displayed on older API versions.
     */
    private void removeTitleBar() {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
    }

    /**
     * Adjusts the dialog fragment's window size in accordance to the device resolution.
     * Sets the dialog fragment's window width to 92% of the device's screen width.
     * Sets the dialog fragment's window height to 80% of the device's screen height.
     */
    private void adjustWindowSize() {
        final double WIDTH_PERCENTAGE = 0.92;
        final double HEIGHT_PERCENTAGE = 0.80;
        int width = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels;
        int newWidth = (int) (WIDTH_PERCENTAGE * (double) width);
        int newHeight = (int) (HEIGHT_PERCENTAGE * (double) height);
        getDialog().getWindow().setLayout(newWidth, newHeight);
    }

    /**
     * Determines the aspect ratio of the running device by dividing the height by the width.
     *
     * @return the aspect ratio of the running device
     */
    private double getAspectRatio() {
        double width = (double) getResources().getDisplayMetrics().widthPixels;
        double height = (double) getResources().getDisplayMetrics().heightPixels;
        return (height / width);
    }

    /**
     * Assuming the aspect ratio of a standard phone is 1.777 (the division of 16 by 9) this method
     * tries to determine if the aspect ratio of the running device is of that of a tablet.
     *
     * @return whether the aspect ratio on the running device resembles a tablet.
     */
    private boolean isRunningOnTablet() {
        final double MINIMUM_ASPECT_RATIO_OF_A_PHONE = 1.6;
        return getAspectRatio() < MINIMUM_ASPECT_RATIO_OF_A_PHONE;
    }
}
