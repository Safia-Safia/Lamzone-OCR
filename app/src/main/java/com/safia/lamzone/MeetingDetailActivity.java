package com.safia.lamzone;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.safia.lamzone.model.Meeting;

import java.util.ArrayList;

public class MeetingDetailActivity extends AppCompatActivity {
    private TextView mSubject, mDate,mLocation,mStartHour,mParticipants;
    private Meeting mMeeting;
    ArrayList<String> mList_Emails = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meeting_detail);
        setUpView();
        initMeetingDetail();
    }

    public void setUpView (){
        mMeeting = getIntent().getParcelableExtra(MeetingList.KEY_MEETING);
        mSubject = findViewById(R.id.detail_subject);
        mDate= findViewById(R.id.detail_date);
        mLocation= findViewById(R.id.detail_room);
        mStartHour = findViewById(R.id.detail_startTime);
        mParticipants = findViewById(R.id.detail_participant);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (AddMeetingActivity.ADD_USER_ACTIVITY_REQUEST_CODE == requestCode && RESULT_OK == resultCode) {
            mList_Emails = data.getStringArrayListExtra(AddUserActivity.BUNDLE_EXTRA_NAME);
        }
    }

    public void initMeetingDetail(){
        mSubject.setText(mMeeting.getReunionName());
        mDate.setText(mMeeting.getDate());
        // mLocation.setText(mMeeting.getRoom().getRoomName());
        //mSubject.setBackground(mMeeting.getRoom().getRoomColor());
        mStartHour.setText(mMeeting.getStartTime());
        mParticipants.setText(mList_Emails.toString().replace('[',' ').replace(']',' '));
    }
}
