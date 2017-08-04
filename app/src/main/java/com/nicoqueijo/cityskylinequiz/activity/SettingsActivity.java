package com.nicoqueijo.cityskylinequiz.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

import com.nicoqueijo.cityskylinequiz.R;

public class SettingsActivity extends AppCompatActivity {

    AppCompatActivity thisActivity = SettingsActivity.this;
    private SharedPreferences sharedPreferences;
    private LinearLayout mThemeView;
    private LinearLayout mLanguageView;
    private LinearLayout mScoresView;
    private Switch mThemeSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences("settings", Context.MODE_PRIVATE);
        setTheme(sharedPreferences.getInt("theme", R.style.AppThemeLight));
        setContentView(R.layout.activity_settings);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mThemeView = (LinearLayout) findViewById(R.id.container_settings_theme);
        mLanguageView = (LinearLayout) findViewById(R.id.container_settings_language);
        mScoresView = (LinearLayout) findViewById(R.id.container_settings_scores);
        mThemeSwitch = (Switch) findViewById(R.id.switch_theme);
        mThemeSwitch.setChecked(sharedPreferences.getInt("theme", R.style.AppThemeDark) == R.style.AppThemeDark);

        mThemeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleThemeSwitch();
                loadTheme();
                thisActivity.recreate();
            }
        });

        mThemeSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadTheme();
                thisActivity.recreate();
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
    }

    private void toggleThemeSwitch() {
        mThemeSwitch.setChecked(!mThemeSwitch.isChecked());
    }


    public void loadTheme() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (mThemeSwitch.isChecked()) {
            editor.putInt("theme", R.style.AppThemeDark);
            editor.commit();
        } else {
            editor.putInt("theme", R.style.AppThemeLight);
            editor.commit();
        }
    }

    public void saveLanguage(View view) {

    }
}
