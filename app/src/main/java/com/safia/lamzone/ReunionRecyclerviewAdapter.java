package com.safia.lamzone;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.safia.lamzone.events.DeleteMeetingEvent;
import com.safia.lamzone.model.Meeting;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class ReunionRecyclerviewAdapter extends RecyclerView.Adapter<ReunionRecyclerviewAdapter.ViewHolder> {

    private final List <Meeting> mReunionList;

    public ReunionRecyclerviewAdapter (List<Meeting> items){
       mReunionList = items;
    }

    @Override
    public ReunionRecyclerviewAdapter.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.content_main, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Meeting meeting = mReunionList.get(position);
        holder.mReunionName.setText(meeting.getReunionName());
        holder.mMemberName.setText(meeting.getEmail());
        Glide.with(holder.mMeetingsPicture.getContext())
                .load(meeting.getMeetingUrl())
                .apply(RequestOptions.circleCropTransform())
                .into(holder.mMeetingsPicture);

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
        TextView mReunionName, mMemberName;
        ImageButton mDeleteBtn;
        ImageView mMeetingsPicture;
        ViewHolder(View view){
            super(view);
            mReunionName = view.findViewById(R.id.txt_reunionName);
            mMemberName = view.findViewById(R.id.text_memberName);
            mMeetingsPicture = view.findViewById(R.id.imageView_meeting_picture);
            mDeleteBtn = view.findViewById(R.id.imageButton_delete);
        }
    }
}
