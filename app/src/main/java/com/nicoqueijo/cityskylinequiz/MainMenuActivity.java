package com.nicoqueijo.cityskylinequiz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_main);


        // caching example
        //Picasso.with(MainActivity.this).load(url).fetch();
    }
}
