package com.safia.lamzone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import com.safia.lamzone.di.DI;
import com.safia.lamzone.model.Meeting;
import com.safia.lamzone.service.MeetingApiService;

public class AddMeetingActivity extends AppCompatActivity  {
    private EditText mMeetingName, mEmail;
    private MeetingApiService mApiService;
    private Button createBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_reunion);
        setUpViews();
        onCreateBtn();
    }

    public void setUpViews(){
        mApiService = DI.getMeetingApiService();
        mMeetingName =findViewById(R.id.edit_meeting_name);
        mEmail =findViewById(R.id.edit_email);
        createBtn = findViewById(R.id.btn_create);
    }

    public void onCreateBtn(){
        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Meeting reunion = new Meeting(mMeetingName.getText().toString(), mEmail.getText().toString(),"https://i.pravatar.cc/150?u=");
                mApiService.addMeeting(reunion);
                Intent intent = new Intent(AddMeetingActivity.this, MeetingList.class);
                startActivity(intent);
            }
        });
    }

}
