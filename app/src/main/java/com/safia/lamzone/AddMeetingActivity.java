package com.safia.lamzone;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.safia.lamzone.di.DI;
import com.safia.lamzone.model.Meeting;
import com.safia.lamzone.model.Room;
import com.safia.lamzone.service.MeetingApiService;
import com.safia.lamzone.view.RoomSpinnerAdapter;

import java.util.ArrayList;
import java.util.Calendar;

public class AddMeetingActivity extends AppCompatActivity {
    private EditText mMeetingName, mEmail;
    private MeetingApiService mApiService;
    private ArrayList<Room> mRoomList;
    private Room meetingRoomChoose;
    RoomSpinnerAdapter mAdapter;
    Spinner spinnerRooms;
    private Button createBtn;
    private ImageButton datePicker, timePicker;
    private String date, time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_reunion);

        setUpViews();
        setUpSpinner();
        setUpDatePicker();
        setUpTimePicker();
        onCreateBtn();
        onClickOnSpinner();
    }

    public void setUpViews() {
        mApiService = DI.getMeetingApiService();
        mMeetingName = findViewById(R.id.edit_meeting_name);
        mEmail = findViewById(R.id.edit_email);
        createBtn = findViewById(R.id.btn_create);
        spinnerRooms = findViewById(R.id.spinner);
        datePicker = findViewById(R.id.btn_datePicker);
        timePicker = findViewById(R.id.btn_timePicker);
    }

    public void onCreateBtn() {
        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Meeting reunion = new Meeting(mMeetingName.getText().toString(), mEmail.getText().toString(), meetingRoomChoose, date, time);
                mApiService.addMeeting(reunion);
                Intent intent = new Intent(AddMeetingActivity.this, MeetingList.class);
                startActivity(intent);
            }
        });
    }

    public void setUpSpinner() {
        initList();
        mAdapter = new RoomSpinnerAdapter(this, mRoomList);
        spinnerRooms.setAdapter(mAdapter);
    }

    public void onClickOnSpinner() {
        setUpSpinner();
        spinnerRooms.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Room clickedItem = (Room) parent.getItemAtPosition(position);
                String roomName = clickedItem.getRoomName();
                meetingRoomChoose = mApiService.getMeetingRooms().get(position);
                Toast.makeText(AddMeetingActivity.this, roomName, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void initList() {
        mRoomList = new ArrayList<>();
        mRoomList.add(new Room("Salle 1", 0x36000000 + Color.GREEN));
        mRoomList.add(new Room("Salle 2", 0x36000000 + Color.BLUE));
        mRoomList.add(new Room("Salle 3", 0x36000000 + Color.RED));
        mRoomList.add(new Room("Salle 4", 0x36000000 + Color.YELLOW));
        mRoomList.add(new Room("Salle 5", 0x36000000 + Color.BLACK));
    }

    public void setUpDatePicker() {
        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar currentDate = Calendar.getInstance();
                int year = currentDate.get(Calendar.YEAR);
                int month = currentDate.get(Calendar.MONTH);
                int day = currentDate.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog mDatePicker;

                mDatePicker = new DatePickerDialog(AddMeetingActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        date = ", " + dayOfMonth + "/" + month + "/" + year;
                    }
                }, year, month, day);
                mDatePicker.show();
            }
        });

    }

    public void setUpTimePicker() {
        timePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar currentHour = Calendar.getInstance();
                int hour = currentHour.get(Calendar.HOUR_OF_DAY);
                int minute = currentHour.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;

                mTimePicker = new TimePickerDialog(AddMeetingActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                        time = hour + ":" + minute;
                    }
                }, hour, minute, true);
                mTimePicker.show();
            }
        });
    }

}
