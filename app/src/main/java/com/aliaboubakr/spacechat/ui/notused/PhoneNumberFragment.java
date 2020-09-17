package com.aliaboubakr.spacechat.ui.notused;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.aliaboubakr.spacechat.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.hbb20.CountryCodePicker;

import java.util.Objects;

import java.util.concurrent.TimeUnit;

import static androidx.constraintlayout.widget.Constraints.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class PhoneNumberFragment extends Fragment {

EditText mPhone;
CountryCodePicker ccp;
String mFullPhone="";
   // PhoneAuthProvider authProvider;
  //  PhoneAuthCredential credential;
   //FirebaseAuth mAuth=new FirebaseAuth();
PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    public PhoneNumberFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //thith line to be able to call fragment  by >> this.getActivity()
        super.onActivityCreated(savedInstanceState);
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_phone_number, container, false);
        mPhone=v.findViewById(R.id.phone_etxt);
        ccp=v.findViewById(R.id.country_picker);
        FirebaseApp.initializeApp(Objects.requireNonNull(this.getContext()));
        
        v.findViewById(R.id.get_verify_code_btn).setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if (mPhone.getText().length()==11){
          //  getFullPhone();
         //   mPhone.setText(ccp.getSelectedCountryCode() .trim()+ mPhone.getText().toString().trim());
           startPhoneNumberVerification();

        }
        else {mPhone.setError("wrong phone");}
       // goToVerificationCodeFragment();
    }



});






        mCallbacks=new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {


                signInWithPhoneAuthCridential(phoneAuthCredential);
                Log.e("()()()","onVerificationCompleted");

            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                Log.e("()()()Faild()()",e.getMessage());

            }

            @Override
            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
            Log.e("()()()() CODE ()()()()",s);
            mPhone.setText(s);

            }
        };

        return v;
    }

    private void signInWithPhoneAuthCridential(PhoneAuthCredential phoneAuthCredential) {
        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential).addOnCompleteListener(this.getActivity(), new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                 if (task.isSuccessful()){
                     Log.e("()()()()()()","task sucess");
                 }
            }
        });

    }


    private void getFullPhone() {

     //     mPhone.setText(ccp.getSelectedCountryCode() .trim()+ mPhone.getText().toString().trim());
          //  mFullPhone=ccp.getFullNumber()+mPhone.getText().toString().trim();
          // mPhone.setText(mFullPhone);}
          mFullPhone="2001211652320";

    }

    private void startPhoneNumberVerification() {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                mFullPhone,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                 this.getActivity(),               // Activity (for callback binding)
                mCallbacks);
    }






}
