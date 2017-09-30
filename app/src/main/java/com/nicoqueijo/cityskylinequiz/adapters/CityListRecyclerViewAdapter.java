package com.nicoqueijo.cityskylinequiz.adapters;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nicoqueijo.cityskylinequiz.R;
import com.nicoqueijo.cityskylinequiz.activities.CityListActivity;
import com.nicoqueijo.cityskylinequiz.fragments.CityDetailDialog;
import com.nicoqueijo.cityskylinequiz.helpers.CornerRounder;
import com.nicoqueijo.cityskylinequiz.helpers.ResourceByNameRetriever;
import com.nicoqueijo.cityskylinequiz.models.City;
import com.squareup.picasso.Picasso;
import com.turingtechnologies.materialscrollbar.INameableAdapter;

import java.util.ArrayList;

/**
 * The Recycler View adapter for the city list. Implements INameableAdapter to get the first
 * character of the city/country in the first position of the adapter and have it displayed on the
 * ScrollBar when the user drags it.
 */
public class CityListRecyclerViewAdapter extends RecyclerView.Adapter<CityListRecyclerViewAdapter
        .ViewHolder> implements INameableAdapter {

    private SharedPreferences mSharedPreferences;
    private Context mContext;
    private ArrayList<City> mCities;
    private LayoutInflater mInflater;
    private int mPreviousPosition = 0;

    /**
     * Constructor for the RecyclerView adapter.
     *
     * @param context activity hosting the RecyclerView
     * @param cities  data set of the CityListRecyclerViewAdapter
     */
    public CityListRecyclerViewAdapter(Context context, ArrayList<City> cities) {
        this.mContext = context;
        this.mCities = cities;
        mInflater = LayoutInflater.from(context);
        mSharedPreferences = mContext.getSharedPreferences("settings", Context.MODE_PRIVATE);
    }

    /**
     * Called when RecyclerView needs a new ViewHolder of the given type to represent an item.
     *
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.card_city_detail, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    /**
     * Called by RecyclerView to display the data at the specified position. This method updates the
     * contents of the ViewHolder to reflect the item at the given position.
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        if (position > mPreviousPosition) {
            animate(holder, true);
        } else {
            animate(holder, false);
        }
        mPreviousPosition = position;

        final int CURRENT_POSITION = position;
        holder.mCountryFlagImageView.setImageResource(ResourceByNameRetriever.getDrawableResourceByName
                (mCities.get(position).getCountryName(), mContext));
        CornerRounder.roundImageCorners(holder.mCountryFlagImageView);
        holder.mCityTextView.setText(ResourceByNameRetriever.getStringResourceByName(mCities.
                get(position).getCityName(), mContext));
        Picasso.with(mContext).load(mCities.get(position).getImageUrl()).fetch();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CityDetailDialog cityDetailDialog = new CityDetailDialog();
                Bundle args = new Bundle();
                args.putSerializable("city", mCities.get(CURRENT_POSITION));
                cityDetailDialog.setArguments(args);
                cityDetailDialog.show(((FragmentActivity) mContext).getSupportFragmentManager(),
                        "dialog_city");
            }
        });
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return mCities.size();
    }

    /**
     * Retrieves the first character of the element in the current position of the adapter. If the
     * list is being sorted by city name it gets the first character of the city name, else it is
     * being sorted by country name so it gets the first character of the country name.
     *
     * @param position of the adapter that should be titled.
     * @return The character that the AlphabetIndicator should display for the corresponding element.
     */
    @Override
    public Character getCharacterForElement(int position) {
        Character firstCharacter = ' ';
        int sortMode = mSharedPreferences.getInt("sort_mode", CityListActivity.CITY_SORT);
        if (sortMode == CityListActivity.CITY_SORT) {
            firstCharacter = mCities.get(position).getCityNameInCurrentLanguage().charAt(0);
        } else if (sortMode == CityListActivity.COUNTRY_SORT) {
            firstCharacter = mCities.get(position).getCountryNameInCurrentLanguage().charAt(0);
        }
        return firstCharacter;
    }

    /**
     * Animates the holder being bound by shifting it up or down 250 pixels for a duration of half
     * a second depending on whether the user is scrolling up or down.
     *
     * @param holder    the current holder to animate
     * @param goingDown whether user is scrolling down
     */
    private void animate(RecyclerView.ViewHolder holder, boolean goingDown) {
        final int ORIGINAL_POSITION = 0;
        final int DOWNSCROLL_STARTING_POSITION = 250;
        final int UPSCROLL_STARTING_POSITION = -250;
        final int HALF_SECOND = 500;
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator animatorTranslateY = ObjectAnimator.ofFloat(holder.itemView, "translationY",
                goingDown ? DOWNSCROLL_STARTING_POSITION : UPSCROLL_STARTING_POSITION,
                ORIGINAL_POSITION);
        animatorTranslateY.setDuration(HALF_SECOND);
        animatorSet.playTogether(animatorTranslateY);
        animatorSet.start();
    }

    /**
     * A ViewHolder describes an item view and metadata about its place within the RecyclerView.
     */
    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mCountryFlagImageView;
        TextView mCityTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            mCountryFlagImageView = (ImageView) itemView.findViewById(R.id.flag_image);
            mCityTextView = (TextView) itemView.findViewById(R.id.city_name);
        }
    }
}
