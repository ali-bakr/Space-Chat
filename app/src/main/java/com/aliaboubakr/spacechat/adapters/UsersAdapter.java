package com.aliaboubakr.spacechat.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.aliaboubakr.spacechat.R;
import com.aliaboubakr.spacechat.models.UsersModel;
import com.aliaboubakr.spacechat.models.UsersViewModel;
import com.bumptech.glide.Glide;

import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UsersViewHolder> {

private Context mContext;
private List<UsersModel> mUsers;

    public UsersAdapter(Context context, List<UsersModel> mUsers) {
   this.mUsers=mUsers;
   this.mContext=context;

    }

    @NonNull
    @Override
    public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

    View view = LayoutInflater.from(mContext).inflate(R.layout.item_user,parent,false);

        return new UsersAdapter.UsersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersViewHolder holder, int position) {

        UsersModel usersModel=mUsers.get(position);
        holder.username.setText(usersModel.getUserName());
        if (usersModel.getImgURL().equals("default")){

            holder.profile_image.setImageResource(R.mipmap.ic_launcher_round);
        }else {
            Glide.with(mContext).load(usersModel.getImgURL()).into(holder.profile_image);
        }
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }




    public class UsersViewHolder extends RecyclerView.ViewHolder{

public TextView username;
public ImageView profile_image;
        public UsersViewHolder(@NonNull View itemView) {
            super(itemView);
            username=itemView.findViewById(R.id.item_user_name_tv);
            profile_image=itemView.findViewById(R.id.item_profile_image);

        }
    }
}
