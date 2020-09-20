package com.safia.lamzone.service;

import android.widget.Toast;

import com.safia.lamzone.AddMeetingActivity;
import com.safia.lamzone.model.Meeting;
import com.safia.lamzone.model.Room;

import java.util.Iterator;
import java.util.List;

public class DummyMeetingApiService implements MeetingApiService {

    private List<Meeting> mMeetingList = DummyMeetingGenerator.generateMeetings();
    private List<Room> rooms = DummyMeetingGenerator.generateRooms();

    @Override
    public List<Meeting> getMeeting() {
        return mMeetingList;
    }

    @Override
    public List<Room> getMeetingRooms() {
        return rooms;
    }

    @Override
    public void addMeeting(Meeting meeting) {
        mMeetingList.add(meeting);
    }

    @Override
    public void deleteMeeting(Meeting meeting) {
        mMeetingList.remove(meeting);
    }


    @Override
    public boolean isRoomAvailable(Meeting meeting) {
        for (Meeting r : mMeetingList) {
            if (r.getRoom().equals(meeting.getRoom())) {
                return false;
            }
        }
        return true;
    }
}
