package com.nicoqueijo.cityskylinequiz.adapter;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nicoqueijo.cityskylinequiz.R;
import com.nicoqueijo.cityskylinequiz.activity.MainMenuActivity;
import com.nicoqueijo.cityskylinequiz.fragment.CityDetailDialog;
import com.nicoqueijo.cityskylinequiz.model.City;
import com.squareup.picasso.Picasso;
import com.turingtechnologies.materialscrollbar.INameableAdapter;

import java.util.ArrayList;

/**
 *
 */
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> implements
        INameableAdapter {

    private Context mContext;
    private ArrayList<City> mCities;
    private LayoutInflater mInflater;
    private int mPreviousPosition = 0;

    public CustomAdapter(Context context, ArrayList<City> cities) {
        this.mContext = context;
        this.mCities = cities;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.card_city_detail, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        if (position > mPreviousPosition) {
            animate(holder, true);
        } else {
            animate(holder, false);
        }
        mPreviousPosition = position;

        final int CURRENT_POSITION = position;
        holder.mCountryFlagImageView.setImageResource(getDrawableResourceByName
                (mCities.get(position).getCountryName()));
        if (MainMenuActivity.isRunningLollipopOrHigher()) {
            holder.mCountryFlagImageView.setClipToOutline(true);
        } else {
            // Sorry, can only round corners on devices running Lollipop or higher :(
        }
        holder.mCityTextView.setText(getStringResourceByName(mCities.get(position).getCityName()));
        Picasso.with(mContext).load(mCities.get(position).getImageUrl()).fetch();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CityDetailDialog cityDetailDialog = new CityDetailDialog();
                Bundle args = new Bundle();
                args.putSerializable("city", mCities.get(CURRENT_POSITION));
                cityDetailDialog.setArguments(args);
                cityDetailDialog.show(((FragmentActivity) mContext).getSupportFragmentManager(),
                        "Open Dialog");
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCities.size();
    }

    /**
     * Retrieves string resources using a String instead of an int.
     *
     * @param name name of the string resource
     * @return the string resource
     */
    private String getStringResourceByName(String name) {
        int resId = mContext.getResources().getIdentifier(name, "string", mContext.getPackageName());
        return mContext.getString(resId);
    }

    /**
     * Retrieves drawable resources using a String instead of an int.
     *
     * @param name name of the drawable resource
     * @return the drawable resource id
     */
    private int getDrawableResourceByName(String name) {
        return mContext.getResources().getIdentifier(name, "drawable", mContext.getPackageName());
    }

    /**
     * Retrieves the first character of the element in the current position of the adapter.
     *
     * @param position of the adapter that should be titled.
     * @return The character that the AlphabetIndicator should display for the corresponding element.
     */
    @Override
    public Character getCharacterForElement(int position) {
        Character c = mCities.get(position).getCityName().charAt(0);
        if (Character.isDigit(c)) {
            c = '#';
        }
        return c;
    }

    /**
     * Animates the holder being binded by shifting it up or down 250 pixels for a duration of half
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
     *
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
