package com.safia.lamzone.service;

import com.safia.lamzone.model.Meeting;
import com.safia.lamzone.model.Room;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

    @Override
    public boolean isDateAvailable(Meeting meeting) {
        for (Meeting r1 : mMeetingList) {
            if (r1.getDate().equals(meeting.getDate())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isTimeAvailable(Meeting meeting) {
        for (Meeting r2 : mMeetingList) {
            if (r2.getStartTime().equals(meeting.getStartTime()) ||
                    r2.getEndTime().equals(meeting.getEndTime())){
                return false;
            }else if (r2.getStartTime().after(meeting.getStartTime()) ||
                        r2.getEndTime().before(meeting.getEndTime())){
                return false;
            }
        }
        return true;
    }

    @Override
    public List<Meeting> getMeetingByRoom(Room room) {
        ArrayList<Meeting> filterByRoom = new ArrayList<>();
        for(Meeting meeting : mMeetingList) {
            if(meeting.getRoom() == (room))
                filterByRoom.add(meeting);
        }
        return filterByRoom;
    }

    @Override
    public List<Meeting> getMeetingByDate(Date date) {
     return null;}
}
