package com.sawaedaib.aibrahemsawaed.myapplication.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sawaedaib.aibrahemsawaed.myapplication.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FoodFragment extends Fragment  {


    public FoodFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_food, container, false);




        return view;
    }

}
