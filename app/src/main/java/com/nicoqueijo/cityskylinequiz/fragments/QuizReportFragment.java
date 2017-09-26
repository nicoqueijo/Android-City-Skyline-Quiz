package com.nicoqueijo.cityskylinequiz.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nicoqueijo.cityskylinequiz.R;
import com.nicoqueijo.cityskylinequiz.activities.QuizGameActivity;
import com.nicoqueijo.cityskylinequiz.adapters.QuizReportRecyclerViewAdapter;

public class QuizReportFragment extends Fragment {

    private RecyclerView mRecyclerQuizReport;
    private QuizReportRecyclerViewAdapter mAdapter;

    /**
     * Required empty public constructor
     */
    public QuizReportFragment() {
    }

    /**
     * Called to do initial creation of a fragment.
     *
     * @param savedInstanceState If the fragment is being re-created from
     *                           a previous saved state, this is the state.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Called to have the fragment instantiate its user interface view.
     *
     * @param inflater           The LayoutInflater object that can be used to inflate
     *                           any views in the fragment,
     * @param container          If non-null, this is the parent view that the fragment's
     *                           UI should be attached to.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     *                           from a previous saved state as given here.
     * @return Return the View for the fragment's UI, or null.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_quiz_report, container, false);

        mRecyclerQuizReport = (RecyclerView) view.findViewById(R.id.recycler_quiz_report);
        mAdapter = new QuizReportRecyclerViewAdapter(getActivity(), QuizGameActivity
                .questionReports);
        mRecyclerQuizReport.setAdapter(mAdapter);
        mRecyclerQuizReport.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }

}
