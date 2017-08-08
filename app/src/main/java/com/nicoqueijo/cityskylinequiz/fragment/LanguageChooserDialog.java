package com.nicoqueijo.cityskylinequiz.fragment;

import android.app.DialogFragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.nicoqueijo.cityskylinequiz.R;
import com.nicoqueijo.cityskylinequiz.helper.CornerRounder;

import java.util.Stack;

public class LanguageChooserDialog extends DialogFragment {

    public enum Language {
        ENGLISH, SPANISH, FRENCH, GERMAN, ITALIAN, DUTCH, PORTUGUESE, POLISH, RUSSIAN, TURKISH,
        CHINESE, JAPANESE, KOREAN, ARABIC, HINDI, MALAY
    }

    private SharedPreferences mSharedPreferences;
    private Stack<RadioButton> mCurrentRadioButtonPressed = new Stack<>();

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

    private RadioButton mEnglishRadioButton;
    private RadioButton mSpanishRadioButton;
    private RadioButton mFrenchRadioButton;
    private RadioButton mGermanRadioButton;
    private RadioButton mItalianRadioButton;
    private RadioButton mDutchRadioButton;
    private RadioButton mPortugueseRadioButton;
    private RadioButton mPolishRadioButton;
    private RadioButton mRussianRadioButton;
    private RadioButton mTurkishRadioButton;
    private RadioButton mChineseRadioButton;
    private RadioButton mJapaneseRadioButton;
    private RadioButton mKoreanRadioButton;
    private RadioButton mArabicRadioButton;
    private RadioButton mHindiRadioButton;
    private RadioButton mMalayRadioButton;

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
        mSharedPreferences = getActivity().getSharedPreferences("settings", Context.MODE_PRIVATE);
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

        mEnglishRadioButton = (RadioButton) view.findViewById(R.id.radio_button_english);
        mSpanishRadioButton = (RadioButton) view.findViewById(R.id.radio_button_spanish);
        mFrenchRadioButton = (RadioButton) view.findViewById(R.id.radio_button_french);
        mGermanRadioButton = (RadioButton) view.findViewById(R.id.radio_button_german);
        mItalianRadioButton = (RadioButton) view.findViewById(R.id.radio_button_italian);
        mDutchRadioButton = (RadioButton) view.findViewById(R.id.radio_button_dutch);
        mPortugueseRadioButton = (RadioButton) view.findViewById(R.id.radio_button_portuguese);
        mPolishRadioButton = (RadioButton) view.findViewById(R.id.radio_button_polish);
        mRussianRadioButton = (RadioButton) view.findViewById(R.id.radio_button_russian);
        mTurkishRadioButton = (RadioButton) view.findViewById(R.id.radio_button_turkish);
        mChineseRadioButton = (RadioButton) view.findViewById(R.id.radio_button_chinese);
        mJapaneseRadioButton = (RadioButton) view.findViewById(R.id.radio_button_japanese);
        mKoreanRadioButton = (RadioButton) view.findViewById(R.id.radio_button_korean);
        mArabicRadioButton = (RadioButton) view.findViewById(R.id.radio_button_arabic);
        mHindiRadioButton = (RadioButton) view.findViewById(R.id.radio_button_hindi);
        mMalayRadioButton = (RadioButton) view.findViewById(R.id.radio_button_malay);

        disableRadioButtonsClickability();

        mCancelButton = (Button) view.findViewById(R.id.cancel_button);

        mSharedPreferences.getInt("language", Language.ENGLISH.ordinal());
        setSavedLanguage();

        CornerRounder.roundImageCorners(mUnitedKingdomFlag, mSpainFlag, mFanceFlag, mGermanyFlag,
                mItalyFlag, mNetherlandsFlag, mPortugalFlag, mPolandFlag, mRussiaFlag, mTurkeyFlag,
                mChinaFlag, mJapanFlag, mSouthKoreaFlag, mSaudiArabiaFlag, mIndiaFlag, mMalaysiaFlag);

        mEnglishOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentRadioButtonPressed.pop().setChecked(false);
                mEnglishRadioButton.setChecked(true);
                mCurrentRadioButtonPressed.push(mEnglishRadioButton);
                saveLanguage(Language.ENGLISH);
                // change radio button selected
                // save language selected to sharedPreferences
                // change language app-wide
                // dismiss dialog
            }
        });

        mSpanishOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentRadioButtonPressed.pop().setChecked(false);
                mSpanishRadioButton.setChecked(true);
                mCurrentRadioButtonPressed.push(mSpanishRadioButton);
                saveLanguage(Language.SPANISH);
                // change radio button selected
                // save language selected to sharedPreferences
                // change language app-wide
                // dismiss dialog
            }
        });

        mFrenchOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentRadioButtonPressed.pop().setChecked(false);
                mFrenchRadioButton.setChecked(true);
                mCurrentRadioButtonPressed.push(mFrenchRadioButton);
                saveLanguage(Language.FRENCH);
                // change radio button selected
                // save language selected to sharedPreferences
                // change language app-wide
                // dismiss dialog
            }
        });

        mGermanOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentRadioButtonPressed.pop().setChecked(false);
                mGermanRadioButton.setChecked(true);
                mCurrentRadioButtonPressed.push(mGermanRadioButton);
                saveLanguage(Language.GERMAN);
                // change radio button selected
                // save language selected to sharedPreferences
                // change language app-wide
                // dismiss dialog
            }
        });

        mItalianOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentRadioButtonPressed.pop().setChecked(false);
                mItalianRadioButton.setChecked(true);
                mCurrentRadioButtonPressed.push(mItalianRadioButton);
                saveLanguage(Language.ITALIAN);
                // change radio button selected
                // save language selected to sharedPreferences
                // change language app-wide
                // dismiss dialog
            }
        });

        mDutchOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentRadioButtonPressed.pop().setChecked(false);
                mDutchRadioButton.setChecked(true);
                mCurrentRadioButtonPressed.push(mDutchRadioButton);
                saveLanguage(Language.DUTCH);
                // change radio button selected
                // save language selected to sharedPreferences
                // change language app-wide
                // dismiss dialog
            }
        });

        mPortugueseOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentRadioButtonPressed.pop().setChecked(false);
                mPortugueseRadioButton.setChecked(true);
                mCurrentRadioButtonPressed.push(mPortugueseRadioButton);
                saveLanguage(Language.PORTUGUESE);
                // change radio button selected
                // save language selected to sharedPreferences
                // change language app-wide
                // dismiss dialog
            }
        });

        mPolishOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentRadioButtonPressed.pop().setChecked(false);
                mPolishRadioButton.setChecked(true);
                mCurrentRadioButtonPressed.push(mPolishRadioButton);
                saveLanguage(Language.POLISH);
                // change radio button selected
                // save language selected to sharedPreferences
                // change language app-wide
                // dismiss dialog
            }
        });

        mRussianOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentRadioButtonPressed.pop().setChecked(false);
                mRussianRadioButton.setChecked(true);
                mCurrentRadioButtonPressed.push(mRussianRadioButton);
                saveLanguage(Language.RUSSIAN);
                // change radio button selected
                // save language selected to sharedPreferences
                // change language app-wide
                // dismiss dialog
            }
        });

        mTurkishOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentRadioButtonPressed.pop().setChecked(false);
                mTurkishRadioButton.setChecked(true);
                mCurrentRadioButtonPressed.push(mTurkishRadioButton);
                saveLanguage(Language.TURKISH);
                // change radio button selected
                // save language selected to sharedPreferences
                // change language app-wide
                // dismiss dialog
            }
        });

        mChineseOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentRadioButtonPressed.pop().setChecked(false);
                mChineseRadioButton.setChecked(true);
                mCurrentRadioButtonPressed.push(mChineseRadioButton);
                saveLanguage(Language.CHINESE);
                // change radio button selected
                // save language selected to sharedPreferences
                // change language app-wide
                // dismiss dialog
            }
        });

        mJapaneseOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentRadioButtonPressed.pop().setChecked(false);
                mJapaneseRadioButton.setChecked(true);
                mCurrentRadioButtonPressed.push(mJapaneseRadioButton);
                saveLanguage(Language.JAPANESE);
                // change radio button selected
                // save language selected to sharedPreferences
                // change language app-wide
                // dismiss dialog
            }
        });

        mKoreanOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentRadioButtonPressed.pop().setChecked(false);
                mKoreanRadioButton.setChecked(true);
                mCurrentRadioButtonPressed.push(mKoreanRadioButton);
                saveLanguage(Language.KOREAN);
                // change radio button selected
                // save language selected to sharedPreferences
                // change language app-wide
                // dismiss dialog
            }
        });

        mArabicOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentRadioButtonPressed.pop().setChecked(false);
                mArabicRadioButton.setChecked(true);
                mCurrentRadioButtonPressed.push(mArabicRadioButton);
                saveLanguage(Language.ARABIC);
                // change radio button selected
                // save language selected to sharedPreferences
                // change language app-wide
                // dismiss dialog
            }
        });

        mHindiOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentRadioButtonPressed.pop().setChecked(false);
                mHindiRadioButton.setChecked(true);
                mCurrentRadioButtonPressed.push(mHindiRadioButton);
                saveLanguage(Language.HINDI);
                // change radio button selected
                // save language selected to sharedPreferences
                // change language app-wide
                // dismiss dialog
            }
        });

        mMalayOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentRadioButtonPressed.pop().setChecked(false);
                mMalayRadioButton.setChecked(true);
                mCurrentRadioButtonPressed.push(mMalayRadioButton);
                saveLanguage(Language.MALAY);
                // change radio button selected
                // save language selected to sharedPreferences
                // change language app-wide
                // dismiss dialog
            }
        });

        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    private void setSavedLanguage() {
        int savedLanguage = mSharedPreferences.getInt("language", Language.ENGLISH.ordinal());
        switch (savedLanguage) {
            case 0:
                mEnglishRadioButton.setChecked(true);
                mCurrentRadioButtonPressed.push(mEnglishRadioButton);
        }
        switch (savedLanguage) {
            case 1:
                mSpanishRadioButton.setChecked(true);
                mCurrentRadioButtonPressed.push(mSpanishRadioButton);
        }
        switch (savedLanguage) {
            case 2:
                mFrenchRadioButton.setChecked(true);
                mCurrentRadioButtonPressed.push(mFrenchRadioButton);
        }
        switch (savedLanguage) {
            case 3:
                mGermanRadioButton.setChecked(true);
                mCurrentRadioButtonPressed.push(mGermanRadioButton);
        }
        switch (savedLanguage) {
            case 4:
                mItalianRadioButton.setChecked(true);
                mCurrentRadioButtonPressed.push(mItalianRadioButton);
        }
        switch (savedLanguage) {
            case 5:
                mDutchRadioButton.setChecked(true);
                mCurrentRadioButtonPressed.push(mDutchRadioButton);
        }
        switch (savedLanguage) {
            case 6:
                mPortugueseRadioButton.setChecked(true);
                mCurrentRadioButtonPressed.push(mPortugueseRadioButton);
        }
        switch (savedLanguage) {
            case 7:
                mPolishRadioButton.setChecked(true);
                mCurrentRadioButtonPressed.push(mPolishRadioButton);
        }
        switch (savedLanguage) {
            case 8:
                mRussianRadioButton.setChecked(true);
                mCurrentRadioButtonPressed.push(mRussianRadioButton);
        }
        switch (savedLanguage) {
            case 9:
                mTurkishRadioButton.setChecked(true);
                mCurrentRadioButtonPressed.push(mTurkishRadioButton);
        }
        switch (savedLanguage) {
            case 10:
                mChineseRadioButton.setChecked(true);
                mCurrentRadioButtonPressed.push(mChineseRadioButton);
        }
        switch (savedLanguage) {
            case 11:
                mJapaneseRadioButton.setChecked(true);
                mCurrentRadioButtonPressed.push(mJapaneseRadioButton);
        }
        switch (savedLanguage) {
            case 12:
                mKoreanRadioButton.setChecked(true);
                mCurrentRadioButtonPressed.push(mKoreanRadioButton);
        }
        switch (savedLanguage) {
            case 13:
                mArabicRadioButton.setChecked(true);
                mCurrentRadioButtonPressed.push(mArabicRadioButton);
        }
        switch (savedLanguage) {
            case 14:
                mHindiRadioButton.setChecked(true);
                mCurrentRadioButtonPressed.push(mHindiRadioButton);
        }
        switch (savedLanguage) {
            case 15:
                mMalayRadioButton.setChecked(true);
                mCurrentRadioButtonPressed.push(mMalayRadioButton);
        }
    }

    private void saveLanguage(Language language) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        switch (language) {
            case ENGLISH:
                editor.putInt("language", Language.ENGLISH.ordinal()).commit();
                break;
            case SPANISH:
                editor.putInt("language", Language.SPANISH.ordinal()).commit();
                break;
            case FRENCH:
                editor.putInt("language", Language.FRENCH.ordinal()).commit();
                break;
            case GERMAN:
                ;
                editor.putInt("language", Language.GERMAN.ordinal()).commit();
                break;
            case ITALIAN:
                editor.putInt("language", Language.ITALIAN.ordinal()).commit();
                break;
            case DUTCH:
                editor.putInt("language", Language.DUTCH.ordinal()).commit();
                break;
            case PORTUGUESE:
                editor.putInt("language", Language.PORTUGUESE.ordinal()).commit();
                break;
            case POLISH:
                editor.putInt("language", Language.POLISH.ordinal()).commit();
                break;
            case RUSSIAN:
                editor.putInt("language", Language.RUSSIAN.ordinal()).commit();
                break;
            case TURKISH:
                editor.putInt("language", Language.TURKISH.ordinal()).commit();
                break;
            case CHINESE:
                editor.putInt("language", Language.CHINESE.ordinal()).commit();
                break;
            case JAPANESE:
                editor.putInt("language", Language.JAPANESE.ordinal()).commit();
                break;
            case KOREAN:
                editor.putInt("language", Language.KOREAN.ordinal()).commit();
                break;
            case ARABIC:
                editor.putInt("language", Language.ARABIC.ordinal()).commit();
                break;
            case HINDI:
                editor.putInt("language", Language.HINDI.ordinal()).commit();
                break;
            case MALAY:
                editor.putInt("language", Language.MALAY.ordinal()).commit();
                break;
        }
    }

    /**
     * Sets clickable of all RadioButtons to false because their clicks are handles by their parent
     * view.
     */
    private void disableRadioButtonsClickability() {
        mEnglishRadioButton.setClickable(false);
        mSpanishRadioButton.setClickable(false);
        mFrenchRadioButton.setClickable(false);
        mGermanRadioButton.setClickable(false);
        mItalianRadioButton.setClickable(false);
        mDutchRadioButton.setClickable(false);
        mPortugueseRadioButton.setClickable(false);
        mPolishRadioButton.setClickable(false);
        mRussianRadioButton.setClickable(false);
        mTurkishRadioButton.setClickable(false);
        mChineseRadioButton.setClickable(false);
        mJapaneseRadioButton.setClickable(false);
        mKoreanRadioButton.setClickable(false);
        mArabicRadioButton.setClickable(false);
        mHindiRadioButton.setClickable(false);
        mMalayRadioButton.setClickable(false);
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
