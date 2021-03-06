package com.safia.lamzone;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.safia.lamzone.model.Meeting;

import java.text.SimpleDateFormat;
import java.util.Locale;


public class MeetingDetailActivity extends AppCompatActivity {
    private TextView mSubject, mDate, mStartHour, mEndHour,mParticipants, mText_Room;
    private ImageView mColorBlock;
    Button btnClose;
    private Meeting mMeeting;
    SimpleDateFormat mFormatter, mDateFormatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meeting_detail);
        setUpView();
        initMeetingDetail();
        setUpCloseBtn();
    }

    public void setUpView() {
        mMeeting = getIntent().getParcelableExtra(MeetingListActivity.KEY_MEETING);
        mSubject = findViewById(R.id.detail_subject);
        mDate = findViewById(R.id.detail_date);
        mStartHour = findViewById(R.id.detail_startTime);
        mEndHour = findViewById(R.id.detail_EndTime);
        mParticipants = findViewById(R.id.detail_participant);
        mColorBlock = findViewById(R.id.decor);
        mText_Room = findViewById(R.id.detail_roomName);
        btnClose = findViewById(R.id.btnClose);
        mFormatter = new SimpleDateFormat("HH:mm ", Locale.FRANCE);
        mDateFormatter = new SimpleDateFormat("EEEE d MMM yyyy", Locale.FRANCE);

    }

    public void initMeetingDetail() {
        mSubject.setText(mMeeting.getReunionName());
        mParticipants.setText(mMeeting.getEmails().toString().replace('[',' ').replace(']',' '));
        mText_Room.setText(mMeeting.getRoom().getRoomName());
        //Colors and backgrounds
        mColorBlock.setBackgroundColor(mMeeting.getRoom().getRoomColor());
        String formatDate = mDateFormatter.format(mMeeting.getDate());
        String formatStartHour = mFormatter.format(mMeeting.getStartTime());
        String formatEndHour = mFormatter.format(mMeeting.getEndTime());
        mStartHour.setText(formatStartHour);
        mEndHour.setText(formatEndHour);
        mDate.setText(formatDate);

    }

    private void setUpCloseBtn(){
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);

    }

}
