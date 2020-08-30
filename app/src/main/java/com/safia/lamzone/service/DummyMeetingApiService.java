package com.safia.lamzone.service;

import com.safia.lamzone.model.Meeting;
import com.safia.lamzone.model.Room;

import java.util.List;

public class DummyMeetingApiService implements MeetingApiService {

    private List<Meeting> reunions = DummyMeetingGenerator.generateNeighbours();
    private List<Room> rooms = DummyRoomGenerator.generateRooms();

    @Override
    public List<Meeting> getMeeting() {
        return reunions;
    }

    @Override
    public List<Room> getMeetingRooms() {
        return rooms;
    }

    @Override
    public void addMeeting(Meeting meeting) {
        reunions.add(meeting);
    }

    @Override
    public void deleteMeeting(Meeting meeting) {
        reunions.remove(meeting);
    }
}
