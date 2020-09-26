package com.safia.lamzone.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Meeting implements Parcelable {

    private String mReunionName;


    private List<String> mEmails;
    private String mStartTime, mEndTime, mDate;
    private Room mRoom;

    public Meeting(String reunionName, List<String> email, Room room, String date, String startTime, String endTime) {
        this.mReunionName = reunionName;
        this.mEmails = email;
        this.mRoom = room;
        this.mDate = date;
        this.mStartTime = startTime;
        this.mEndTime = endTime;
    }

    protected Meeting(Parcel in) {
        mReunionName = in.readString();
        mEmails = in.createStringArrayList();
        mStartTime = in.readString();
        mEndTime = in.readString();
        mDate = in.readString();
    }

    public static final Creator<Meeting> CREATOR = new Creator<Meeting>() {
        @Override
        public Meeting createFromParcel(Parcel in) {
            return new Meeting(in);
        }

        @Override
        public Meeting[] newArray(int size) {
            return new Meeting[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mReunionName);
        parcel.writeList(mEmails);
        parcel.writeString(mDate);
        parcel.writeString(mStartTime);
        parcel.writeString(mEndTime);
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
