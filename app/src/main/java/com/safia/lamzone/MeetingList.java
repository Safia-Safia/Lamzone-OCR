package com.safia.lamzone;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.safia.lamzone.events.DeleteMeetingEvent;
import com.safia.lamzone.di.DI;
import com.safia.lamzone.model.Meeting;
import com.safia.lamzone.model.Room;
import com.safia.lamzone.service.DummyMeetingGenerator;
import com.safia.lamzone.service.MeetingApiService;
import com.safia.lamzone.view.MeetingRecyclerviewAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

public class MeetingList extends AppCompatActivity implements MeetingRecyclerviewAdapter.onMeetingClickListener {
    FloatingActionButton fab;
    AlertDialog.Builder mDialog;
    AlertDialog dialog;
    int currentRoom;
    private RadioGroup mRadioGroup;
    private Toolbar mToolbar;
    private List<Meeting> mMeeting;
    private RecyclerView mRecyclerView;
    private MeetingApiService mApiService;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter mAdapter, mAdapter2;
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
        mApiService = DI.getMeetingApiService();
        fab = findViewById(R.id.addReunion);
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
    }

    public void onClickFab() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MeetingList.this, AddMeetingActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setUpRecyclerView() {
        mRecyclerView = findViewById(R.id.meeting_list);
        layoutManager = new LinearLayoutManager(this);
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
        Intent MeetingDetailIntent = new Intent(MeetingList.this, MeetingDetailActivity.class);
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
        mAdapter2 = new MeetingRecyclerviewAdapter(mMeeting, this);
        mRecyclerView.setAdapter(mAdapter2);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.byDate:
                initList();
                return true;
            case R.id.room1:
                initRoomFilter();
                currentRoom = 0;
                return true;
            case R.id.room2:
                currentRoom = 1;
                initRoomFilter();
                return true;
            case R.id.room3:
                initRoomFilter();
                currentRoom = 2;
                return true;
            case R.id.room4:
                initRoomFilter();
                currentRoom = 3;
                return true;
            case R.id.room5:
                initRoomFilter();
                currentRoom = 4;
                return true;
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
