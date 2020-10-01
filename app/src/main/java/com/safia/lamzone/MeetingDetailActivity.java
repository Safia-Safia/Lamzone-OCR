package com.safia.lamzone;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;

import com.safia.lamzone.model.Meeting;


public class MeetingDetailActivity extends AppCompatActivity {
    private TextView mSubject, mDate, mLocation, mStartHour, mParticipants;
    private TextView mTxt_Subject, mText_Location, mText_StartHour, mText_Participants, mTextView;
    private Meeting mMeeting;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meeting_detail);
        setUpView();
        setUpToolbar();
        initMeetingDetail();
    }

    public void setUpView() {
        mMeeting = getIntent().getParcelableExtra(MeetingList.KEY_MEETING);
        mSubject = findViewById(R.id.detail_subject);
        mDate = findViewById(R.id.detail_date);
        mLocation = findViewById(R.id.detail_room);
        mStartHour = findViewById(R.id.detail_startTime);
        mParticipants = findViewById(R.id.detail_participant);
        mToolbar = findViewById(R.id.toolbar3);
        mText_Location =findViewById(R.id.text_salle);
        mText_Participants = findViewById(R.id.text_participant);
        mText_StartHour = findViewById(R.id.text_heure_debut);
        mTextView = findViewById(R.id.decor);
    }

    public void initMeetingDetail() {
        mSubject.setText(mMeeting.getReunionName());
        mSubject.setTextColor((mMeeting.getRoom().getRoomColor()));
        mDate.setText(mMeeting.getDate());
        mLocation.setText(mMeeting.getRoom().getRoomName());
        mStartHour.setText(mMeeting.getStartTime().toString());
        mParticipants.setText(mMeeting.getEmails().toString());

        mToolbar.setBackgroundColor(mMeeting.getRoom().getRoomColor());
        mText_Location.setTextColor(mMeeting.getRoom().getRoomColor());
        mText_StartHour.setTextColor(mMeeting.getRoom().getRoomColor());
        mText_Participants.setTextColor(mMeeting.getRoom().getRoomColor());
        mDate.setTextColor(mMeeting.getRoom().getRoomColor());
        mTextView.setBackgroundColor(mMeeting.getRoom().getRoomColor());
    }

    private void setUpToolbar() {
        mToolbar.setTitle(" Retour ");
        mToolbar.getResources().getColor(android.R.color.white);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
