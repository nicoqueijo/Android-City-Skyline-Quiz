package com.nicoqueijo.cityskylinequiz.fragments;

import android.app.DialogFragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.nicoqueijo.cityskylinequiz.R;

/**
 * This is the DialogFragment that allows the user to change the quiz's regions.
 */
public class RegionChooserDialog extends DialogFragment {
    private SharedPreferences mSharedPreferences;

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
     * Overrides the class's onStart method so the window size could be adjusted at run-time.
     */
    @Override
    public void onStart() {
        super.onStart();
        adjustWindowSize();
    }
}

