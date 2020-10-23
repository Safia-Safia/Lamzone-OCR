package com.safia.lamzone;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;

import com.safia.lamzone.di.DI;
import com.safia.lamzone.model.Meeting;
import com.safia.lamzone.model.Room;
import com.safia.lamzone.service.MeetingApiService;
import com.safia.lamzone.view.RoomSpinnerAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddMeetingActivity extends AppCompatActivity {
    private MeetingApiService mApiService;
    public static final int ADD_USER_ACTIVITY_REQUEST_CODE = 1;
    private EditText mMeetingName;
    private TextView mText_StartTime, mText_EndTime, mText_Date, mCardView_Email;
    private Button createBtn, mBtn_add_email;
    private ImageButton datePicker, startTimePicker, endTimePicker;
    private Date startTime, endTime,mDate;
    private String txtStartTime, txtEndTime;
    RoomSpinnerAdapter mAdapter;
    Spinner spinnerRooms;
    SimpleDateFormat mFormatter;
    ArrayList<String> mList_Emails = new ArrayList<>();
    private Calendar currentEndHour, currentStartHour, currentDate;
    private Toolbar mToolbar;
    private Room meetingRoomChoose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_reunion);

        setUpViews();
        setUpToolbar();
        setUpSpinner();
        setUpDatePicker();
        setUpEmailBtn();
        setUpStartTimePicker();
        setUpEndTimePicker();
        onCreateBtn();
        onClickOnSpinner();
    }

    private void setUpViews() {
        mApiService = DI.getMeetingApiService();
        mMeetingName = findViewById(R.id.edit_meeting_name);
        mBtn_add_email = findViewById(R.id.btn_add_email);
        createBtn = findViewById(R.id.btn_create);
        spinnerRooms = findViewById(R.id.spinner);
        datePicker = findViewById(R.id.btn_datePicker);
        startTimePicker = findViewById(R.id.btn_start_timePicker);
        endTimePicker = findViewById(R.id.btn_end_timePicker);
        mText_Date = findViewById(R.id.txt_date_picker);
        mText_StartTime = findViewById(R.id.txt_start_time_picker);
        mText_EndTime = findViewById(R.id.txt_end_time_picker);
        mCardView_Email = findViewById(R.id.email_textview);
        mToolbar = findViewById(R.id.toolbar2);
        currentEndHour = Calendar.getInstance();
        currentStartHour = Calendar.getInstance();
        currentDate = Calendar.getInstance();
    }

    private void onCreateBtn() {
        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Meeting meeting = new Meeting(
                        mMeetingName.getText().toString(),
                        mList_Emails,
                        meetingRoomChoose,
                        mDate,
                        startTime,
                        endTime);
                if (checkDataValid() && compareTime()) {
                    if (mApiService.isRoomAvailable(meeting) || mApiService.isDateAvailable(meeting)
                            || mApiService.isTimeAvailable(meeting)) {
                        mApiService.addMeeting(meeting);
                        finish();
                    } else {
                        Toast.makeText(AddMeetingActivity.this, R.string.anavailableRoom, Toast.LENGTH_LONG).show();

                    }
                }

            }

        });
    }

    private void setUpSpinner() {
        mAdapter = new RoomSpinnerAdapter(this, mApiService.getMeetingRooms());
        spinnerRooms.setAdapter(mAdapter);
    }

    private void setUpToolbar() {
        mToolbar.setTitle(R.string.ajouter_reunion_textview);
        mToolbar.getResources().getColor(android.R.color.white);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void onClickOnSpinner() {
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

    private void setUpDatePicker() {
        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int year = currentDate.get(Calendar.YEAR);
                int month = currentDate.get(Calendar.MONTH);
                int day = currentDate.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog mDatePicker;
                mFormatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.FRANCE);

                mDatePicker = new DatePickerDialog(AddMeetingActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth ) {
                        currentDate.set(Calendar.YEAR,year);
                        currentDate.set(Calendar.MONTH, month);
                        currentDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        mDate = currentDate.getTime();
                        String format = mFormatter.format(mDate.getTime());
                        mText_Date.setText(format);
                    }
                }, year, month, day);
                mDatePicker.show();
            }
        });

    }

    private void setUpStartTimePicker() {
        startTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int hour = currentStartHour.get(Calendar.HOUR_OF_DAY);
                int minute = currentStartHour.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;

                mTimePicker = new TimePickerDialog(AddMeetingActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                        txtStartTime = hour + " : " + minute;
                        currentStartHour.set(Calendar.HOUR, hour);
                        currentStartHour.set(Calendar.MINUTE, minute);
                        startTime = currentStartHour.getTime();
                        mText_StartTime.setText(txtStartTime);
                    }
                }, hour, minute, true);
                mTimePicker.show();
            }
        });
    }

    private void setUpEndTimePicker() {
        endTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int hour = currentEndHour.get(Calendar.HOUR_OF_DAY);
                int minute = currentEndHour.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;

                mTimePicker = new TimePickerDialog(AddMeetingActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                        txtEndTime = hour + " : " + minute;
                        mText_EndTime.setText(txtEndTime);
                        currentEndHour.set(Calendar.HOUR, hour);
                        currentEndHour.set(Calendar.MINUTE, minute);
                        endTime = currentEndHour.getTime();
                    }
                }, hour, minute, true);
                mTimePicker.show();
            }
        });
    }

    private boolean compareTime() {
        if (currentEndHour.before(currentStartHour) || currentStartHour.equals(currentEndHour)) {
            Toast.makeText(AddMeetingActivity.this, "Verifiez l'heure", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    private void setUpEmailBtn() {
        mBtn_add_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addUserIntent = new Intent(AddMeetingActivity.this, AddUserActivity.class);
                addUserIntent.putStringArrayListExtra(AddUserActivity.BUNDLE_EXTRA_NAME, mList_Emails);
                startActivityForResult(addUserIntent, ADD_USER_ACTIVITY_REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (ADD_USER_ACTIVITY_REQUEST_CODE == requestCode && RESULT_OK == resultCode) {
            mList_Emails = data.getStringArrayListExtra(AddUserActivity.BUNDLE_EXTRA_NAME);
            mCardView_Email.setText(mList_Emails.toString().replace('[', ' ').replace(']', ' ').replace(',', ' '));
        }
    }

    public boolean checkDataValid() {
        if ((mMeetingName.getText().toString().isEmpty() || mList_Emails.size() == 0 ||
                mText_Date.getText().toString().isEmpty())) {
            Toast.makeText(this, R.string.emptyData, Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}


