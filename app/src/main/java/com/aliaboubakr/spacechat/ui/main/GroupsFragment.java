package com.aliaboubakr.spacechat.ui.main;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aliaboubakr.spacechat.R;
import com.aliaboubakr.spacechat.ui.signup.SignUpActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class GroupsFragment extends Fragment {


    public GroupsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_groups, container, false);
    v.findViewById(R.id.go_first).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            goFirst();
        }
    });
    return v;
    }

    private void goFirst() {
        Intent intent = new Intent(getActivity(), SignUpActivity.class);
        startActivity(intent);
    }

}
