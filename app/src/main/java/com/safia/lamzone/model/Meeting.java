package com.safia.lamzone.model;

import java.util.List;

public class Meeting {

    private String mReunionName;


    private List<String> mEmails;
    private String mStartTime,mEndTIme, mDate;
    private Room mRoom;

    public Meeting(String reunionName, List <String> email,Room room, String date, String startTime) {
        this.mReunionName = reunionName;
        this.mEmails = email;
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

    public List<String> getEmails() {
        return mEmails;
    }

    public void setEmails(List<String> emails) {
        mEmails = emails;
    }

    public String getEndTIme() {
        return mEndTIme;
    }

    public void setEndTIme(String endTIme) {
        mEndTIme = endTIme;
    }

}
