package com.nicoqueijo.cityskylinequiz.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;

import com.nicoqueijo.cityskylinequiz.adapters.ExpandableListAdapter;
import com.nicoqueijo.cityskylinequiz.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This activity hosts an expandable list of options that serve as the different modes the game can
 * be played in.
 */
public class QuizMenuActivity extends AppCompatActivity {

    public static final int PARENT_MODE_TIMED = 0;
    public static final int PARENT_MODE_UNTIMED = 1;
    public static final int PARENT_MODE_EVERY_CITY = 2;
    public static final int CHILD_MODE_SECONDS_30 = 0;
    public static final int CHILD_MODE_SECONDS_60 = 1;
    public static final int CHILD_MODE_SECONDS_120 = 2;
    public static final int CHILD_MODE_QUESTIONS_10 = 0;
    public static final int CHILD_MODE_QUESTIONS_20 = 1;
    public static final int CHILD_MODE_QUESTIONS_50 = 2;
    public static final int CHILD_MODE_NO_FAULTS = 0;
    public static final int CHILD_MODE_FAULTS_ALLOWED = 1;

    private ActionBar mActionBar;
    private SharedPreferences mSharedPreferences;
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

        mExpandableList = (ExpandableListView) findViewById(R.id.expandable_list_view);
        fillExpandableListData();
        mExpandableListAdapter = new ExpandableListAdapter(this, mParentGameModes, mChildGameModes);
        mExpandableList.setAdapter(mExpandableListAdapter);

        mExpandableList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition,
                                        int childPosition, long id) {

                Intent intentQuizGame = new Intent(QuizMenuActivity.this, QuizGameActivity.class);
                intentQuizGame.putExtra("parentMode", groupPosition);
                intentQuizGame.putExtra("childMode", childPosition);
                startActivity(intentQuizGame);
                return true;
            }
        });
    }

    /**
     * Populates the parent and child items of the expandable list view with their appropriate strings.
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

        mChildGameModes.put(mParentGameModes.get(PARENT_MODE_TIMED), timedModeChildren);
        mChildGameModes.put(mParentGameModes.get(PARENT_MODE_UNTIMED), untimedModeChildren);
        mChildGameModes.put(mParentGameModes.get(PARENT_MODE_EVERY_CITY), everyCityModeChildren);
    }
}
