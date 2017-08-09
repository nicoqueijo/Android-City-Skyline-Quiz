package com.nicoqueijo.cityskylinequiz.fragment;

import android.app.DialogFragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.ScrollView;

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

    private ScrollView mScrollView;
    private Button mCancelButton;

    public LanguageChooserDialog() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
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

        mScrollView = (ScrollView) view.findViewById(R.id.scroll_view);
        mCancelButton = (Button) view.findViewById(R.id.button_cancel);

        disableRadioButtonsClickability();
        restoreSavedLanguage();
        restoreSavedScrollingPosition();

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
                // change language app wide to selected
                smallDelayAndDismiss();
            }
        });

        mSpanishOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentRadioButtonPressed.pop().setChecked(false);
                mSpanishRadioButton.setChecked(true);
                mCurrentRadioButtonPressed.push(mSpanishRadioButton);
                saveLanguage(Language.SPANISH);
                // change language app wide to selected
                smallDelayAndDismiss();
            }
        });

        mFrenchOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentRadioButtonPressed.pop().setChecked(false);
                mFrenchRadioButton.setChecked(true);
                mCurrentRadioButtonPressed.push(mFrenchRadioButton);
                saveLanguage(Language.FRENCH);
                // change language app wide to selected
                smallDelayAndDismiss();
            }
        });

        mGermanOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentRadioButtonPressed.pop().setChecked(false);
                mGermanRadioButton.setChecked(true);
                mCurrentRadioButtonPressed.push(mGermanRadioButton);
                saveLanguage(Language.GERMAN);
                // change language app wide to selected
                smallDelayAndDismiss();
            }
        });

        mItalianOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentRadioButtonPressed.pop().setChecked(false);
                mItalianRadioButton.setChecked(true);
                mCurrentRadioButtonPressed.push(mItalianRadioButton);
                saveLanguage(Language.ITALIAN);
                // change language app wide to selected
                smallDelayAndDismiss();
            }
        });

        mDutchOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentRadioButtonPressed.pop().setChecked(false);
                mDutchRadioButton.setChecked(true);
                mCurrentRadioButtonPressed.push(mDutchRadioButton);
                saveLanguage(Language.DUTCH);
                // change language app wide to selected
                smallDelayAndDismiss();
            }
        });

        mPortugueseOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentRadioButtonPressed.pop().setChecked(false);
                mPortugueseRadioButton.setChecked(true);
                mCurrentRadioButtonPressed.push(mPortugueseRadioButton);
                saveLanguage(Language.PORTUGUESE);
                // change language app wide to selected
                smallDelayAndDismiss();
            }
        });

        mPolishOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentRadioButtonPressed.pop().setChecked(false);
                mPolishRadioButton.setChecked(true);
                mCurrentRadioButtonPressed.push(mPolishRadioButton);
                saveLanguage(Language.POLISH);
                // change language app wide to selected
                smallDelayAndDismiss();
            }
        });

        mRussianOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentRadioButtonPressed.pop().setChecked(false);
                mRussianRadioButton.setChecked(true);
                mCurrentRadioButtonPressed.push(mRussianRadioButton);
                saveLanguage(Language.RUSSIAN);
                // change language app wide to selected
                smallDelayAndDismiss();
            }
        });

        mTurkishOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentRadioButtonPressed.pop().setChecked(false);
                mTurkishRadioButton.setChecked(true);
                mCurrentRadioButtonPressed.push(mTurkishRadioButton);
                saveLanguage(Language.TURKISH);
                // change language app wide to selected
                smallDelayAndDismiss();
            }
        });

        mChineseOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentRadioButtonPressed.pop().setChecked(false);
                mChineseRadioButton.setChecked(true);
                mCurrentRadioButtonPressed.push(mChineseRadioButton);
                saveLanguage(Language.CHINESE);
                // change language app wide to selected
                smallDelayAndDismiss();
            }
        });

        mJapaneseOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentRadioButtonPressed.pop().setChecked(false);
                mJapaneseRadioButton.setChecked(true);
                mCurrentRadioButtonPressed.push(mJapaneseRadioButton);
                saveLanguage(Language.JAPANESE);
                // change language app wide to selected
                smallDelayAndDismiss();
            }
        });

        mKoreanOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentRadioButtonPressed.pop().setChecked(false);
                mKoreanRadioButton.setChecked(true);
                mCurrentRadioButtonPressed.push(mKoreanRadioButton);
                saveLanguage(Language.KOREAN);
                // change language app wide to selected
                smallDelayAndDismiss();
            }
        });

        mArabicOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentRadioButtonPressed.pop().setChecked(false);
                mArabicRadioButton.setChecked(true);
                mCurrentRadioButtonPressed.push(mArabicRadioButton);
                saveLanguage(Language.ARABIC);
                // change language app wide to selected
                smallDelayAndDismiss();
            }
        });

        mHindiOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentRadioButtonPressed.pop().setChecked(false);
                mHindiRadioButton.setChecked(true);
                mCurrentRadioButtonPressed.push(mHindiRadioButton);
                saveLanguage(Language.HINDI);
                // change language app wide to selected
                smallDelayAndDismiss();
            }
        });

        mMalayOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentRadioButtonPressed.pop().setChecked(false);
                mMalayRadioButton.setChecked(true);
                mCurrentRadioButtonPressed.push(mMalayRadioButton);
                saveLanguage(Language.MALAY);
                // change language app wide to selected
                smallDelayAndDismiss();
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
     * Dismisses the dialog when the user selects a language after a 200 millisecond delay so the
     * user has a chance to see the radio button change to his/her selection.
     */
    private void smallDelayAndDismiss() {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putInt("language_scroll_position", mScrollView.getScrollY());
        editor.commit();
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms
                dismiss();
            }
        }, 200);
    }

    /**
     * Retrieves the language setting from the sharedPreferences file and sets that language to the
     * appropriate RadioButton. I had to use magic numbers in the switch statement because Java only
     * allows constants in the cases so Language.ENGLISH.ordinal() wouldn't work. The magic numbers
     * map to the order declared in the Language enum.
     */
    private void restoreSavedLanguage() {
        int savedLanguage = mSharedPreferences.getInt("language", Language.ENGLISH.ordinal());
        switch (savedLanguage) {
            case 0:
                mEnglishRadioButton.setChecked(true);
                mCurrentRadioButtonPressed.push(mEnglishRadioButton);
                break;
            case 1:
                mSpanishRadioButton.setChecked(true);
                mCurrentRadioButtonPressed.push(mSpanishRadioButton);
                break;
            case 2:
                mFrenchRadioButton.setChecked(true);
                mCurrentRadioButtonPressed.push(mFrenchRadioButton);
                break;
            case 3:
                mGermanRadioButton.setChecked(true);
                mCurrentRadioButtonPressed.push(mGermanRadioButton);
                break;
            case 4:
                mItalianRadioButton.setChecked(true);
                mCurrentRadioButtonPressed.push(mItalianRadioButton);
                break;
            case 5:
                mDutchRadioButton.setChecked(true);
                mCurrentRadioButtonPressed.push(mDutchRadioButton);
                break;
            case 6:
                mPortugueseRadioButton.setChecked(true);
                mCurrentRadioButtonPressed.push(mPortugueseRadioButton);
                break;
            case 7:
                mPolishRadioButton.setChecked(true);
                mCurrentRadioButtonPressed.push(mPolishRadioButton);
                break;
            case 8:
                mRussianRadioButton.setChecked(true);
                mCurrentRadioButtonPressed.push(mRussianRadioButton);
                break;
            case 9:
                mTurkishRadioButton.setChecked(true);
                mCurrentRadioButtonPressed.push(mTurkishRadioButton);
                break;
            case 10:
                mChineseRadioButton.setChecked(true);
                mCurrentRadioButtonPressed.push(mChineseRadioButton);
                break;
            case 11:
                mJapaneseRadioButton.setChecked(true);
                mCurrentRadioButtonPressed.push(mJapaneseRadioButton);
                break;
            case 12:
                mKoreanRadioButton.setChecked(true);
                mCurrentRadioButtonPressed.push(mKoreanRadioButton);
                break;
            case 13:
                mArabicRadioButton.setChecked(true);
                mCurrentRadioButtonPressed.push(mArabicRadioButton);
                break;
            case 14:
                mHindiRadioButton.setChecked(true);
                mCurrentRadioButtonPressed.push(mHindiRadioButton);
                break;
            case 15:
                mMalayRadioButton.setChecked(true);
                mCurrentRadioButtonPressed.push(mMalayRadioButton);
                break;
        }
    }

    /**
     * Retrieves the position of the the ScrollBar from the state of the last time a language was
     * selected and scrolls to that position. Since there is no horizontal ScrollBar, the x position
     * is 0.
     */
    private void restoreSavedScrollingPosition() {
        final int X_POSITION = 0;
        final int Y_POSITION = mSharedPreferences.getInt("language_scroll_position", 0);
        mScrollView.post(new Runnable() {
            public void run() {
                mScrollView.scrollTo(X_POSITION, Y_POSITION);
            }
        });
    }

    /**
     * Saves the language that the user selected to SharedPreferences.
     *
     * @param language the language that the user selected.
     */
    private void saveLanguage(Language language) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putInt("language", language.ordinal());
        editor.commit();
    }

    /**
     * Sets clickable of all RadioButtons to false because their clicks are handled by their parent
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
