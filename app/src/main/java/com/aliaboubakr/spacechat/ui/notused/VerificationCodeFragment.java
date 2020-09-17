package com.aliaboubakr.spacechat.ui.notused;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aliaboubakr.spacechat.R;
import com.aliaboubakr.spacechat.ui.main.MainActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class VerificationCodeFragment extends Fragment {


    public VerificationCodeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_verification_code, container, false);
        v.findViewById(R.id.verify_code_btn).setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        goToMainAtivity();
    }
});
        return v;
    }

    private void goToMainAtivity() {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);

    }

}
