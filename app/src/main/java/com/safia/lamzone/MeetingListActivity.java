package com.safia.lamzone;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.safia.lamzone.events.DeleteMeetingEvent;
import com.safia.lamzone.di.DI;
import com.safia.lamzone.model.Meeting;
import com.safia.lamzone.service.MeetingApiService;
import com.safia.lamzone.view.MeetingRecyclerviewAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.DatePicker;
import androidx.appcompat.widget.Toolbar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Calendar;
import java.util.List;

public class MeetingListActivity extends AppCompatActivity implements MeetingRecyclerviewAdapter.onMeetingClickListener {
    FloatingActionButton fab;
    int currentRoom;
    private List<Meeting> mMeeting;
    private RecyclerView mRecyclerView;
    private MeetingApiService mApiService;
    private RecyclerView.Adapter mAdapter;
    public static final String KEY_MEETING = "KEY_MEETING";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpView();
        setUpRecyclerView();
        initList();
        onClickFab();

    }

    public void setUpView() {
        mApiService = DI.getNewInstanceApiService();
        fab = findViewById(R.id.addReunion);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void onClickFab() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MeetingListActivity.this, AddMeetingActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setUpRecyclerView() {
        mRecyclerView = findViewById(R.id.meeting_list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
    }

    private void initList() {
        mMeeting = mApiService.getMeeting();
        mAdapter = new MeetingRecyclerviewAdapter(mMeeting, this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        initList();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onDeleteMeeting(DeleteMeetingEvent event) {
        mApiService.deleteMeeting(event.mMeeting);
        initList();
    }

    @Override
    public void onMeetingClick(int position) {
        Intent MeetingDetailIntent = new Intent(MeetingListActivity.this, MeetingDetailActivity.class);
        MeetingDetailIntent.putExtra(KEY_MEETING, mMeeting.get(position));
        startActivity(MeetingDetailIntent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void initRoomFilter() {
        mMeeting = mApiService.getMeetingByRoom(mApiService.getMeetingRooms().get(currentRoom));
        mAdapter = new MeetingRecyclerviewAdapter(mMeeting, this);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initDateFilter() {
        Calendar c =Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog mDatePicker;

        mDatePicker = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                mMeeting = mApiService.getMeetingByDate(dayOfMonth,month,year);
                mAdapter = new MeetingRecyclerviewAdapter(mMeeting, MeetingListActivity.this);
                mRecyclerView.setAdapter(mAdapter);
            }
        }, year, month, day);
        mDatePicker.show();

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.byDate:
               initDateFilter();
                return true;
            case R.id.room1:
                currentRoom = 0;
                initRoomFilter();
                return true;
            case R.id.room2:
                initRoomFilter();
                currentRoom = 1;
                return true;
            case R.id.room3:
                currentRoom = 2;
                initRoomFilter();
                return true;
            case R.id.room4:
                currentRoom = 3;
                initRoomFilter();
                return true;
            case R.id.room5:
                currentRoom = 4;
                initRoomFilter();
                return true;
            case R.id.action_settings:
                return true;
            case R.id.initList:
                initList();
        }
        return super.onOptionsItemSelected(item);
    }

}
