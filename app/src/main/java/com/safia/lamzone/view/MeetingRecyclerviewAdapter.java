package com.safia.lamzone.view;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.safia.lamzone.R;
import com.safia.lamzone.events.DeleteMeetingEvent;
import com.safia.lamzone.model.Meeting;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class MeetingRecyclerviewAdapter extends RecyclerView.Adapter<MeetingRecyclerviewAdapter.ViewHolder> {

    private final List<Meeting> mReunionList;
    private onMeetingClickListener mOnMeetingClickListener;

    public MeetingRecyclerviewAdapter(List<Meeting> items, onMeetingClickListener onMeetingClickListener) {
        mReunionList = items;
        this.mOnMeetingClickListener = onMeetingClickListener;
    }

    @Override
    public MeetingRecyclerviewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.content_main, parent, false);
        return new ViewHolder(view , mOnMeetingClickListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Meeting meeting = mReunionList.get(position);
        holder.mReunionName.setText(meeting.getReunionName());
        holder.mMemberMails.setText(meeting.getEmails().toString().replace('[',' ').replace(']',' '));
        holder.mMeetingsPicture.setBackgroundColor(meeting.getRoom().getRoomColor());

        //For Date
        SimpleDateFormat dateFormatter = new SimpleDateFormat("EEEE d MMM yyyy", Locale.FRANCE);
        String formatDate = dateFormatter.format(meeting.getDate());
        holder.mDateText.setText(formatDate);
        //For time
        Log.e("Meeting " + position, " meeting " + meeting.getStartTime().getTime());
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm", Locale.FRANCE);
        String formatStartHour = formatter.format(meeting.getStartTime());
        String formatEndHour = formatter.format(meeting.getEndTime());
        holder.mStartTimeText.setText(formatStartHour);
        holder.mEndTimeText.setText(formatEndHour);

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

    public class ViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{
        TextView mReunionName, mMemberMails, mDateText, mStartTimeText, mEndTimeText;
        ImageButton mDeleteBtn;
        ImageView mMeetingsPicture;
        onMeetingClickListener mOnMeetingClickListener;

        ViewHolder(View view, onMeetingClickListener onMeetingClickListener) {
            super(view);
            mReunionName = view.findViewById(R.id.txt_reunionName);
            mMemberMails = view.findViewById(R.id.text_memberMails);
            mDateText = view.findViewById(R.id.txt_reunionDate);
            mStartTimeText = view.findViewById(R.id.txt_startTime);
            mEndTimeText = view.findViewById(R.id.txt_endTime);
            mMeetingsPicture = view.findViewById(R.id.imageView_meeting_picture);
            mDeleteBtn = view.findViewById(R.id.imageButton_delete);
            this.mOnMeetingClickListener = onMeetingClickListener;
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mOnMeetingClickListener.onMeetingClick(getAdapterPosition());
        }
    }

    public interface onMeetingClickListener{
        void onMeetingClick(int position);
    }
}
