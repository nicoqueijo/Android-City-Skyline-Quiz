package com.nicoqueijo.cityskylinequiz.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.nicoqueijo.cityskylinequiz.R;
import com.nicoqueijo.cityskylinequiz.helpers.CornerRounder;
import com.nicoqueijo.cityskylinequiz.helpers.ResourceByNameRetriever;
import com.nicoqueijo.cityskylinequiz.models.City;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

/**
 * This is the DialogFragment that provides the details of the city selected by the user in the
 * RecyclerView.
 */
public class CityDetailDialog extends DialogFragment {

    private City mCity;
    private ImageView mImageFlag;
    private TextView mTextCity;
    private ImageView mImageCity;
    private ImageButton mButtonGoogleMaps;
    private ImageButton mButtonWikipedia;
    private ProgressBar mImageProgressBar;

    /**
     * Empty constructor required for DialogFragment.
     */
    public CityDetailDialog() {
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
        return inflater.inflate(R.layout.dialog_city_detail, container, false);
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
        removeTitleBar();
        mCity = (City) getArguments().getSerializable("city");
        mImageFlag = (ImageView) view.findViewById(R.id.flag_image);
        mTextCity = (TextView) view.findViewById(R.id.city_name);
        mImageCity = (ImageView) view.findViewById(R.id.city_image);
        mButtonGoogleMaps = (ImageButton) view.findViewById(R.id.button_google_maps);
        mButtonWikipedia = (ImageButton) view.findViewById(R.id.button_wikipedia);
        mImageProgressBar = (ProgressBar) view.findViewById(R.id.image_progress_bar);

        populateViews();
        CornerRounder.roundImageCorners(mImageFlag, mImageCity);

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
     * Overrides the class's onStart method so the window size could be adjusted at run-time.
     */
    @Override
    public void onStart() {
        super.onStart();
        adjustWindowSize();
    }

    /**
     * Fills the views of this Dialog Fragment with the content of the city passed. This includes
     * the country flag image, the city image, and the city name label.
     */
    private void populateViews() {
        mImageFlag.setImageResource(ResourceByNameRetriever.getDrawableResourceByName(mCity.
                getCountryName(), getActivity()));
        mTextCity.setText(ResourceByNameRetriever.getStringResourceByName(mCity.getCityName(),
                getActivity()));
        Picasso.with(getActivity()).load(mCity.getImageUrl()).into(mImageCity, new Callback() {
            @Override
            public void onSuccess() {
                mImageProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError() {
                Toast.makeText(getActivity(), getResources().getString
                        (R.string.error_image_load_message), Toast.LENGTH_SHORT).show();
                Toast.makeText(getActivity(), getResources().getString
                        (R.string.internet_connection_message), Toast.LENGTH_LONG).show();
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
    private boolean isRunningOnTablet() {
        final double MINIMUM_ASPECT_RATIO_OF_A_PHONE = 1.6;
        return getAspectRatio() < MINIMUM_ASPECT_RATIO_OF_A_PHONE;
    }
}
