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

    private City city;
    private ImageView mImageFlag;
    private TextView mTextCity;
    private ImageView mImageCity;
    private ImageButton mButtonGoogleMaps;
    private ImageButton mButtonWikipedia;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_city_detail, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        city = (City) getArguments().getSerializable("city");
        mImageFlag = (ImageView) view.findViewById(R.id.flag_image);
        mTextCity = (TextView) view.findViewById(R.id.city_name);
        mImageCity = (ImageView) view.findViewById(R.id.city_image);
        mButtonGoogleMaps = (ImageButton) view.findViewById(R.id.button_google_maps);
        mButtonWikipedia = (ImageButton) view.findViewById(R.id.button_wikipedia);

        mImageFlag.setImageResource(getDrawableResourceByName(city.getCountryName()));
        mTextCity.setText(getStringResourceByName(city.getCityName()));
        Picasso.with(getActivity()).load(city.getImageUrl()).into(mImageCity);

        mButtonGoogleMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mapsIntent = new Intent(Intent.ACTION_VIEW);
                mapsIntent.setData(Uri.parse("geo:" + city.getCoordinates()));
                Intent chooser = Intent.createChooser(mapsIntent, getString(R.string.launch_maps));
                startActivity(chooser);
            }
        });

        mButtonWikipedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent wikiIntent = new Intent(Intent.ACTION_VIEW);
                wikiIntent.setData(Uri.parse(city.getWikiUrl()));
                Intent chooser = Intent.createChooser(wikiIntent, getString(R.string.launch_browser));
                startActivity(chooser);
            }
        });
    }

    @Override
    public void onStart() {

        // Move all this code to its own method and call it from here
        final double WIDTH_PERCENTAGE = 0.90;
        final double HEIGHT_PERCENTAGE = 0.65;
        super.onStart();
        int width = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels;
        int newWidth = (int) (WIDTH_PERCENTAGE * (double) width);
        int newHeight = (int) (HEIGHT_PERCENTAGE * (double) height);
        Log.v("Dimensions: ", "Width: " + newWidth + "");
        Log.v("Dimensions: ", "Height: " + newHeight + "");
        getDialog().getWindow().setLayout(newWidth, newHeight);
    }

    /**
     * Retrieves String resources using a String instead of an int.
     *
     * @param name name of the String resource
     * @return the String resource
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
