package com.aliaboubakr.spacechat.models;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UsersRepo {


    private static UsersRepo instane;
    private ArrayList<UsersModel> users=new ArrayList<>();
    private MutableLiveData<ArrayList<UsersModel>> usersLiveData=new MutableLiveData<>();
    UsersModel usersModel=new UsersModel();



    public static UsersRepo getInstance() {


        if (instane == null) {

            instane = new UsersRepo();
        }
        return instane;

    }


    public MutableLiveData<ArrayList<UsersModel>> getUsers(){

        if (users.size()==0){
            loadUsers();
        }
        usersLiveData.setValue(users);
        return usersLiveData;
    }

    private void loadUsers() {

        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference();
        Query query=reference.child("clients").child(user.getPhoneNumber());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                users.clear();
                for (DataSnapshot snapshot :dataSnapshot.getChildren()) {
                    users.add(snapshot.getValue(usersModel.getClass()));
                }
                usersLiveData.postValue(users);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
