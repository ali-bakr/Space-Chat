package com.aliaboubakr.spacechat.adapters;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.aliaboubakr.spacechat.ui.main.ChatsFragment;
import com.aliaboubakr.spacechat.ui.main.ContactsFragment;
import com.aliaboubakr.spacechat.ui.main.GroupsFragment;

public class TabsAceessorAdapter extends FragmentPagerAdapter {
    public TabsAceessorAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                ChatsFragment chatsFragment=new ChatsFragment();
                return chatsFragment;
            case 1:
                ContactsFragment contactsFragment=new ContactsFragment();
                return contactsFragment;
            case 2:
                GroupsFragment groupsFragment=new GroupsFragment();
                return groupsFragment;

                default:
                    return null;

        }

    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                ChatsFragment chatsFragment=new ChatsFragment();
                return "Chats";
            case 1:
                ContactsFragment contactsFragment=new ContactsFragment();
                return "Contacts";
            case 2:
                GroupsFragment groupsFragment=new GroupsFragment();
                return "Groups";

            default:
                return null;
        }
    }
}
