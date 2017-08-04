package com.nicoqueijo.cityskylinequiz.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nicoqueijo.cityskylinequiz.R;
import com.nicoqueijo.cityskylinequiz.helper.ApiChecker;
import com.nicoqueijo.cityskylinequiz.model.City;
import com.squareup.picasso.Picasso;

/**
 *
 */
public class CityDetailDialog extends DialogFragment {

    private SharedPreferences sharedPreferences;
    private City mCity;
    private ImageView mImageFlag;
    private TextView mTextCity;
    private ImageView mImageCity;
    private ImageButton mButtonGoogleMaps;
    private ImageButton mButtonWikipedia;
    private TextView mMoreInfoTextView;

    public CityDetailDialog() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        sharedPreferences = getActivity().getSharedPreferences("settings", Context.MODE_PRIVATE);
        return inflater.inflate(R.layout.dialog_city_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        removeTitleBar();
        mCity = (City) getArguments().getSerializable("city");
        mImageFlag = (ImageView) view.findViewById(R.id.flag_image);
        mTextCity = (TextView) view.findViewById(R.id.city_name);
        mImageCity = (ImageView) view.findViewById(R.id.city_image);
        mButtonGoogleMaps = (ImageButton) view.findViewById(R.id.button_google_maps);
        mButtonWikipedia = (ImageButton) view.findViewById(R.id.button_wikipedia);
        mMoreInfoTextView = (TextView) view.findViewById(R.id.more_info_label);

        if (sharedPreferences.getInt("theme", R.style.AppThemeLight) == R.style.AppThemeDark) {
            mMoreInfoTextView.setBackgroundColor(getResources().getColor(R.color.darkBackground));
        } else {
            mMoreInfoTextView.setBackgroundColor(getResources().getColor(R.color.lightBackground));
        }

        if (isRunningOnTablet()) {
            mImageCity.setScaleType(ImageView.ScaleType.FIT_CENTER);
        }

        if (ApiChecker.isRunningLollipopOrHigher()) {
            mImageCity.setClipToOutline(true);
            mImageFlag.setClipToOutline(true);
        } else {
            // Sorry, can only round corners on devices running Lollipop or higher :(
        }

        mImageFlag.setImageResource(getDrawableResourceByName(mCity.getCountryName()));
        mTextCity.setText(getStringResourceByName(mCity.getCityName()));
        Picasso.with(getActivity()).load(mCity.getImageUrl()).into(mImageCity);

        mButtonGoogleMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mapsIntent = new Intent(Intent.ACTION_VIEW);
                mapsIntent.setData(Uri.parse("geo:" + mCity.getCoordinates()));
                Intent chooser = Intent.createChooser(mapsIntent, getString(R.string.launch_maps));
                startActivity(chooser);
            }
        });

        mButtonWikipedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent wikiIntent = new Intent(Intent.ACTION_VIEW);
                wikiIntent.setData(Uri.parse(mCity.getWikiUrl()));
                Intent chooser = Intent.createChooser(wikiIntent, getString(R.string.launch_browser));
                startActivity(chooser);
            }
        });
    }

    /**
     * Overrides the class's onStart() method so the window size could be adjusted at run-time.
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
     * Sets the dialog fragment's window height to 65% of the device's screen height.
     */
    private void adjustWindowSize() {
        final double WIDTH_PERCENTAGE = 0.92;
        final double HEIGHT_PERCENTAGE = 0.65;
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
    public boolean isRunningOnTablet() {
        final double MINIMUM_ASPECT_RATIO_OF_A_PHONE = 1.6;
        return getAspectRatio() < MINIMUM_ASPECT_RATIO_OF_A_PHONE;
    }

    /**
     * Retrieves string resources using a String instead of an int.
     *
     * @param name name of the string resource
     * @return the string resource
     */
    private String getStringResourceByName(String name) {
        int resId = getActivity().getResources().getIdentifier(name, "string", getActivity().getPackageName());
        return getActivity().getString(resId);
    }

    /**
     * Retrieves drawable resources using a String instead of an int.
     *
     * @param name name of the drawable resource
     * @return the drawable resource id
     */
    private int getDrawableResourceByName(String name) {
        return getActivity().getResources().getIdentifier(name, "drawable", getActivity().getPackageName());
    }
}
