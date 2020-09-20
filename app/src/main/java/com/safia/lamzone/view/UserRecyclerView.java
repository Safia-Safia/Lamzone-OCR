package com.safia.lamzone.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.safia.lamzone.R;

import java.util.List;

public class UserRecyclerView extends RecyclerView.Adapter<UserRecyclerView.MyViewHolder> {
    private List<String> mParticipants;

    public UserRecyclerView(List<String> participants) {
        this.mParticipants = participants;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.content_user_rv, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.display(mParticipants.get(position));
    }

    @Override
    public int getItemCount() {
        return mParticipants == null ? 0 : mParticipants.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView userEmailAddress;

        MyViewHolder(View v) {
            super(v);
            userEmailAddress = v.findViewById(R.id.txt_user_address_email);
        }

        void display(String userEmailAddress) {
            this.userEmailAddress.setText(userEmailAddress);
        }
    }
}
