package com.aliaboubakr.spacechat.ui.signup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.aliaboubakr.spacechat.R;
import com.aliaboubakr.spacechat.ui.main.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hbb20.CountryCodePicker;

import java.util.concurrent.TimeUnit;

public class SignUpActivity extends AppCompatActivity {
EditText mPhoneVrfET,mCodeVrfET;
TextView mEntrPhoneTv,mEnterCodeTv;
Button mGetVrfCodeBtn,mVrfCodeBtn;
ProgressBar mProgressBarPhoneSent;
CountryCodePicker ccp;
String mVerificationId;
FirebaseAuth mAuth;

PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallBacks;
//private PhoneAuthProvider.ForceResendingToken mResendToken;
    private String mFullphone;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mAuth = FirebaseAuth.getInstance();
        getSupportActionBar().hide();
        checkUserIsLogedIn();
     //   userIsLogedIn();
        mEntrPhoneTv = findViewById(R.id.entr_ur_phon_tv);
        mEnterCodeTv = findViewById(R.id.entercode_tv);
        mPhoneVrfET = findViewById(R.id.phone_etxt);
        mCodeVrfET = findViewById(R.id.code_etxt);
        mGetVrfCodeBtn = findViewById(R.id.get_verify_code_btn);
        mVrfCodeBtn = findViewById(R.id.verify_code_btn);
        ccp = findViewById(R.id.country_picker);
        mProgressBarPhoneSent = findViewById(R.id.progres_bar1);
        checkUserIsLogedIn();
        mGetVrfCodeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              mFullphone = ccp.getSelectedCountryCodeWithPlus() + mPhoneVrfET.getText().toString();
                mProgressBarPhoneSent.setVisibility(View.VISIBLE);
                sendVerificationCode();

            }
        });

   mVrfCodeBtn.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {

           verifySignInCode();
       }
   });


        mCallBacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {
                signInWithPhoneAuthCredential(credential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                mProgressBarPhoneSent.setVisibility(View.INVISIBLE);
                Log.w("my__Log", "onVerificationFailed", e);

                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    // Invalid request
                    Log.e("onVerificationFailed",e.getMessage());
                    Toast.makeText(SignUpActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                } else if (e instanceof FirebaseTooManyRequestsException) {
                    // The SMS quota for the project has been exceeded
                    Log.e("onVerificationFailed",e.getMessage());
                    Toast.makeText(SignUpActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                }

                // Show a message and update the UI
                // ...
            }

            @Override
            public void onCodeSent(@NonNull String verificationId,
                                   @NonNull PhoneAuthProvider.ForceResendingToken token) {
                // The SMS verification code has been sent to the provided phone number, we
                // now need to ask the user to enter the code and then construct a credential
                // by combining the code with a verification ID.

                mVerificationId=verificationId;

Log.e("lolololo","code sent sent");
                mEntrPhoneTv.setVisibility(View.INVISIBLE);
                mEnterCodeTv.setVisibility(View.VISIBLE);
                mGetVrfCodeBtn.setEnabled(false);
                mGetVrfCodeBtn.setVisibility(View.INVISIBLE);
                mVrfCodeBtn.setVisibility(View.VISIBLE);
                mVrfCodeBtn.setEnabled(true);
                mCodeVrfET.setVisibility(View.VISIBLE);
                mCodeVrfET.setEnabled(true);
                mPhoneVrfET.setEnabled(false);
                mPhoneVrfET.setVisibility(View.INVISIBLE);
                ccp.setVisibility(View.INVISIBLE);
                ccp.setEnabled(false);
                mProgressBarPhoneSent.setVisibility(View.GONE);

                // Save verification ID and resending token so we can use them later

            }
        };

    }






        private void sendVerificationCode () {
            Toast.makeText(this, mFullphone, Toast.LENGTH_SHORT).show();
            if (mFullphone.isEmpty()) {
                mPhoneVrfET.setError("Phone  is required ");
                mPhoneVrfET.requestFocus();
                return;
            }
            if (mFullphone.length() < 11) {
                mPhoneVrfET.setError("Pleas enter valid phone ");
                mPhoneVrfET.requestFocus();
                return;
            }
            PhoneAuthProvider.getInstance().verifyPhoneNumber(mFullphone,
                    120
                    , TimeUnit.SECONDS
                    , this
                    , mCallBacks);
        }



    private void signInWithPhoneAuthCredential(final PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {


                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            // Sign in success, update UI with the signed-in user's information
                         //   Toast.makeText(SignUpActivity.this, credential.getSmsCode(), Toast.LENGTH_SHORT).show();
                            Log.d("my___Log", "signInWithCredential:success");
                            Toast.makeText(SignUpActivity.this, "Logged In Successfully ", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            finish();
                            // FirebaseUser user = task.getResult().getUser();
                            // ...

                            FirebaseUser user = task.getResult().getUser();

                            Bundle bundle=new Bundle();
                            bundle.putString(user.getPhoneNumber(),"useraccountphone");

                        } else {
                            // Sign in failed, display a message and update the UI
                            Log.w("my___Log", "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                            }
                        }
                    }
                });
    }


    private void verifySignInCode() {

        PhoneAuthCredential credential=PhoneAuthProvider.getCredential(mVerificationId,mCodeVrfET.getText().toString());
        signInWithPhoneAuthCredential(credential);
     //   Toast.makeText(this, credential.getSmsCode(), Toast.LENGTH_SHORT).show();
    }















    private void checkUserIsLogedIn () {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
       //  mProgressBarPhoneSent.setVisibility(View.INVISIBLE);

            return;
        }
    }

    }