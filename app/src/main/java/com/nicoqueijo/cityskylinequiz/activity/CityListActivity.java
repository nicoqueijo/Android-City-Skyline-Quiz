package com.nicoqueijo.cityskylinequiz.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.nicoqueijo.cityskylinequiz.R;
import com.nicoqueijo.cityskylinequiz.adapter.CustomAdapter;
import com.nicoqueijo.cityskylinequiz.fragment.LanguageChooserDialog;
import com.nicoqueijo.cityskylinequiz.model.City;
import com.turingtechnologies.materialscrollbar.AlphabetIndicator;
import com.turingtechnologies.materialscrollbar.DragScrollBar;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;

public class CityListActivity extends AppCompatActivity {

    public static final int CITY_SORT = 0;
    public static final int COUNTRY_SORT = 1;

    private ActionBar mActionBar;
    private SharedPreferences mSharedPreferences;
    private ArrayList<City> mCities;
    private RecyclerView mRecyclerCityList;
    private DragScrollBar mDragScrollBar;
    private CustomAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSharedPreferences = getSharedPreferences("settings", Context.MODE_PRIVATE);
        setTheme(mSharedPreferences.getInt("theme", R.style.AppThemeLight));
        setContentView(R.layout.activity_city_list);
        mActionBar = getSupportActionBar();
        mActionBar.setDisplayShowHomeEnabled(true);
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setIcon(R.drawable.ic_light_city);
        mActionBar.setTitle(R.string.actionbar_city_list);

        Intent intentCityList = getIntent();
        mCities = (ArrayList<City>) intentCityList.getSerializableExtra("cityList");
        sortCities(mSharedPreferences.getInt("sort_mode", CITY_SORT));

        mRecyclerCityList = (RecyclerView) findViewById(R.id.recycler_city_list);
        mDragScrollBar = (DragScrollBar) findViewById(R.id.drag_scroll_bar);
        mAdapter = new CustomAdapter(CityListActivity.this, mCities);
        mRecyclerCityList.setAdapter(mAdapter);
        mRecyclerCityList.setLayoutManager(new LinearLayoutManager(CityListActivity.this));
        mDragScrollBar.setIndicator(new AlphabetIndicator(this), true);
    }

    /**
     * Creates a hamburger-style menu with options to sort the list. Sets the sort mode to the last
     * selected mode or to sort by city by default.
     *
     * @param menu The menu to be created.
     * @return Status of the operation.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        final int CITY_SORT_MENU_POSITION = 1;
        final int COUNTRY_SORT_MENU_POSITION = 2;
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_list_sort, menu);
        int sortMode = mSharedPreferences.getInt("sort_mode", CITY_SORT);
        switch (sortMode) {
            case CITY_SORT:
                menu.getItem(CITY_SORT_MENU_POSITION).setChecked(true);
                break;
            case COUNTRY_SORT:
                menu.getItem(COUNTRY_SORT_MENU_POSITION).setChecked(true);
                break;
        }
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Sorts the list of cities by either city name or country name, refreshes the recycler view to
     * show the changes, and saves the sorting mode in the sharedPreferences.
     *
     * @param item The menu item being selected.
     * @return Status of the operation.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        switch (item.getItemId()) {
            case (R.id.menu_item_sort_city):
                item.setChecked(true);
                sortCities(CITY_SORT);
                mAdapter.notifyDataSetChanged();
                editor.putInt("sort_mode", CITY_SORT);
                editor.commit();
                break;
            case (R.id.menu_item_sort_country):
                item.setChecked(true);
                sortCities(COUNTRY_SORT);
                mAdapter.notifyDataSetChanged();
                editor.putInt("sort_mode", COUNTRY_SORT);
                editor.commit();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Sorts the list of cities using a Collator for the locale. If the sorting mode is by city,
     * we using the city name fields of the city objects to sort the list, otherwise the sorting
     * mode is country and we use the country name fields to sort.
     *
     * @param sortMode the mode to sort by (either by city or country)
     */
    private void sortCities(final int sortMode) {
        final String currentLanguage = mSharedPreferences
                .getString("language", LanguageChooserDialog.Language.en.name());
        Collections.sort(mCities, new Comparator<City>() {
            Collator collator = Collator.getInstance(new Locale(currentLanguage));

            public int compare(City city1, City city2) {
                if (sortMode == CITY_SORT) {
                    return collator.compare(city1.getCityNameInCurrentLanguage(),
                            city2.getCityNameInCurrentLanguage());
                } else {
                    return collator.compare(city1.getCountryNameInCurrentLanguage(),
                            city2.getCountryNameInCurrentLanguage());
                }
            }
        });
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
