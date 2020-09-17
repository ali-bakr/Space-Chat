package com.aliaboubakr.spacechat.ui.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


import com.aliaboubakr.spacechat.R;
import com.aliaboubakr.spacechat.adapters.TabsAceessorAdapter;
import com.aliaboubakr.spacechat.ui.signup.SignUpActivity;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private ViewPager myViewPager;
    private TabLayout myTabLayout;
    private TabsAceessorAdapter myTabsAceessorAdapter ;
    FirebaseAuth firebaseAuth;
    FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setElevation(0);
       // mToolbar=findViewById(R.id.main_page_toolbar);

      //  setSupportActionBar(mToolbar);
    //    getSupportActionBar().setTitle("Space Chat");

    //    databaseReference = FirebaseDatabase.getInstance().getReference().child("users").child("hhh");

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
//user.updateEmail("bakra23456@gmail.com");

        DatabaseReference myRef = database.getReference().child("users");
      //  currentUser=firebaseAuth.getCurrentUser();

       // .child("jjj").child(currentUser.getPhoneNumber());

        myRef.child(user.getPhoneNumber()).child("messager").setValue("hiiiii");
       // myRef.setValue("Hello, World!");

        myViewPager=findViewById(R.id.main_tabs_pager);
        myTabLayout=findViewById(R.id.main_tabs);
        myTabsAceessorAdapter=new TabsAceessorAdapter(getSupportFragmentManager());
        myViewPager.setAdapter(myTabsAceessorAdapter);
        myTabLayout.setupWithViewPager(myViewPager);


    }

    @Override
    protected void onStart() {
        super.onStart();
        if (user==null){

         //   sendUserToLoginActivity();
        }
    }

    private void sendUserToLoginActivity() {

       // Intent i=new Intent(MainActivity.this,LogInActivity.class);
        //startActivity(i);
        //androidx.fragment.app.FragmentManager fragmentManager = getSupportFragmentManager();
        //FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //LogInFragment logInFragment=new LogInFragment();

       // getSupportFragmentManager().beginTransaction().replace(R.id.main_activity_container, logInFragment).addToBackStack(null).commit();

        //fragmentTransaction.replace(R.id.main_activity_container, logInFragment);
        //fragmentTransaction.commit();

        }


    // menue onCreateOptionsMenu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.menu_option, menu);
        return true;
    }

    // menue  onOptionsItemSelected
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.logout_item) {
            signOut();

        }
        return super.onOptionsItemSelected(item);
    }


    //fore sign out firebase account
    private void signOut() {

        FirebaseAuth.getInstance().signOut();

       startActivity(new Intent(getApplicationContext(), SignUpActivity.class));
        finish();
    }



}
