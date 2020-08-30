package com.safia.lamzone;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.safia.lamzone.di.DI;
import com.safia.lamzone.model.Meeting;
import com.safia.lamzone.model.Room;
import com.safia.lamzone.service.MeetingApiService;
import com.safia.lamzone.view.RoomSpinnerAdapter;

import java.util.ArrayList;
import java.util.List;

public class AddMeetingActivity extends AppCompatActivity {
    private EditText mMeetingName, mEmail;
    private MeetingApiService mApiService;
    private List<Room> mRoomList;
    private Room meetingRoomChoose;
    RoomSpinnerAdapter mAdapter;
    Spinner spinnerRooms;
    private Button createBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_reunion);

        setUpViews();
        setUpSpinner();
        onCreateBtn();
        onClickOnSpinner();
    }

    public void setUpViews(){
        mApiService = DI.getMeetingApiService();
        mMeetingName =findViewById(R.id.edit_meeting_name);
        mEmail =findViewById(R.id.edit_email);
        createBtn = findViewById(R.id.btn_create);
        spinnerRooms = findViewById(R.id.spinner);
    }

    public void onCreateBtn(){
        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Meeting reunion = new Meeting(mMeetingName.getText().toString(), mEmail.getText().toString(),meetingRoomChoose);
                mApiService.addMeeting(reunion);
                Intent intent = new Intent(AddMeetingActivity.this, MeetingList.class);
                startActivity(intent);
            }
        });
    }

    public void setUpSpinner (){
        initList();
        mAdapter = new RoomSpinnerAdapter(this, mRoomList);
        spinnerRooms.setAdapter(mAdapter);
    }

    public void onClickOnSpinner(){
        spinnerRooms.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Room clickedItem = (Room) parent.getItemAtPosition(position);
                String roomName = clickedItem.getRoomName();
                meetingRoomChoose = mApiService.getMeetingRooms().get(position);

                Toast.makeText(AddMeetingActivity.this, roomName + " selected", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
    private void initList() {
        mRoomList = new ArrayList<>();
        mRoomList.add(new Room("Salle 1" ,R.drawable.a));
        mRoomList.add(new Room("Salle 2" ,R.drawable.b ));
        mRoomList.add(new Room("Salle 3" ,R.drawable.c));
        mRoomList.add(new Room("Salle 4" ,R.drawable.d));
        mRoomList.add(new Room("Salle 5" ,R.drawable.e));
        mRoomList.add(new Room("Salle 6" ,R.drawable.f));
        mRoomList.add(new Room("Salle 7" ,R.drawable.g));
        mRoomList.add(new Room("Salle 8" ,R.drawable.h));
        mRoomList.add(new Room("Salle 9" ,R.drawable.i));
    }
}
