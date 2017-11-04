package com.nicoqueijo.cityskylinequiz.activities;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

import com.nicoqueijo.cityskylinequiz.R;
import com.nicoqueijo.cityskylinequiz.fragments.ClearScoresDialog;
import com.nicoqueijo.cityskylinequiz.fragments.LanguageChooserDialog;
import com.nicoqueijo.cityskylinequiz.interfaces.Communicator;

/**
 * This is the settings activity where the user can change the app configurations. This includes
 * changing the theme, toggling the vibration, changing the regions, changing the language,
 * and resetting the scores.
 * Implements Communicator to receive a message back from a DialogFragment and act accordingly.
 */
public class SettingsActivity extends AppCompatActivity implements Communicator {

    private AppCompatActivity mThisActivity = SettingsActivity.this;
    private ActionBar mActionBar;
    private FragmentManager mFragmentManager = getFragmentManager();
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private LinearLayout mThemeView;
    private LinearLayout mVibrationView;
    private LinearLayout mRegionsView;
    private LinearLayout mLanguageView;
    private LinearLayout mScoresView;
    private Switch mThemeSwitch;
    private Switch mVibrationSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSharedPreferences = getSharedPreferences("settings", Context.MODE_PRIVATE);
        setTheme(mSharedPreferences.getInt("theme", R.style.AppThemeLight));
        setContentView(R.layout.activity_settings);
        mActionBar = getSupportActionBar();
        mActionBar.setDisplayShowHomeEnabled(true);
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setIcon(R.drawable.ic_light_settings);
        mActionBar.setTitle(R.string.actionbar_settings);

        mThemeView = (LinearLayout) findViewById(R.id.container_settings_theme);
        mVibrationView = (LinearLayout) findViewById(R.id.container_settings_vibration);
        mRegionsView = (LinearLayout) findViewById(R.id.container_regions_list);
        mLanguageView = (LinearLayout) findViewById(R.id.container_settings_language);
        mScoresView = (LinearLayout) findViewById(R.id.container_settings_scores);
        mThemeSwitch = (Switch) findViewById(R.id.switch_theme);
        mVibrationSwitch = (Switch) findViewById(R.id.switch_vibration);

        mThemeSwitch.setChecked(mSharedPreferences.getInt("theme", R.style.AppThemeLight)
                == R.style.AppThemeDark);
        mVibrationSwitch.setChecked(mSharedPreferences.getBoolean("vibration", true));

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

        mVibrationView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleVibrationSwitch();
                saveVibration();
            }
        });

        mVibrationSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveVibration();
            }
        });

        mRegionsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mLanguageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LanguageChooserDialog languageChooserDialog = new LanguageChooserDialog();
                languageChooserDialog.show(mFragmentManager, "dialog_language");
            }
        });

        mScoresView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearScoresDialog clearScoresDialog = new ClearScoresDialog();
                clearScoresDialog.show(mFragmentManager, "dialog_scores");
            }
        });
    }

    /**
     * When the user selects an option in a DialogFragment without cancelling this method executes
     * passing back the result of the option the user selected. If the user confirmed to clearing
     * the game's scores it does that, otherwise the user set the app to a new language and it
     * restarts the activity to load the layout in the language the user selected.
     *
     * @param message the result returned from opening a DialogFragment.
     */
    @Override
    public void onDialogMessage(String message) {
        if (message.equals(ClearScoresDialog.CLEAR_SCORES)) {
            mEditor = mSharedPreferences.edit();
            mEditor.remove("high_score_30_seconds").commit();
            mEditor.remove("high_score_60_seconds").commit();
            mEditor.remove("high_score_120_seconds").commit();
            mEditor.remove("high_score_10_questions").commit();
            mEditor.remove("high_score_20_questions").commit();
            mEditor.remove("high_score_50_questions").commit();
            mEditor.remove("high_score_every_city_no_faults").commit();
            mEditor.remove("high_score_every_city_faults_allowed").commit();
            Toast.makeText(mThisActivity, R.string.scores_cleared, Toast.LENGTH_SHORT).show();
        } else {
            restartActivity();
        }
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
     * Toggles the status of the vibration switch so click listeners of other views can manipulate
     * the vibration switch.
     */
    private void toggleVibrationSwitch() {
        mVibrationSwitch.setChecked(!mVibrationSwitch.isChecked());
    }

    /**
     * Saves the theme status on mSharedPreferences according to the status of the theme switch. If
     * theme switch is checked, it saved the theme as dark. Else it saves the theme as light.
     */
    private void saveTheme() {
        mEditor = mSharedPreferences.edit();
        if (mThemeSwitch.isChecked()) {
            mEditor.putInt("theme", R.style.AppThemeDark);
            mEditor.commit();
        } else {
            mEditor.putInt("theme", R.style.AppThemeLight);
            mEditor.commit();
        }
    }

    /**
     * Saves the vibration setting to SharedPreferences according to check status of the vibration
     * switch.
     */
    private void saveVibration() {
        mEditor = mSharedPreferences.edit();
        if (mVibrationSwitch.isChecked()) {
            mEditor.putBoolean("vibration", true);
        } else {
            mEditor.putBoolean("vibration", false);
        }
        mEditor.commit();
    }
}
