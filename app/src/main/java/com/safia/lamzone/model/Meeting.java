package com.safia.lamzone.model;

import java.util.List;

public class Meeting {

    private String mReunionName;
    private String mEmail;
    private String mStartTime,mEndTIme, mDate;
    private Room mRoom;

    public Meeting(String reunionName, String email,Room room, String date, String startTime) {
        this.mReunionName = reunionName;
        this.mEmail = email;
        this.mRoom = room;
        this.mDate = date;
        this.mStartTime = startTime;
    }

    public String getReunionName() {
        return mReunionName;
    }

    public void setReunionName(String reunionName) {
        mReunionName = reunionName;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public Room getRoom() {
        return mRoom;
    }

    public void setRoom(Room room) { mRoom = room; }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public String getStartTime() {
        return mStartTime;
    }

    public void setStartTime(String startTime) {
        mStartTime = startTime;
    }
}
