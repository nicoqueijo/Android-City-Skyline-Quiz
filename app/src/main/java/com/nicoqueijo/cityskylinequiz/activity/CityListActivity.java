package com.nicoqueijo.cityskylinequiz.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.nicoqueijo.cityskylinequiz.R;
import com.nicoqueijo.cityskylinequiz.adapter.CustomAdapter;
import com.nicoqueijo.cityskylinequiz.model.City;
import com.turingtechnologies.materialscrollbar.AlphabetIndicator;
import com.turingtechnologies.materialscrollbar.DragScrollBar;

import java.util.ArrayList;

public class CityListActivity extends AppCompatActivity {

    ArrayList<City> mCities;
    RecyclerView mRecyclerCityList;
    CustomAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intentCityList = getIntent();
        mCities = (ArrayList<City>) intentCityList.getSerializableExtra("cityList");

        mRecyclerCityList = (RecyclerView) findViewById(R.id.recycler_city_list);
        mAdapter = new CustomAdapter(CityListActivity.this, mCities);
        mRecyclerCityList.setAdapter(mAdapter);
        mRecyclerCityList.setLayoutManager(new LinearLayoutManager(CityListActivity.this));
        ((DragScrollBar) findViewById(R.id.drag_scroll_bar))
                .setIndicator(new AlphabetIndicator(this), true);
    }
}
