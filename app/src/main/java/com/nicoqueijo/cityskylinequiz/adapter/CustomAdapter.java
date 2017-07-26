package com.nicoqueijo.cityskylinequiz.adapter;

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
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> implements INameableAdapter {

    private Context mContext;
    private ArrayList<City> mCities;
    private LayoutInflater mInflater;

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
                cityDetailDialog.show(((FragmentActivity) mContext).getSupportFragmentManager(), "Open Dialog");
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
