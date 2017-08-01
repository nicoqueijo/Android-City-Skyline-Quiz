package com.nicoqueijo.cityskylinequiz.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Switch;

import com.nicoqueijo.cityskylinequiz.R;

public class SettingsActivity extends AppCompatActivity {

    private LinearLayout mThemeView;
    private LinearLayout mLanguageView;
    private LinearLayout mScoresView;
    private Switch mThemeSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mThemeView = (LinearLayout) findViewById(R.id.container_settings_theme);
        mLanguageView = (LinearLayout) findViewById(R.id.container_settings_language);
        mScoresView = (LinearLayout) findViewById(R.id.container_settings_scores);
        mThemeSwitch = (Switch) findViewById(R.id.switch_theme);

        mThemeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mThemeSwitch.setChecked(!mThemeSwitch.isChecked());
            }
        });

        mLanguageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // open a dialog fragment to change language
            }
        });

        mScoresView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // open dialog fragment to confirm scores reset
            }
        });

        mThemeSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // do the same as themeview listener
            }
        });
    }
}
