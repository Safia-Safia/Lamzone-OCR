package com.safia.lamzone.view;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.safia.lamzone.R;
import com.safia.lamzone.events.DeleteMeetingEvent;
import com.safia.lamzone.model.Meeting;
import com.safia.lamzone.service.MeetingRoomGenerator;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class MeetingRecyclerviewAdapter extends RecyclerView.Adapter<MeetingRecyclerviewAdapter.ViewHolder> {

    private final List <Meeting> mReunionList;

    public MeetingRecyclerviewAdapter(List<Meeting> items){
       mReunionList = items;
    }

    @Override
    public MeetingRecyclerviewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.content_main, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Meeting meeting = mReunionList.get(position);
        holder.mReunionName.setText(meeting.getReunionName());
        holder.mMemberMails.setText(meeting.getEmails().toString());
        holder.mMeetingsPicture.setBackgroundColor(meeting.getRoom().getRoomColor());
        holder.mDateText.setText(meeting.getDate());
        holder.mTimeText.setText(meeting.getStartTime());
        holder.mDeleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new DeleteMeetingEvent(meeting));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mReunionList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView mReunionName, mMemberMails,mDateText, mTimeText;
        ImageButton mDeleteBtn;
        ImageView mMeetingsPicture;
        ViewHolder(View view){
            super(view);
            mReunionName = view.findViewById(R.id.txt_reunionName);
            mMemberMails = view.findViewById(R.id.text_memberMails);
            mDateText = view.findViewById(R.id.txt_reunionDate);
            mTimeText = view.findViewById(R.id.txt_hour);
            mMeetingsPicture = view.findViewById(R.id.imageView_meeting_picture);
            mDeleteBtn = view.findViewById(R.id.imageButton_delete);
        }
    }
}
