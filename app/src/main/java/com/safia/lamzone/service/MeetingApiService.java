package com.safia.lamzone.service;

import com.safia.lamzone.model.Meeting;
import com.safia.lamzone.model.Room;
import java.util.List;

public interface MeetingApiService {

    List <Meeting> getMeeting();

    List<Room> getMeetingRooms();

    void addMeeting(Meeting reunion);

    void deleteMeeting(Meeting meeting);

    boolean canMeetingBeCreated(Meeting meeting);

    List <Meeting> getMeetingByRoom (Room room);

    List <Meeting> getMeetingByDate (int d, int m, int y);
}