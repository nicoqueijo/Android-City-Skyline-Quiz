package com.nicoqueijo.cityskylinequiz.activity;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Switch;

import com.nicoqueijo.cityskylinequiz.R;
import com.nicoqueijo.cityskylinequiz.fragment.LanguageChooserDialog;

public class SettingsActivity extends AppCompatActivity {

    AppCompatActivity mThisActivity = SettingsActivity.this;
    FragmentManager mFragmentManager = getFragmentManager();
    private SharedPreferences mSharedPreferences;
    private LinearLayout mThemeView;
    private LinearLayout mLanguageView;
    private LinearLayout mScoresView;
    private Switch mThemeSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSharedPreferences = getSharedPreferences("settings", Context.MODE_PRIVATE);
        setTheme(mSharedPreferences.getInt("theme", R.style.AppThemeLight));
        setContentView(R.layout.activity_settings);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mThemeView = (LinearLayout) findViewById(R.id.container_settings_theme);
        mLanguageView = (LinearLayout) findViewById(R.id.container_settings_language);
        mScoresView = (LinearLayout) findViewById(R.id.container_settings_scores);
        mThemeSwitch = (Switch) findViewById(R.id.switch_theme);
        mThemeSwitch.setChecked(mSharedPreferences.getInt("theme", R.style.AppThemeLight) == R.style.AppThemeDark);

        mThemeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleThemeSwitch();
                saveTheme();
                restartActivity();
            }
        });

        mThemeSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveTheme();
                restartActivity();
            }
        });

        mLanguageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LanguageChooserDialog languageChooserDialog = new LanguageChooserDialog();
                languageChooserDialog.show(mFragmentManager, "Open Dialog");
            }
        });

        mScoresView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // open dialog fragment to confirm scores reset
                // show toast if user chose to clear scores
            }
        });
    }

    /**
     * Finishes and then starts the activity with a new intent so onCreate gets called and as a
     * result the new theme is applied.
     */
    public void restartActivity() {
        mThisActivity.finish();
        final Intent intent = mThisActivity.getIntent();
        mThisActivity.startActivity(intent);
    }

    /**
     * Toggles the status of the theme switch so click listeners of other views can manipulate the
     * theme switch.
     */
    private void toggleThemeSwitch() {
        mThemeSwitch.setChecked(!mThemeSwitch.isChecked());
    }

    /**
     * Saves the theme status on mSharedPreferences according to the status of the theme switch. If
     * theme switch is checked, it saved the theme as dark. Else it saves the theme as light.
     */
    public void saveTheme() {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        if (mThemeSwitch.isChecked()) {
            editor.putInt("theme", R.style.AppThemeDark);
            editor.commit();
        } else {
            editor.putInt("theme", R.style.AppThemeLight);
            editor.commit();
        }
    }
}
