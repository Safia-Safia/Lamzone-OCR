package com.safia.lamzone.service;

import android.util.Log;
import android.widget.Toast;

import com.safia.lamzone.AddMeetingActivity;
import com.safia.lamzone.R;
import com.safia.lamzone.model.Meeting;
import com.safia.lamzone.model.Room;

import java.util.ArrayList;
import java.util.Calendar;
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
    public boolean areDataAvailable(Meeting meeting) {
        for (Meeting r : mMeetingList) {
            if (isDateAvailable(meeting, r)) {
                if (isRoomAvailable(meeting, r)) {
                   if (isTimeAvailable(meeting, r)) {
                       return false;
                   }
                }
            }
       }
        return true;
    }

    private boolean isRoomAvailable(Meeting meeting, Meeting r) {
        return meeting.getRoom().equals(r.getRoom());
    }

    private boolean isDateAvailable(Meeting meeting, Meeting r) {
        return (meeting.getDate().getDay()== r.getDate().getDay());
    }

    private boolean isTimeAvailable(Meeting meeting, Meeting r) {
        if (meeting.getStartTime().getHours() == r.getStartTime().getHours() &&
                meeting.getEndTime().getHours() == r.getEndTime().getHours()){
            return  false;
        } else return meeting.getStartTime().after(r.getStartTime()) &&
                (meeting.getEndTime().before(r.getEndTime()));
    }

    @Override
    public List<Meeting> getMeetingByRoom(Room room) {
        ArrayList<Meeting> filterByRoom = new ArrayList<>();
        for (Meeting meeting : mMeetingList) {
            if (meeting.getRoom() == (room))
                filterByRoom.add(meeting);
        }
        return filterByRoom;
    }

    @Override
    public List<Meeting> getMeetingByDate(int d, int m, int y) {
        ArrayList<Meeting> filterByDate = new ArrayList<>();
        int year2, month2, day2;
        Calendar c = Calendar.getInstance();
        for (Meeting meeting1 : mMeetingList) {
            c.setTime(meeting1.getDate());
            year2 = c.get(Calendar.YEAR);
            month2 = c.get(Calendar.MONTH);
            day2 = c.get(Calendar.DAY_OF_MONTH);
            if (y == year2 && m == month2 && d == day2) {
                filterByDate.add(meeting1);
            }
        }
        return filterByDate;
    }
}
