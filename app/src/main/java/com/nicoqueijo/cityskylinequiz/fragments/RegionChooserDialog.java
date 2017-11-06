package com.nicoqueijo.cityskylinequiz.fragments;

import android.app.DialogFragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.nicoqueijo.cityskylinequiz.R;

/**
 * This is the DialogFragment that allows the user to change the quiz's regions.
 */
public class RegionChooserDialog extends DialogFragment {

    private SharedPreferences mSharedPreferences;

    private LinearLayout mAmericasOptionContainer;
    private LinearLayout mEuropeOptionContainer;
    private LinearLayout mAsiaAfricaOceaniaOptionContainer;

    private CheckBox mAmericasCheckBox;
    private CheckBox mEuropeCheckBox;
    private CheckBox mAsiaAfricaOceaniaCheckBox;

    private Button mCancelButton;
    private Button mOkButton;

    /**
     * Empty constructor required for DialogFragment.
     */
    public RegionChooserDialog() {
    }

    /**
     * * Called to have the fragment instantiate its user interface view.
     *
     * @param inflater           The LayoutInflater object that can be used to inflate
     *                           any views in the fragment.
     * @param container          If non-null, this is the parent view that the fragment's
     *                           UI should be attached to.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     *                           from a previous saved state as given here.
     * @return Return the View for the fragment's UI, or null.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_region_chooser, container, false);
    }

    /**
     * Called immediately after onCreateView has returned, but before any saved state has been
     * restored in to the view. This gives subclasses a chance to initialize themselves once
     * they know their view hierarchy has been completely created.
     *
     * @param view               The View returned by onCreateView
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     *                           from a previous saved state as given here.
     */
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mSharedPreferences = getActivity().getSharedPreferences("settings", Context.MODE_PRIVATE);
        removeTitleBar();

        mAmericasOptionContainer = (LinearLayout) view.findViewById(R.id.choice_americas);
        mEuropeOptionContainer = (LinearLayout) view.findViewById(R.id.choice_europe);
        mAsiaAfricaOceaniaOptionContainer = (LinearLayout)
                view.findViewById(R.id.choice_asia_africa_oceania);

        mAmericasCheckBox = (CheckBox) view.findViewById(R.id.check_box_americas);
        mEuropeCheckBox = (CheckBox) view.findViewById(R.id.check_box_europe);
        mAsiaAfricaOceaniaCheckBox = (CheckBox) view.findViewById(R.id.check_box_asia_africa_oceania);

        mCancelButton = (Button) view.findViewById(R.id.button_cancel);
        mOkButton = (Button) view.findViewById(R.id.button_ok);

        disableCheckBoxesClickability();
        restoreSelectedRegions();

        mAmericasOptionContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAmericasCheckBox.setChecked(!mAmericasCheckBox.isChecked());
            }
        });

        mEuropeOptionContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEuropeCheckBox.setChecked(!mEuropeCheckBox.isChecked());
            }
        });

        mAsiaAfricaOceaniaOptionContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAsiaAfricaOceaniaCheckBox.setChecked(!mAsiaAfricaOceaniaCheckBox.isChecked());
            }
        });

        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        mOkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveSelectedRegions(mAmericasCheckBox.isChecked(), mEuropeCheckBox.isChecked(),
                        mAsiaAfricaOceaniaCheckBox.isChecked());
                dismiss();
            }
        });
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
     * Sets the dialog fragment's window height the content of the layout.
     */
    private void adjustWindowSize() {
        final double WIDTH_PERCENTAGE = 0.92;
        int width = getResources().getDisplayMetrics().widthPixels;
        int newWidth = (int) (WIDTH_PERCENTAGE * (double) width);
        getDialog().getWindow().setLayout(newWidth, WindowManager.LayoutParams.WRAP_CONTENT);
    }

    /**
     * Saves the regions that the user selected to SharedPreferences.
     */
    private void saveSelectedRegions(boolean... selectedRegions) {
        final int AMERICAS = 0;
        final int EUROPE = 1;
        final int ASIA_AFRICA_OCEANIA = 2;
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putBoolean("region_americas", selectedRegions[AMERICAS]);
        editor.putBoolean("region_europe", selectedRegions[EUROPE]);
        editor.putBoolean("region_asia_africa_oceania", selectedRegions[ASIA_AFRICA_OCEANIA]);
        editor.commit();
    }

    /**
     * Retrieves the language setting from the SharedPreferences file and sets that language to the
     * appropriate RadioButton. If this is the first time running the app SharedPreferences won't
     * have a language value and the default value will be the system language. If the system
     * language is not a supported language in this app it defaults to English.
     */
    private void restoreSelectedRegions() {
        boolean americasSelected = mSharedPreferences.getBoolean("region_americas", true);
        boolean europeSelected = mSharedPreferences.getBoolean("region_europe", true);
        boolean asiaAfricaOceaniaSelected = mSharedPreferences
                .getBoolean("region_asia_africa_oceania", true);
        if (americasSelected) {
            mAmericasCheckBox.setChecked(true);
        }
        if (europeSelected) {
            mEuropeCheckBox.setChecked(true);
        }
        if (asiaAfricaOceaniaSelected) {
            mAsiaAfricaOceaniaCheckBox.setChecked(true);
        }
    }

    /**
     * Sets clickable of all CheckBoxes to false because their clicks are handled by their parent
     * view.
     */
    private void disableCheckBoxesClickability() {
        mAmericasCheckBox.setClickable(false);
        mEuropeCheckBox.setClickable(false);
        mAsiaAfricaOceaniaCheckBox.setClickable(false);
    }

    /**
     * Overrides the class's onStart method so the window size could be adjusted at run-time.
     */
    @Override
    public void onStart() {
        super.onStart();
        adjustWindowSize();
    }
}

