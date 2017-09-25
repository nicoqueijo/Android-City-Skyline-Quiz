package com.nicoqueijo.cityskylinequiz.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nicoqueijo.cityskylinequiz.R;

public class QuizReportFragment extends Fragment {

    private RecyclerView mRecyclerQuizReport;

    /**
     * Required empty public constructor
     */
    public QuizReportFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_quiz_report, container, false);

        return view;
    }

}
