package com.safia.lamzone.events;

import com.safia.lamzone.model.Meeting;

public class DeleteMeetingEvent {

    public Meeting mMeeting;

    public DeleteMeetingEvent(Meeting meeting){
        this.mMeeting = meeting;
    }

}
