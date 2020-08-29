package com.safia.lamzone.model;

public class Meeting {

    private String mReunionName;
    private String mEmail;
    private String mSubject;
    private String mHour;
    private String mDate;
    private String mMeetingUrl;

    public Meeting(String reunionName, String email,String meetingUrl) {
        this.mReunionName = reunionName;
        this.mEmail = email;
        this.mMeetingUrl =meetingUrl;
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

    public String getMeetingUrl() {
        return mMeetingUrl;
    }

    public void setMeetingUrl(String meetingUrl) {
        mMeetingUrl = meetingUrl;
    }


}
