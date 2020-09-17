package com.aliaboubakr.spacechat.ui.notused;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.aliaboubakr.spacechat.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class HelloFragment extends Fragment {
Button letusGoBtn;

    public HelloFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_hello, container, false);


        return v;

    }



}
