package com.nicoqueijo.cityskylinequiz.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.nicoqueijo.cityskylinequiz.R;
import com.nicoqueijo.cityskylinequiz.model.City;
import com.squareup.picasso.Picasso;

/**
 *
 */
public class CityDetailDialog extends DialogFragment {

    private City mCity;
    private ImageView mImageFlag;
    private TextView mTextCity;
    private ImageView mImageCity;
    private ImageButton mButtonGoogleMaps;
    private ImageButton mButtonWikipedia;

    public CityDetailDialog() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
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
     *
     */
    private void adjustWindowSize() {
        final double WIDTH_PERCENTAGE = 0.90;
        final double HEIGHT_PERCENTAGE = 0.65;
        int width = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels;
        int newWidth = (int) (WIDTH_PERCENTAGE * (double) width);
        int newHeight = (int) (HEIGHT_PERCENTAGE * (double) height);
        // REMOVE THESE LOG STATEMENTS BEFORE DEPLOYING
        Log.v("Dimensions", "Original width: " + width + "");
        Log.v("Dimensions", "Original height: " + height + "");
        Log.v("Dimensions", "New width: " + newWidth + "");
        Log.v("Dimensions", "New height: " + newHeight + "");
        ///////////////////////////////////////////////
        getDialog().getWindow().setLayout(newWidth, newHeight);
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
