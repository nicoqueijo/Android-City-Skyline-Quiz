package com.nicoqueijo.cityskylinequiz.fragment;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.nicoqueijo.cityskylinequiz.R;
import com.nicoqueijo.cityskylinequiz.interfaces.Communicator;

public class ClearScoresDialog extends DialogFragment {

    Communicator communicator;
    private SharedPreferences mSharedPreferences;
    private Button cancelButton;
    private Button deleteButton;

    /**
     * Empty constructor required for DialogFragment.
     */
    public ClearScoresDialog() {
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_clear_scores, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mSharedPreferences = getActivity().getSharedPreferences("settings", Context.MODE_PRIVATE);
        removeTitleBar();

        cancelButton = (Button) view.findViewById(R.id.button_cancel);
        deleteButton = (Button) view.findViewById(R.id.button_delete);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                communicator.onDialogMessage("false");
                dismiss();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // clear the saved scores
                // show toast confirming operation (might be done from settings activity after receiving dialog status)
                communicator.onDialogMessage("true");
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

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        communicator = (Communicator) activity;
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

}
