package com.nicoqueijo.cityskylinequiz.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nicoqueijo.cityskylinequiz.R;
import com.nicoqueijo.cityskylinequiz.model.City;
import com.turingtechnologies.materialscrollbar.INameableAdapter;

import java.util.ArrayList;

/**
 *
 */
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> implements INameableAdapter {

    Context mContext;
    ArrayList<City> mCities;
    LayoutInflater mInflater;

    /**
     * @param context
     * @param cities
     */
    public CustomAdapter(Context context, ArrayList<City> cities) {
        this.mContext = context;
        this.mCities = cities;
        mInflater = LayoutInflater.from(context);
    }

    /**
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.card_city_detail, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    /**
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mCityTextView.setText(getStringResourceByName(mCities.get(position).getCityName()));
        holder.mCountryFlagImageView.setImageResource(getDrawableResourceByName
                (mCities.get(position).getCountryName()));
        // popup a dialog with title and image in foreground, can click anywhere to dismiss it
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    /**
     * @return
     */
    @Override
    public int getItemCount() {
        return mCities.size();
    }

    /**
     * Retrieves String resources using a String instead of an int.
     *
     * @param name name of the String resource
     * @return the String resource
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
     * @param element of the adapter that should be titled.
     * @return The character that the AlphabetIndicator should display for the corresponding element.
     */
    @Override
    public Character getCharacterForElement(int element) {
        Character c = mCities.get(element).getCityName().charAt(0);
        if(Character.isDigit(c)){
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

        /**
         * @param itemView
         */
        public ViewHolder(View itemView) {
            super(itemView);

            mCountryFlagImageView = (ImageView) itemView.findViewById(R.id.flag_icon);
            mCityTextView = (TextView) itemView.findViewById(R.id.city_name);
        }
    }
}
