package com.nicoqueijo.cityskylinequiz.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;

import com.nicoqueijo.cityskylinequiz.adapter.ExpandableListAdapter;
import com.nicoqueijo.cityskylinequiz.R;
import com.nicoqueijo.cityskylinequiz.model.City;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuizMenuActivity extends AppCompatActivity {

    private ActionBar mActionBar;
    private SharedPreferences mSharedPreferences;
    private ArrayList<City> mCities;
    private ExpandableListView mExpandableList;
    private ExpandableListAdapter mExpandableListAdapter;
    private List<Integer> mParentGameModes;
    private Map<Integer, List<Integer>> mChildGameModes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSharedPreferences = getSharedPreferences("settings", Context.MODE_PRIVATE);
        setTheme(mSharedPreferences.getInt("theme", R.style.AppThemeLight));
        setContentView(R.layout.activity_menu_quiz);
        mActionBar = getSupportActionBar();
        mActionBar.setDisplayShowHomeEnabled(true);
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setIcon(R.drawable.ic_light_game);
        mActionBar.setTitle(R.string.actionbar_play_game);

        Intent intentPlayGame = getIntent();
        mCities = (ArrayList<City>) intentPlayGame.getSerializableExtra("cityList");

        mExpandableList = (ExpandableListView) findViewById(R.id.expandable_list_view);
        fillExpandableListData();
        mExpandableListAdapter = new ExpandableListAdapter(this, mParentGameModes, mChildGameModes);
        mExpandableList.setAdapter(mExpandableListAdapter);
    }

    /**
     * JAVADOC THIS @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
     */
    private void fillExpandableListData() {
        mParentGameModes = new ArrayList<>();
        mChildGameModes = new HashMap<>();

        mParentGameModes.add(R.string.timed);
        mParentGameModes.add(R.string.untimed);
        mParentGameModes.add(R.string.every_city);

        List<Integer> timedModeChildren = new ArrayList<>();
        List<Integer> untimedModeChildren = new ArrayList<>();
        List<Integer> everyCityModeChildren = new ArrayList<>();

        timedModeChildren.add(R.string.seconds_30);
        timedModeChildren.add(R.string.seconds_60);
        timedModeChildren.add(R.string.seconds_120);

        untimedModeChildren.add(R.string.questions_10);
        untimedModeChildren.add(R.string.questions_20);
        untimedModeChildren.add(R.string.questions_50);

        everyCityModeChildren.add(R.string.no_faults);
        everyCityModeChildren.add(R.string.faults_allowed);

        mChildGameModes.put(mParentGameModes.get(0), timedModeChildren);
        mChildGameModes.put(mParentGameModes.get(1), untimedModeChildren);
        mChildGameModes.put(mParentGameModes.get(2), everyCityModeChildren);
    }
}
