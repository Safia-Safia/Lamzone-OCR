package com.safia.lamzone.model;

import java.util.List;

public class Meeting {

    private String mReunionName;
    private String mEmail;
    private String mStartTime,mEndTIme;
    private String mDate;
    private Room mRoom;

    public Meeting(String reunionName, String email,Room room) {
        this.mReunionName = reunionName;
        this.mEmail = email;
        this.mRoom = room;
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

    //public void setRoom(Room room) { mRoom = room; }


}
