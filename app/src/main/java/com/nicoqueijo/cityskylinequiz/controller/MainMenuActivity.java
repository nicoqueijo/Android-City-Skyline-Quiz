package com.nicoqueijo.cityskylinequiz.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.nicoqueijo.cityskylinequiz.R;

public class MainMenuActivity extends AppCompatActivity {

    // caching example
    // for all cities fetch the images...
    // Picasso.with(MainActivity.this).load(url).fetch();

    private Button mButtonStartGame;
    private Button mButtonCityList;
    private Button mButtonSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_main);

        cacheImagesAndLoadToMemory();

        mButtonStartGame = (Button) findViewById(R.id.startGameButton);
        mButtonCityList = (Button) findViewById(R.id.cityListButton);
        mButtonSettings = (Button) findViewById(R.id.settingsButton);

        /**
         *
         */
        mButtonStartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        /**
         *
         */
        mButtonCityList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cityListIntent = new Intent(MainMenuActivity.this, CityListActivity.class);
                startActivity(cityListIntent);
            }
        });

        /**
         *
         */
        mButtonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    /**
     *
     */
    private void cacheImagesAndLoadToMemory() {
        //TODO
    }
}
