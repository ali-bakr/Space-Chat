package com.aliaboubakr.spacechat.models;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class UsersViewModel extends ViewModel {

MutableLiveData<ArrayList<UsersModel>> arrayListMutableLiveDataUsers;
void initiat(){

    if (arrayListMutableLiveDataUsers!=null){
        return;
    }

    arrayListMutableLiveDataUsers=UsersRepo.getInstance().getUsers();
}

public MutableLiveData<ArrayList<UsersModel>> getArrayListMutableLiveDataUsers(){

    return arrayListMutableLiveDataUsers;
}

}
