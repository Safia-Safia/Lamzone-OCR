package com.safia.lamzone.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.DataInput;
import java.util.Date;
import java.util.List;

public class Meeting implements Parcelable {

    private String mReunionName;
    private List<String> mEmails;
    private Date mStartTime, mEndTime;
    private String mDate;
    private Room mRoom;

    public Meeting(String reunionName, List<String> email, Room room, String date, Date startTime, Date endTime) {
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
        mDate = in.readString();
        mStartTime = new Date(in.readLong());
        mEndTime = new Date (in.readLong());
        mRoom = in.readParcelable(Room.class.getClassLoader());
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
    public void writeToParcel(Parcel parcel, int flag) {
        parcel.writeString(mReunionName);
        parcel.writeStringList(mEmails);
        parcel.writeString(mDate);
        parcel.writeLong(mStartTime.getTime());
        parcel.writeLong(mEndTime.getTime());
        parcel.writeParcelable(mRoom, flag);
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

    public Date getStartTime() {
        return mStartTime;
    }

    public Date getEndTime() {
        return mEndTime;
    }

    public List<String> getEmails() {
        return mEmails;
    }


}
