package com.nicoqueijo.cityskylinequiz.activities;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

import com.nicoqueijo.cityskylinequiz.R;
import com.nicoqueijo.cityskylinequiz.fragments.ClearScoresDialog;
import com.nicoqueijo.cityskylinequiz.fragments.LanguageChooserDialog;
import com.nicoqueijo.cityskylinequiz.interfaces.Communicator;

public class SettingsActivity extends AppCompatActivity implements Communicator {

    private AppCompatActivity mThisActivity = SettingsActivity.this;
    private ActionBar mActionBar;
    private FragmentManager mFragmentManager = getFragmentManager();
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
        mActionBar = getSupportActionBar();
        mActionBar.setDisplayShowHomeEnabled(true);
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setIcon(R.drawable.ic_light_settings);
        mActionBar.setTitle(R.string.actionbar_settings);

        mThemeView = (LinearLayout) findViewById(R.id.container_settings_theme);
        mLanguageView = (LinearLayout) findViewById(R.id.container_settings_language);
        mScoresView = (LinearLayout) findViewById(R.id.container_settings_scores);
        mThemeSwitch = (Switch) findViewById(R.id.switch_theme);
        mThemeSwitch.setChecked(mSharedPreferences.getInt("theme", R.style.AppThemeLight)
                == R.style.AppThemeDark);

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
                languageChooserDialog.show(mFragmentManager, "dialog_language");
            }
        });

        mScoresView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearScoresDialog clearScoresDialog = new ClearScoresDialog();
                clearScoresDialog.show(mFragmentManager, "dialog_scores");
                // open dialog fragment to confirm scores reset
                // show toast if user chose to clear scores
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
            // Reset scores
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
