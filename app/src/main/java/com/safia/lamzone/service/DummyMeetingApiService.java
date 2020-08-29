package com.safia.lamzone.service;

import com.safia.lamzone.model.Meeting;

import java.util.List;

public class DummyMeetingApiService implements MeetingApiService {

    private List<Meeting> reunions = DummyMeetingGenerator.generateNeighbours();

    @Override
    public List<Meeting> getMeeting() {
        return reunions;
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
