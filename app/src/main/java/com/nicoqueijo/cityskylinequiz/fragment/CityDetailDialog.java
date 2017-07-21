package com.nicoqueijo.cityskylinequiz.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nicoqueijo.cityskylinequiz.R;

public class CityDetailDialog extends DialogFragment {

    // make fields for city name and city image


    public CityDetailDialog() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_city_detail, container, false);
    }
}
