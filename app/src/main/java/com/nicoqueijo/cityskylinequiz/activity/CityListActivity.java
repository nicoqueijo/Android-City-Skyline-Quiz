package com.nicoqueijo.cityskylinequiz.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.nicoqueijo.cityskylinequiz.R;
import com.nicoqueijo.cityskylinequiz.adapter.CustomAdapter;
import com.nicoqueijo.cityskylinequiz.fragment.LanguageChooserDialog;
import com.nicoqueijo.cityskylinequiz.model.City;
import com.turingtechnologies.materialscrollbar.AlphabetIndicator;
import com.turingtechnologies.materialscrollbar.DragScrollBar;
import com.turingtechnologies.materialscrollbar.MaterialScrollBar;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;

public class CityListActivity extends AppCompatActivity {

    private SharedPreferences mSharedPreferences;
    ArrayList<City> mCities;
    RecyclerView mRecyclerCityList;
    DragScrollBar mDragScrollBar;
    CustomAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSharedPreferences = getSharedPreferences("settings", Context.MODE_PRIVATE);
        setTheme(mSharedPreferences.getInt("theme", R.style.AppThemeLight));
        setContentView(R.layout.activity_city_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.city_list);

        Intent intentCityList = getIntent();
        mCities = (ArrayList<City>) intentCityList.getSerializableExtra("cityList");
        Collections.sort(mCities, new Comparator<City>() {
            public int compare(City o1, City o2) {
                Collator collator = Collator.getInstance(Locale.JAPAN);
                return collator.compare(o1.getCityNameInCurrentLanguage(), o2.getCityNameInCurrentLanguage());
            }
        });

//        Collator coll = Collator.getInstance(new Locale(mSharedPreferences
//                .getString("language", LanguageChooserDialog.Language.en.name())));
//        Collections.sort(mCities, coll);

        mRecyclerCityList = (RecyclerView) findViewById(R.id.recycler_city_list);
        mDragScrollBar = (DragScrollBar) findViewById(R.id.drag_scroll_bar);
        mAdapter = new CustomAdapter(CityListActivity.this, mCities);
        mRecyclerCityList.setAdapter(mAdapter);
        mRecyclerCityList.setLayoutManager(new LinearLayoutManager(CityListActivity.this));
        ((DragScrollBar) findViewById(R.id.drag_scroll_bar))
                .setIndicator(new AlphabetIndicator(this), true);
    }

//    CODE TO SAVE AND RESTORE RECYCLERVIEW POSITION
//
//    @Override
//    public void onPause() {
//        super.onPause();
//        // Save the current position of the recycler view
//        int lastFirstVisiblePosition = ((LinearLayoutManager) mRecyclerCityList.getLayoutManager()).
//                findFirstCompletelyVisibleItemPosition();
//        SharedPreferences.Editor editor = mSharedPreferences.edit();
//        editor.putInt("recycler_adapter_position", lastFirstVisiblePosition);
//        editor.commit();
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        // Restore the recycler view to the previous position
//        int lastFirstVisiblePosition = mSharedPreferences.getInt("recycler_adapter_position", 0);
//        mRecyclerCityList.getLayoutManager().
//                scrollToPosition(lastFirstVisiblePosition);
//    }
}
