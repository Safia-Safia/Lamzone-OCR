package com.safia.lamzone.service;

import com.safia.lamzone.model.Meeting;

import java.util.List;

public interface MeetingApiService {

    List <Meeting> getMeeting();

    void addMeeting(Meeting reunion);

    void deleteMeeting(Meeting meeting);

}
