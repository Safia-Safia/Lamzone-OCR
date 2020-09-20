package com.safia.lamzone.model;

import java.util.List;

public class Meeting {

    private String mReunionName;


    private List<String> mEmails;
    private String mStartTime, mEndTime, mDate;
    private Room mRoom;

    public Meeting(String reunionName, List <String> email,Room room, String date, String startTime, String endTime) {
        this.mReunionName = reunionName;
        this.mEmails = email;
        this.mRoom = room;
        this.mDate = date;
        this.mStartTime = startTime;
        this.mEndTime = endTime;
    }

    public String getReunionName() {
        return mReunionName;
    }

    public Room getRoom() {
        return mRoom;
    }

    public String getDate() {
        return mDate;
    }

    public String getStartTime() {
        return mStartTime;
    }

    public List<String> getEmails() {
        return mEmails;
    }

    public String getEndTime() {
        return mEndTime;
    }


}
