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
import android.widget.ImageButton;
import android.widget.ImageView;

import com.nicoqueijo.cityskylinequiz.R;
import com.nicoqueijo.cityskylinequiz.helper.CornerRounder;

public class LanguageChooserDialog extends DialogFragment {

    private SharedPreferences sharedPreferences;
    private Button cancelButton;
    private ImageView unitedKingdomFlag;
    private ImageView spainFlag;
    private ImageView franceFlag;
    private ImageView germanyFlag;
    private ImageView italyFlag;
    private ImageView netherlandsFlag;
    private ImageView portugalFlag;
    private ImageView polandFlag;
    private ImageView russiaFlag;
    private ImageView turkeyFlag;
    private ImageView chinaFlag;
    private ImageView japanFlag;
    private ImageView southKoreaFlag;
    private ImageView saudiArabiaFlag;
    private ImageView indiaFlag;
    private ImageView malaysiaFlag;

    public LanguageChooserDialog() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        sharedPreferences = getActivity().getSharedPreferences("settings", Context.MODE_PRIVATE);
        return inflater.inflate(R.layout.dialog_languague_chooser, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        removeTitleBar();

        cancelButton = (Button) view.findViewById(R.id.cancel_button);
        unitedKingdomFlag = (ImageView) view.findViewById(R.id.flag_united_kingdom);
        spainFlag = (ImageView) view.findViewById(R.id.flag_spain);
        franceFlag = (ImageView) view.findViewById(R.id.flag_france);
        germanyFlag = (ImageView) view.findViewById(R.id.flag_germany);
        italyFlag = (ImageView) view.findViewById(R.id.flag_italy);
        netherlandsFlag = (ImageView) view.findViewById(R.id.flag_netherlands);
        portugalFlag = (ImageView) view.findViewById(R.id.flag_portugal);
        polandFlag = (ImageView) view.findViewById(R.id.flag_poland);
        russiaFlag = (ImageView) view.findViewById(R.id.flag_russia);
        turkeyFlag = (ImageView) view.findViewById(R.id.flag_turkey);
        chinaFlag = (ImageView) view.findViewById(R.id.flag_china);
        japanFlag = (ImageView) view.findViewById(R.id.flag_japan);
        southKoreaFlag = (ImageView) view.findViewById(R.id.flag_south_korea);
        saudiArabiaFlag = (ImageView) view.findViewById(R.id.flag_saudi_arabia);
        indiaFlag = (ImageView) view.findViewById(R.id.flag_india);
        malaysiaFlag = (ImageView) view.findViewById(R.id.flag_malaysia);

        CornerRounder.roundImageCorners(unitedKingdomFlag, spainFlag, franceFlag, germanyFlag,
                italyFlag, netherlandsFlag, portugalFlag, polandFlag, russiaFlag, turkeyFlag,
                chinaFlag, japanFlag, southKoreaFlag, saudiArabiaFlag, indiaFlag, malaysiaFlag);
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
